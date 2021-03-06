package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;

public class WelcomeFrame extends DefaultEmojiContainer {

    /**
     * Welcome window is by default 20
     *
     * @param displayer The {@link Displayer}
     */
    public WelcomeFrame(Displayer displayer) {
        super(displayer, 56, 15);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {

        int offsetX = 4;
        int offsetY = 3;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 2; y++) {
                initial[y + offsetY][x + offsetX] = this.emojiManager.getEmoji(x + "w" + y);
            }
        }

        insertText("By RubbaBoy", 4, 3, initial);

        insertText("Keybinds:", 4, 6, initial);
        insertText("Alt + Arrows", 4, 7, initial);
        insertText("Switch Tabs", 18, 7, initial);
        insertText("Ctrl + R", 4, 8, initial);
        insertText("Run file", 18, 8, initial);
        insertText("Ctrl + N", 4, 9, initial);
        insertText("New file", 18, 9, initial);
        insertText("Ctrl + X", 4, 10, initial);
        insertText("Delete document", 18, 10, initial);
        insertText("Ctrl + H", 4, 11, initial);
        insertText("Help tab", 18, 11, initial);

        return initial;
    }

    private void insertText(String text, int x, int y, Emoji[][] grid) {
        final int[] offset = {x};
        text.chars().forEach(i -> grid[y + 3][offset[0]++] = this.emojiManager.getTextEmoji(String.valueOf(i)));
    }
}
