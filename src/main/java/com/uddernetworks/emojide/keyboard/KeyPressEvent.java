package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.discord.Emoji;

public class KeyPressEvent {
    private boolean alphanumeric;
    private char character;
    private Emoji emoji;

    public KeyPressEvent(Emoji emoji) {
        this.emoji = emoji;
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

    public Emoji getEmoji() {
        return emoji;
    }

    @Override
    public String toString() {
        return alphanumeric ? String.valueOf(character) : emoji.getName();
    }
}
