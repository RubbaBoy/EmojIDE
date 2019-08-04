package com.uddernetworks.emojide.gui.text;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import org.apache.commons.text.WordUtils;

import java.util.Arrays;

public class TextBlock {

    private int width;
    private int height;
    private char[][] chars;

    public TextBlock(int width, int height) {
        this.width = width;
        this.height = height;

        chars = new char[height][];
        for (int y = 0; y < height; y++) {
            var arr = new char[width];
            chars[y] = arr;
            Arrays.fill(chars[y], ' ');
        }
    }

    public void setText(String text) {
        var wrapped = WordUtils.wrap(text, this.width, "\n", true).split("\n");

        var loopTo = Math.min(height, wrapped.length);
        for (int y = 0; y < loopTo; y++) {
            var line = wrapped[y].toCharArray();
            var emojiLine = chars[y];
            for (int x = 0; x < line.length; x++) {
                emojiLine[x] = line[x];
            }
            chars[y] = emojiLine;
        }
    }

    public void setChar(char character, int x, int y) {
        if (x < 0 || x >= this.width) return;
        if (y < 0 || y >= this.height) return;
        chars[y][x] = character;
    }

    public Emoji[][] toEmoji(EmojiManager emojiManager) {
        return toEmoji(emojiManager, EmojiComponent.getEmptyGrid(Emoji.TRANSPARENT, this.width, this.height));
    }

    public Emoji[][] toEmoji(EmojiManager emojiManager, Emoji[][] initial) {
        for (int y = 0; y < this.height; y++) {
            var line = this.chars[y];
            var emojiLine = initial[y];
            for (int x = 0; x < line.length; x++) {
                emojiLine[x] = emojiManager.getEmoji(String.valueOf((int) line[x]));
            }
            initial[y] = emojiLine;
        }
        return initial;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) builder.append(chars[y][x]);
            builder.append('\n');
        }
        return builder.toString();
    }
}
