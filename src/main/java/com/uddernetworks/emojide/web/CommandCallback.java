package com.uddernetworks.emojide.web;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Map;

@FunctionalInterface
public interface CommandCallback {
    void accept(Member member, TextChannel channel, Map<String, String> query);
}
