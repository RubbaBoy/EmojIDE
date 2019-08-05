package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.StaticEmoji;

public class KeyPressEvent {
    private boolean alphanumeric;
    private char character;
    private StaticEmoji staticEmoji;

    public KeyPressEvent(StaticEmoji staticEmoji) {
        this.staticEmoji = staticEmoji;
    }

    public KeyPressEvent(char character) {
        this.alphanumeric = true;
        this.character = character;
    }

    public boolean isAlphanumeric() {
        return alphanumeric;
    }

    public char getCharacter() {
        return character;
    }

    public StaticEmoji getStaticEmoji() {
        return staticEmoji;
    }

    @Override
    public String toString() {
        return alphanumeric ? String.valueOf(character) : staticEmoji.getName();
    }
}
