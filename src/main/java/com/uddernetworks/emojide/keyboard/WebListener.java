package com.uddernetworks.emojide.keyboard;

import simplenet.Client;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public interface WebListener {

    /**
     * Starts the web listener's server, by default on port 6969.
     *
     * @param keyboardInputManager The {@link SimpleKeyboardInputManager} to send input data to.
     */
    void start(KeyboardInputManager keyboardInputManager);

    /**
     * Invokes {@link #parseHeaders(Client, BiConsumer)} and if the requestHeaderFunction returns false, an error
     * response is sent. Either way, the {@link Client} given is closed at the end.
     *
     * @param client                The {@link Client}
     * @param requestHeaderFunction The function giving request data and header data, returning if the request was
     *                              successful.
     */
    void tryAndParse(Client client, BiFunction<String[], Map<String, String>, Boolean> requestHeaderFunction);

    /**
     * Parses the headers from the given {@link Client}, invoking the function by default when the Connection header is
     * given.
     *
     * @param client          The {@link Client}
     * @param headersComplete The request data and headers
     */
    void parseHeaders(Client client, BiConsumer<String[], Map<String, String>> headersComplete);

    /**
     * Generated a request in bytes to be sent from the given body.
     *
     * @param body The body to include in the request
     * @return The request, in bytes, to send
     */
    byte[] generateRequest(String body);

    /**
     * Creates default headers for {@link #generateRequest(String)}. By default, this is:
     * <pre>
     *     HTTP/1.1 418 I'm a teapot
     *     Server: EmojIDE
     *     Content-Length: [dynamic]
     *     Content-Type: text/html
     *     Connection: Closed
     *
     * </pre>
     *
     * @return The headers
     */
    String createHeaders();
}
