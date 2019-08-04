package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.main.EmojIDE;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simplenet.Client;
import simplenet.Server;
import simplenet.packet.Packet;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Custom HTTP implementation. It's not overengineering, it's a fucking waste of time.
 */
public class WebListener {

    private static Logger LOGGER = LoggerFactory.getLogger(WebListener.class);

    private EmojIDE emojIDE;

    public WebListener(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    public void start(KeyboardInputManager keyboardInputManager) {
        var server = new Server();

        server.onConnect(client -> {
            tryAndParse(client, (request, headers) -> {
                try {
                    var url = request[1];
                    if (!url.startsWith("/e")) return false;

                    var queryParts = url.split("\\?");
                    if (queryParts.length != 2) return false;
                    var query = queryParts[1];
                    var kvSplit = query.split("=");
                    if (kvSplit.length != 2) return false;

                    if (!kvSplit[0].equalsIgnoreCase("k")) return false;
                    var clicked = kvSplit[1];
                    if ((!clicked.startsWith("A") && !clicked.startsWith("E")) || clicked.length() <= 1 || !StringUtils.isNumeric(clicked.substring(1))) return false;

                    keyboardInputManager.handleKey(clicked);

                    Packet.builder().putBytes(generateRequest("{\"message\": \" " + request[1] + "\"}")).writeAndFlush(client);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            });
        });

        server.bind("localhost", 6969);
        LOGGER.info("WebServer running...");
    }

    private void tryAndParse(Client client, BiFunction<String[], Map<String, String>, Boolean> requestHeaderFunction) {
        parseHeaders(client, (request, headers) -> {
            if (!requestHeaderFunction.apply(request, headers)) Packet.builder().putBytes(generateRequest("{\"message\": \"Bruh what the fuck is this?\"}")).writeAndFlush(client);
            client.close();
        });
    }

    private void parseHeaders(Client client, BiConsumer<String[], Map<String, String>> headersComplete) {
        var finishedRequest = new AtomicBoolean(false);
        var request = new AtomicReference<>(new StringBuilder());
        var finalRequest = new AtomicReference<>(new String[3]);

        var headers = new AtomicReference<>(new HashMap<String, String>());
        var buffer = new AtomicReference<>(new StringBuilder());
        client.readByteUntil(ascii -> {
            if (ascii == 13) {
                if (!finishedRequest.getAndSet(true)) {
                    finalRequest.set(request.get().toString().split("\\s+", 3));
                    return true;
                }

                var curr = buffer.getAndSet(new StringBuilder()).toString();
                var split = curr.split(":", 2);
                headers.get().put(split[0], split[1]);

                if (curr.contains("Connection:")) {
                    headersComplete.accept(finalRequest.get(), headers.get());
                    return false;
                }
            } else {
                (finishedRequest.get() ? buffer : request).get().append((char) ascii);
            }

            return true;
         });
    }

    private byte[] generateRequest(String body) {
        return (createHeaders().replace("%CTL%", String.valueOf(body.length())) + body).getBytes();
    }

    private String createHeaders() {
        return "HTTP/1.1 418 I'm a teapot\n" +
                "Server: EmojIDE\n" +
                "Content-Length: %CTL%\n" +
                "Content-Type: application/json\n" +
                "Connection: Closed\n\n";
    }

}
