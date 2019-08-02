package com.uddernetworks.emojide.gui.components.styled;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.EmojiContainer;

import java.util.Arrays;

public class StyleUtils {
    /**
     * Gets the styled base by this class, to be added onto by other instances.
     *
     * @return The initial Emoji grid
     */
    public static Emoji[][] renderInitialStyle(Emoji background, Emoji border, int width, int height) {
        return drawBorder(EmojiContainer.getEmptyGrid(background, width, height), border);
    }

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
}
