package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.DefaultEmojiComponent;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiComponent;

public class WelcomeFrame extends DefaultEmojiComponent {

    /**
     * Welcome window is by default 20
     *
     * @param displayer The {@link Displayer}
     */
    public WelcomeFrame(Displayer displayer) {
        super(displayer, 56, 20);
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

//        var index = new int[] {0};
//        "By RubbaBoy".chars().forEach(i -> initial[offsetY + 3][offsetX + index[0]++] = this.emojiManager.getEmoji(String.valueOf(i)));

        insertText("By RubbaBoy", 4, 3, initial);

        insertText("Keybinds:", 4, 6, initial);
        insertText("Alt + Arrows", 4, 7, initial);  insertText("Switch Tabs", 18, 7, initial);
        insertText("Alt + C", 4, 8, initial);       insertText("Close tab", 18, 8, initial);
        insertText("Ctrl + S", 4, 9, initial);      insertText("Save file", 18, 9, initial);
        insertText("Ctrl + R", 4, 10, initial);     insertText("Run file", 18, 10, initial);
        insertText("Ctrl + N", 4, 11, initial);     insertText("New file", 18, 11, initial);
        insertText("Ctrl + H", 4, 12, initial);     insertText("Help tab", 18, 12, initial);

        return initial;
    }

    private void insertText(String text, int x, int y, Emoji[][] grid) {
        final int[] offset = {x};
        text.chars().forEach(i -> grid[y + 3][offset[0]++] = this.emojiManager.getEmoji(String.valueOf(i)));
    }
}
