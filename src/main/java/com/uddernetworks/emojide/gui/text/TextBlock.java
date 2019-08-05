package com.uddernetworks.emojide.gui.text;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import org.apache.commons.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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

    public char[][] getChars() {
        return chars;
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

    public char getCharacter(int x, int y) {
        if (x < 0 || x >= this.width) return ' ';
        if (y < 0 || y >= this.height) return ' ';
        return chars[y][x];
    }

    public Emoji[][] toEmoji(EmojiManager emojiManager) {
        return toEmoji(emojiManager, EmojiComponent.getEmptyGrid(StaticEmoji.TRANSPARENT, this.width, this.height));
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

    public void addEmpty(int y) {
        if (y > this.height - 2) return;
        var inserting = new char[this.width];
        Arrays.fill(inserting, ' ');
        var array = new ArrayList<>(Arrays.asList(this.chars));
        array.add(y + 1, inserting);
        array.remove(array.remove(array.size() - 1));
        this.chars = array.toArray(char[][]::new);
    }

    public void removeChar(int x, int y) {
        var row = this.chars[y];
        System.out.println("Before = " + Arrays.toString(row));
        System.arraycopy(row, x, row, x - 1, row.length - x);
        System.out.println("After = " + Arrays.toString(row));
        this.chars[y] = row;
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
