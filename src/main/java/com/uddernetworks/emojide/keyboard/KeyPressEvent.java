package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.event.Cancellable;

/**
 * An event to store data about a key being pressed via the {@link SimpleKeyboardInputManager}.
 */
public class KeyPressEvent extends Cancellable {
    private boolean alphanumeric;
    private char character;
    private StaticEmoji staticEmoji;

    /**
     * Creates a {@link KeyPressEvent} if a {@link StaticEmoji} was pressed.
     *
     * @param staticEmoji The pressed {@link StaticEmoji}
     */
    public KeyPressEvent(StaticEmoji staticEmoji) {
        this.staticEmoji = staticEmoji;
    }

    /**
     * Creates a {@link KeyPressEvent} if a character was pressed.
     *
     * @param character The pressed character
     */
    public KeyPressEvent(char character) {
        this.alphanumeric = true;
        this.character = character;
    }

    /**
     * Gets if a character was pressed.
     *
     * @return If a character was pressed
     */
    public boolean isAlphanumeric() {
        return alphanumeric;
    }

    /**
     * Gets the character pressed, if any.
     *
     * @return The character pressed
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Gets the {@link StaticEmoji} pressed, if any.
     *
     * @return The {@link StaticEmoji} pressed
     */
    public StaticEmoji getStaticEmoji() {
        return staticEmoji;
    }

    @Override
    public String toString() {
        return alphanumeric ? String.valueOf(character) : staticEmoji.getName();
    }
}
