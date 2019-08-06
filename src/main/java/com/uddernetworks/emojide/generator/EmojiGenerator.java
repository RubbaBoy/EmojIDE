package com.uddernetworks.emojide.generator;

/**
 * A generator to create emoji images for {@link com.uddernetworks.emojide.discord.StaticEmoji}s.
 */
public interface EmojiGenerator {

    /**
     * Creates letters in Consolas in the path `generated_emojis`. Solidly colored emojis are also created.
     */
    void generate();
}
