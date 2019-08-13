package com.uddernetworks.emojide.web;

import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.utils.Lists;
import net.dv8tion.jda.api.JDA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebCallbackHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(WebCallbackHandler.class);

    private EmojIDE emojIDE;
    private JDA jda;
    private List<WebCallback> callbacks = new ArrayList<>();

    public WebCallbackHandler(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        this.jda = emojIDE.getJda();
    }

    public void registerCallback(String name, List<String> requiredParams, Consumer<Map<String, String>> onReceive) {
        callbacks.add(new WebCallback(name, requiredParams, onReceive));
    }

    public void registerCommandCallback(String name, List<String> requiredParams, CommandCallback commandCallback) {
        var commandRequired = new ArrayList<>(Arrays.asList("channel", "member"));
        commandRequired.addAll(requiredParams);
        registerCallback(name, commandRequired, query -> {
            var channel = jda.getTextChannelById(query.get("channel"));
            if (channel == null) return;
            var member = channel.getGuild().getMemberById(query.get("member"));
            if (member == null) return;
            commandRequired.forEach(query::remove);
            commandCallback.accept(member, channel, query);
        });
    }

    public String generateMdLink(String text, String name, Map<String, String> query) {
        return "[" + name + "](" + generateLink(name, query) + ")";
    }

    public String generateLink(String name, Map<String, String> query) {
        var base = new StringBuilder("http://localhost:6969/c/" + name);
        if (query.isEmpty()) return base.toString();
        base.append("?");
        query.forEach((key, value) -> base.append(key).append("=").append(value).append("&"));
        return base.substring(0, base.length() - 1);
    }

    public void handleCallback(String url, Map<String, String> query) {
        var subTo = url.indexOf("?");
        subTo = subTo == -1 ? url.length() : subTo;
        url = url.substring(3,  subTo);

        LOGGER.info("Handling callback for {} with {}", url, query);
        String finalUrl = url;
        callbacks.stream().filter(callback -> callback.getName().equalsIgnoreCase(finalUrl)).findFirst().ifPresent(callback -> {
            var gotKeys = new ArrayList<>(query.keySet());
            gotKeys.retainAll(callback.getRequired());
            if (gotKeys.size() != callback.getRequired().size()) {
                LOGGER.error("Callback did not receive the correct query parameters.");
                return;
            }

            callback.receive(gotKeys.stream().collect(Collectors.toMap(key -> key, query::get)));
        });

    }
}
