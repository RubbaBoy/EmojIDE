package com.uddernetworks.emojide.discord.emoji;

/**
 * This interface allows for custom Emojis, i.e. dynamically created ones, originally intended for uploaded and
 * converted images of arbitrary sizes.
 */
public interface Emoji {

    /**
     * Gets the name of the emoji.
     *
     * @return The name of the emoji
     */
    String getName();

    /**
     * Gets the text to display in messages to show the emoji.
     *
     * @return The displaying text of the emoji
     */
    String getDisplay();
}
