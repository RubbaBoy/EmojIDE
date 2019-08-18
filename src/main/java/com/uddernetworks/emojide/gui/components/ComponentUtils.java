package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;

import java.util.Arrays;

/**
 * Utilities used by {@link EmojiComponent} instances.
 */
public class ComponentUtils {
    /**
     * Creates a 2D array of null {@link Emoji}s, with the given dimensions.
     *
     * @param width  The width of the array
     * @param height The height of the array
     * @return The 2D array
     */
    public static Emoji[][] getNullGrid(int width, int height) {
        var rows = new Emoji[height][];
        for (int i = 0; i < height; i++) rows[i] = new Emoji[width];
        return rows;
    }

    /**
     * Creates a 2D array of the {@link Emoji} {@link StaticEmoji#DISCORD}, with the given dimensions.
     *
     * @param width  The width of the array
     * @param height The height of the array
     * @return The 2D array
     */
    public static Emoji[][] getEmptyGrid(int width, int height) {
        return getEmptyGrid(StaticEmoji.TRANSPARENT, width, height);
    }

    /**
     * Creates a 2D array of the given {@link Emoji} with the given dimensions.
     *
     * @param emoji The emoji to fill the grid with
     * @param width  The width of the array
     * @param height The height of the array
     * @return The 2D array
     */
    public static Emoji[][] getEmptyGrid(Emoji emoji, int width, int height) {
        if (emoji == null) return getNullGrid(width, height);
        var rows = new Emoji[height][];
        for (int y = 0; y < height; y++) {
            rows[y] = new Emoji[width];
            Arrays.fill(rows[y], emoji);
        }
        return rows;
    }
}
