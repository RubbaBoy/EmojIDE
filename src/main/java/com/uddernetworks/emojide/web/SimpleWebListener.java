package com.uddernetworks.emojide.web;

import com.uddernetworks.emojide.gui.components.MockupWebpageDisplayer;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.main.EmojIDE;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simplenet.Client;
import simplenet.Server;
import simplenet.packet.Packet;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Custom HTTP implementation. It's not overengineering, it's a fucking waste of time.
 */
public class SimpleWebListener implements WebListener {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleWebListener.class);

    private final List<String> goodContains;

    private EmojIDE emojIDE;
    private Map<String, List<String>> usedRandoms = new ConcurrentHashMap<>();

    public SimpleWebListener(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        goodContains = Arrays.asList("localhost", "127.0.0.1", EmojIDE.getConfigManager().getConfig().get("web.host"));
    }

    @Override
    public void start(KeyboardInputManager keyboardInputManager) {
        var server = new Server();

        server.onConnect(client -> tryAndParse(client, (request, headers) -> {
            try {
                var url = request[1];
                if (url.startsWith("/e") || url.startsWith("/z/")) {
                    var queryParts = url.split("\\?");
                    if (queryParts.length != 2) return false;
                    var query = queryParts[1];
                    Map<String, String> kvSplit = Arrays.stream(query.split("&")).map(kv -> {
                        var split = kv.split("=");
                        return new AbstractMap.SimpleEntry<>(split[0], split[1]);
                    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


                    if (url.startsWith("/e")) {
                        if (!kvSplit.containsKey("k") || !kvSplit.containsKey("r")) return false;

                        var clicked = kvSplit.get("k");
                        if ((!clicked.startsWith("A") && !clicked.startsWith("E")) || clicked.length() <= 1 || !StringUtils.isNumeric(clicked.substring(1)))
                            return false;

                        var random = kvSplit.get("r");
                        usedRandoms.putIfAbsent(clicked, Collections.synchronizedList(new ArrayList<>()));
                        var randoms = usedRandoms.get(clicked);
                        if (randoms.contains(random)) {
                            // TODO: Clear after X time
                            LOGGER.error("Random already used for k {} and r {}", clicked, random);
                            return false;
                        }

                        randoms.add(random);

                        keyboardInputManager.handleKey(clicked);

                        Packet.builder().putBytes(generateRequest("")).writeAndFlush(client);
                    } else {
                        var random = kvSplit.get("r");
                        var str = String.valueOf(kvSplit.hashCode());
                        usedRandoms.putIfAbsent(str, Collections.synchronizedList(new ArrayList<>()));
                        var randoms = usedRandoms.get(str);
                        if (randoms.contains(random)) return false;

                        emojIDE.getWebCallbackHandler().handleCallback(url, kvSplit);
                        Packet.builder().putBytes(generateRequest("")).writeAndFlush(client);
                    }
                } else if (url.startsWith("/s")) {
                    Packet.builder().putBytes(generateRequest("<script>var xmlHttp = new XMLHttpRequest();\n" +
                            "xmlHttp.open(\"GET\",\"" + EmojIDE.getConfigManager().getConfig().get("web.js-host") + "" + url.replace("/s", "/e") + "&r=\"+Math.floor(Math.random()*999999999)+\"\",false);\n" +
                            "xmlHttp.send(null);" +
                            "window.open('','_self').close();</script>")).writeAndFlush(client);
                } else if (url.startsWith("/c")) {
                    Packet.builder().putBytes(generateRequest("<script>var xmlHttp = new XMLHttpRequest();\n" +
                            "xmlHttp.open(\"GET\",\"" + EmojIDE.getConfigManager().getConfig().get("web.js-host") + "" + url.replace("/c", "/z") + "&r=\"+Math.floor(Math.random()*999999999)+\"\",false);\n" +
                            "xmlHttp.send(null);" +
                            "window.open('','_self').close();</script>")).writeAndFlush(client);
                } else if (url.startsWith("/w")) {
                    Packet.builder().putBytes(generateRequest(String.valueOf(MockupWebpageDisplayer.UPDATED.getAndSet(false)))).writeAndFlush(client);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }));

        server.bind("0.0.0.0", EmojIDE.getConfigManager().getConfig().get("web.port"));
        LOGGER.info("WebServer running...");
    }

    @Override
    public void tryAndParse(Client client, BiFunction<String[], Map<String, String>, Boolean> requestHeaderFunction) {
        parseHeaders(client, (request, headers) -> {
            if (!requestHeaderFunction.apply(request, headers))
                Packet.builder().putBytes(generateRequest("<p>Bruh what the fuck is this</p>")).writeAndFlush(client);
            client.close();
        });
    }

    @Override
    public void parseHeaders(Client client, BiConsumer<String[], Map<String, String>> headersComplete) {
        var finishedRequest = new AtomicBoolean(false);
        var request = new AtomicReference<>(new StringBuilder());
        var finalRequest = new AtomicReference<>(new String[3]);

        var headers = new AtomicReference<>(new HashMap<String, String>());
        var buffer = new AtomicReference<>(new StringBuilder());
        client.readByteUntil(ascii -> {
            if (ascii == 13) {
                if (!finishedRequest.getAndSet(true)) {
                    var split = request.get().toString().split("\\s+", 3);

                    // This has to do with an ongoing issue with my network locally, contact me if interested in details
                    if (split.length == 1) split = new String[] {"", split[0]};
                    if (!split[0].equals("GET") || (!split[1].startsWith("/") && goodContains.parallelStream().noneMatch(split[1]::contains))) {
                        client.close();
                        return false;
                    }
//                    LOGGER.info("Allowing {}", Arrays.toString(split));

                    finalRequest.set(split);
                    return true;
                }

                var curr = buffer.getAndSet(new StringBuilder()).toString();
                var split = curr.split(":", 2);
                headers.get().put(split[0], split.length != 2 ? "" : split[1]);

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

    @Override
    public byte[] generateRequest(String body) {
        return (createHeaders().replace("%CTL%", String.valueOf(body.length())) + body).getBytes();
    }

    @Override
    public String createHeaders() {
        return "HTTP/1.1 200 OK\n" +
                "Server: EmojIDE\n" +
                "Content-Length: %CTL%\n" +
                "Content-Type: text/html\n" +
                "Access-Control-Allow-Origin: *\n" +
                "Connection: Closed\n\n";
    }

}
