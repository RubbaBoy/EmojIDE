package com.uddernetworks.emojide.gui.components.styled;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;

import java.util.Arrays;

import static com.uddernetworks.emojide.gui.components.ComponentUtils.getEmptyGrid;

/**
 * Utilities used by {@link StyledEmojiComponent} instances.
 */
public class StyleUtils {
    /**
     * Gets the styled base by this class, to be added onto by other instances.
     *
     * @return The initial Emoji grid
     */
    public static Emoji[][] renderInitialStyle(Emoji background, Emoji border, int width, int height) {
        return drawBorder(getEmptyGrid(background, width, height), border);
    }

    /**
     * Draws a border of a given {@link Emoji} with a width of 1 in the given 2D array.
     *
     * @param rows   The 2D array to (presumably) render
     * @param border The {@link Emoji} to set as the border
     * @return The mutated 2D array
     */
    public static Emoji[][] drawBorder(Emoji[][] rows, Emoji border) {
        if (border == null) return rows;
        Arrays.fill(rows[0], border);
        Arrays.fill(rows[rows.length - 1], border);
        for (int y = 0; y < rows.length; y++) {
            rows[y][0] = border;
            rows[y][rows[0].length - 1] = border;
        }
        return rows;
    }

    /**
     * Creates a thin, light gray border with {@link StaticEmoji#LTABBED_FRAME}, {@link StaticEmoji#RTABBED_FRAME},
     * {@link StaticEmoji#TTABBED_FRAME}, and {@link StaticEmoji#BTABBED_FRAME},.
     *
     * @param rows The 2D array to (presumably) render
     * @return The mutated 2D array
     */
    public static Emoji[][] drawThinBorder(Emoji[][] rows) {
        for (int y = 1; y < rows.length - 1; y++) {
            rows[y][0] = StaticEmoji.LTABBED_FRAME;
            rows[y][rows[0].length - 1] = StaticEmoji.RTABBED_FRAME;
        }

        Arrays.fill(rows[0], 1, rows[0].length - 1, StaticEmoji.TTABBED_FRAME);
        Arrays.fill(rows[rows.length - 1], 1, rows[rows.length - 1].length - 1, StaticEmoji.BTABBED_FRAME);
        return rows;
    }
}
