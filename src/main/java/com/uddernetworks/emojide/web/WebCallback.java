package com.uddernetworks.emojide.web;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class WebCallback {

    private String name;
    private List<String> required;
    private Consumer<Map<String, String>> onReceive;

    public WebCallback(String name, List<String> required, Consumer<Map<String, String>> onReceive) {
        this.name = name;
        this.required = required;
        this.onReceive = onReceive;
    }

    public void receive(Map<String, String> query) {
        onReceive.accept(query);
    }

    public String getName() {
        return name;
    }

    public List<String> getRequired() {
        return required;
    }
}
