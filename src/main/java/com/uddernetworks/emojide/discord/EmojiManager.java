package com.uddernetworks.emojide.discord;

/**
 * The manager for all {@link Emoji}s. Upon initialization, any emojis from {@link StaticEmoji} are uploaded to an emoji
 * server in the config list, if the emoji is non existant anywhere.
 */
public interface EmojiManager {

    /**
     * Gets the {@link Emoji} by the given name, case insensitive.
     *
     * @param name The name of the emoji
     * @return The {@link Emoji}
     */
    Emoji getEmoji(String name);
}
