package com.uddernetworks.emojide.web;

import java.util.List;
import java.util.Map;

public interface WebCallback {

    /**
     * When the callback is received via a web request.
     *
     * @param query The web query
     */
    void receive(Map<String, String> query);

    /**
     * The unique name of the callback.
     *
     * @return The name
     */
    String getName();

    /**
     * Gets the required query parameters. If not all parameters are found in a request, the callback is not invoked.
     *
     * @return The required parameters
     */
    List<String> getRequired();
}
