package com.uddernetworks.emojide.gui.text;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.StaticEmoji;
import org.apache.commons.collections.list.GrowthList;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.uddernetworks.emojide.gui.components.ComponentUtils.getEmptyGrid;

/**
 * A {@link TextBlock} that only tracks text in a fixed area, truncating any data outside of the given width and height.
 */
public class DynamicTextBlock implements TextBlock {

    private static Logger LOGGER = LoggerFactory.getLogger(DynamicTextBlock.class);

    private static final Supplier<Character> SPACE_SUPPLIER = () -> ' ';
    private static final Supplier<AutoGrowArrayList<Character>> LIST_SUPPLIER = () -> new AutoGrowArrayList<>(SPACE_SUPPLIER);

    private int width;
    private int height;
    private AutoGrowArrayList<AutoGrowArrayList<Character>> chars;

    /**
     * Creates a {@link DynamicTextBlock} with a dynamic width and height.
     */
    public DynamicTextBlock() {
        this.chars = new AutoGrowArrayList<>(LIST_SUPPLIER);
        this.chars.add(new AutoGrowArrayList<>(SPACE_SUPPLIER));
        addChar(' ', 0, 0);
    }

    @Override
    public AutoGrowArrayList<AutoGrowArrayList<Character>> getCharList() {
        return this.chars;
    }

    @Override
    public void setText(String text) {
        this.chars = new AutoGrowArrayList<>(LIST_SUPPLIER);
        Arrays.stream(text.split("\n"))
                .forEach(line -> this.chars.add(Arrays.stream(line.split(""))
                        .map(str -> str.charAt(0))
                        .collect(Collectors.toCollection(LIST_SUPPLIER))));
    }

    @Override
    public void setChar(char character, int x, int y) {
//        if (x < 0 || x >= this.width) return;
//        if (y < 0 || y >= this.height) return;
        chars.get(y).set(x, character);
    }

    @Override
    public char getCharacter(int x, int y) {
//        if (x < 0 || x >= this.width) return ' ';
//        if (y < 0 || y >= this.height) return ' ';
        return chars.get(y).get(x);
    }

    @Override
    public void addChar(char character, int x, int y) {
//        if (x >= this.width) return;
        this.chars.get(y).add(x, character);
    }

    @Override
    public Emoji[][] toEmoji(EmojiManager emojiManager) {
        return toEmoji(emojiManager, getEmptyGrid(StaticEmoji.TRANSPARENT, getMaxWidth(), getMaxHeight()));
    }

    private int getMaxWidth() {
        return this.chars.stream().mapToInt(AutoGrowArrayList::size).max().orElse(0);
    }

    private int getMaxHeight() {
        return this.chars.size();
    }

    @Override
    public Emoji[][] toEmoji(EmojiManager emojiManager, Emoji[][] initial) {
        for (int y = 0; y < this.chars.size(); y++) {
            var line = this.chars.get(y);
            var emojiLine = initial[y];
            for (int x = 0; x < line.size(); x++) {
                emojiLine[x] = emojiManager.getEmoji(String.valueOf((int) line.get(x)));
            }
            initial[y] = emojiLine;
        }
        return initial;
    }

    @Override
    public void newlineAt(int x, int y) {
//        if (x >= this.width) return;
        var row = this.chars.get(y);

        var lowerRow = row.subList(x, Math.max(x, row.size() - 1));
        var upperRow = row.subList(0, x);

        this.chars.set(y, upperRow);
        this.chars.set(y + 1, lowerRow);
    }

    @Override
    public void addEmpty(int y) {
//        if (y > this.height - 2) return;
        this.chars.add(y + 1, new AutoGrowArrayList<>(SPACE_SUPPLIER));
    }

    @Override
    public void addAll(int y, List<Character> line) {
        var joining = this.chars.get(y);

        int copyX = 0;
        for (int x = line.size() - 1; x >= 0; x--) {
            if (line.get(x) == ' ' || line.get(x) == 0) continue;
            copyX = x;
            break;
        }

        joining.addAll(line.subList(0, copyX + 1));
        this.chars.set(y, joining);
        this.chars.set(y + 1, new AutoGrowArrayList<>(SPACE_SUPPLIER));
    }

    @Override
    public void removeChar(int x, int y) {
//        if (x >= this.width) return;
        this.chars.get(y).remove(x);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) builder.append(chars.get(y).get(x));
            builder.append('\n');
        }
        return builder.toString();
    }
}
