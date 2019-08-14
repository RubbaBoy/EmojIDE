package com.uddernetworks.emojide.generator;

import com.uddernetworks.emojide.discord.emoji.StaticEmoji;

/**
 * A generator to create emoji images for {@link StaticEmoji}s.
 */
public interface EmojiGenerator {

    /**
     * Creates letters in Consolas in the path `generated_emojis`. Solidly colored emojis are also created.
     */
    void generate();
}
