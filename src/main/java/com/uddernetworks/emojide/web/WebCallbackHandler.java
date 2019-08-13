package com.uddernetworks.emojide.web;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface WebCallbackHandler {

    /**
     * Registers a callback with the given name and required parameters.
     *
     * @param name The unique name of the callback
     * @param requiredParams The query parameters that must be available for the callback to invoke
     * @param onReceive When a callback is invoked
     */
    void registerCallback(String name, List<String> requiredParams, Consumer<Map<String, String>> onReceive);

    /**
     * Registers a callback with the intended use being for commands, as the `channel` id and `member` id are parsed and
     * validated for the callback to be invoked.
     *
     * @param name The unique name of the callback
     * @param commandCallback The callback to be invoked
     */
    void registerCommandCallback(String name, CommandCallback commandCallback);

    /**
     * Registers a callback with the intended use being for commands, as the `channel` id and `member` id are parsed and
     * validated for the callback to be invoked. This allows for other required parameters.
     *
     * @param name The unique name of the callback
     * @param requiredParams Extra required parameters for the callback
     * @param commandCallback The callback to be invoked
     */
    void registerCommandCallback(String name, List<String> requiredParams, CommandCallback commandCallback);

    /**
     * Generates a link to be placed directly in markdown that references a callback name with query parameters.
     *
     * @param text The text to display in the link
     * @param name The name of the callback
     * @param query The query parameters of the link
     * @return The markdown of the link
     */
    String generateMdLink(String text, String name, Map<String, String> query);

    /**
     * Generates a raw link referencing a callback name with query parameters.
     *
     * @param name The name of the callback
     * @param query The query parameters of the callback
     * @return The raw link
     */
    String generateLink(String name, Map<String, String> query);

    /**
     * Invoked by the webserver when a request for a callback is made. This parses and finds appropriate listeners with
     * the correct query parameters.
     *
     * @param url The ending URL without the prefix, e.g. if http://localhost:6969/z/info?one=two is requested, this
     *            parameter will be info?one=two
     * @param query The query parameters of the request
     */
    void handleCallback(String url, Map<String, String> query);
}
