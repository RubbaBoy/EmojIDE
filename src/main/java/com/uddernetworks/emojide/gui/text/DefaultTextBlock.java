package com.uddernetworks.emojide.gui.text;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.StaticEmoji;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static com.uddernetworks.emojide.gui.components.ComponentUtils.getEmptyGrid;

/**
 * A {@link TextBlock} that only tracks text in a fixed area, truncating any data outside of the given width and height.
 */
public class DefaultTextBlock implements TextBlock {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultTextBlock.class);

    private int width;
    private int height;
    private char[][] chars;

    /**
     * Creates a {@link DefaultTextBlock} with a fixed with and height, in emojis.
     *
     * @param width  The width
     * @param height The height
     */
    public DefaultTextBlock(int width, int height) {
        this.width = width;
        this.height = height;

        chars = new char[height][];
        for (int y = 0; y < height; y++) {
            var arr = new char[width];
            Arrays.fill(arr, ' ');
            chars[y] = arr;
        }
    }

    @Override
    public char[][] getCharArray() {
        return chars;
    }

    @Override
    public void setText(String text) {
        var wrapped = WordUtils.wrap(text, this.width, "\n", true).split("\n");

        var loopTo = Math.min(height, wrapped.length);
        for (int y = 0; y < loopTo; y++) {
            var line = wrapped[y].toCharArray();
            var emojiLine = chars[y];
            System.arraycopy(line, 0, emojiLine, 0, line.length);
            chars[y] = emojiLine;
        }
    }

    @Override
    public String getText() {
        var builder = new StringBuilder();
        for (char[] x : chars) {
            for (char y : x) {
                if (y == 0) continue;
                builder.append(y);
            }
            builder.append('\n');
        }
        return builder.toString().strip();
    }

    @Override
    public void setChar(char character, int x, int y) {
        if (x < 0 || x >= this.width) return;
        if (y < 0 || y >= this.height) return;
        chars[y][x] = character;
    }

    @Override
    public char getCharacter(int x, int y) {
        if (x < 0 || x >= this.width) return ' ';
        if (y < 0 || y >= this.height) return ' ';
        return chars[y][x];
    }

    @Override
    public void addChar(char character, int x, int y) {
        if (x >= this.width) return;
        var row = this.chars[y];
        System.arraycopy(row, x, row, x + 1, row.length - x - 1);
        row[x] = character;
        this.chars[y] = row;
    }

    @Override
    public Emoji[][] toEmoji(EmojiManager emojiManager) {
        return toEmoji(emojiManager, getEmptyGrid(StaticEmoji.TRANSPARENT, this.width, this.height));
    }

    @Override
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
    public void newlineAt(int x, int y) {
        if (x >= this.width) return;
        if (y + 1 >= height) return;
        var row = this.chars[y];
        var upperRow = new char[width];
        var lowerRow = new char[width];
        System.arraycopy(row, x, lowerRow, 0, row.length - x - 1);
        System.arraycopy(row, 0, upperRow, 0, x);
        chars[y] = upperRow;
        addEmpty(y + 1);
        chars[y + 1] = lowerRow;
    }

    @Override
    public void addEmpty(int y) {
        if (y > this.height - 2) return;
        var inserting = new char[this.width];
        Arrays.fill(inserting, ' ');
        var array = new ArrayList<>(Arrays.asList(this.chars));
        array.add(y + 1, inserting);
        array.remove(array.remove(array.size() - 1));
        this.chars = array.toArray(char[][]::new);
    }

    @Override
    public void addAll(int y, char[] line) {
        var joining = chars[y];

        int copyX = 0;
        for (int x = joining.length - 1; x >= 0; x--) {
            if (joining[x] == ' ' || joining[x] == 0) continue;
            copyX = x;
            break;
        }

        System.arraycopy(line, 0, joining, copyX + 1, line.length - copyX - 1);

        chars[y + 1] = new char[width];
        chars[y] = joining;
    }

    @Override
    public void removeChar(int x, int y) {
        if (x >= this.width) return;
        var row = this.chars[y];
        System.arraycopy(row, x, row, x - 1, row.length - x);
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
