package com.uddernetworks.emojide.discord.font;

import com.uddernetworks.emojide.main.ChoosableEnum;

import java.util.Arrays;
import java.util.Optional;

public enum Font implements ChoosableEnum {
    CONSOLAS("", "Consolas"),
    FIRA_CODE("f", "Fira Code");

    private String prefix;
    private String name;

    Font(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public static Optional<Font> fromName(String name) {
        return Arrays.stream(values()).filter(font -> font.name.equalsIgnoreCase(name)).findFirst();
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getName() {
        return name;
    }
}
