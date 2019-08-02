package com.uddernetworks.emojide.discord;

import net.dv8tion.jda.api.entities.Emote;

public class Emoji {
    private String name;
    private Emote emote;

    public Emoji(String name) {
        this.name = name;
    }

    public Emoji(Emote emote) {
        this.emote = emote;
        this.name = emote.getName();
    }

    public boolean isCustom() {
        return this.emote != null;
    }

    public String getName() {
        return name;
    }

    public Emote getEmote() {
        return emote;
    }

    @Override
    public String toString() {
        return this.emote != null ? this.emote.getAsMention() : ":" + name + ":";
    }
}
