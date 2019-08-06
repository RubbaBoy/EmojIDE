package com.uddernetworks.emojide.gui.text;

import com.uddernetworks.emojide.discord.DefaultEmojiManager;
import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;

import java.util.List;

/**
 * A statically-sized block of text that is meant for the dynamic content of a component containing text. This contains
 * methods to add, remove, and move characters as if they were being changed with a keyboard.
 */
public interface TextBlock {

    /**
     * Gets the characters of the {@link TextBlock}. The 2D array is just an array of rows, meaning the first length is
     * the height, and the inner length is the width.
     *
     * @return A 2D array of characters.
     */
    default char[][] getCharArray() {
        throw new UnsupportedOperationException("This implementation does not support #getCharArray()");
    }

    /**
     * Gets the characters of the {@link TextBlock}. The list grid is just a list of rows, meaning the first size is
     * the height, and the inner size is the width.
     *
     * @return A grid of characters
     */
    default AutoGrowArrayList<AutoGrowArrayList<Character>> getCharList() {
        throw new UnsupportedOperationException("This implementation does not support #getCharList()");
    }

    /**
     * Replaces the current text entirely with the given text.
     *
     * @param text The text to set
     */
    void setText(String text);

    /**
     * Sets the given coordinates to the given character. No other characters or references are modified.
     *
     * @param character The character to set
     * @param x The X position of the grid
     * @param y The Y position of the grid
     */
    void setChar(char character, int x, int y);

    /**
     * Gets the character at the given coordinates.
     *
     * @param x The X position of the grid
     * @param y The Y position of the grid
     * @return The character
     */
    char getCharacter(int x, int y);

    /**
     * Inserts the given character at the given coordinates. This will offset any other characters in the line
     * proceeding it, truncating any overflows.
     *
     * @param character The character to insert
     * @param x The X position of the grid
     * @param y The Y position of the grid
     */
    void addChar(char character, int x, int y);

    /**
     * Converts the current text to an {@link Emoji} grid, with the internal width and height.
     *
     * @param emojiManager The {@link DefaultEmojiManager}
     * @return The 2D array of emojis
     */
    Emoji[][] toEmoji(EmojiManager emojiManager);

    /**
     * Converts the current text to an {@link Emoji} grid, with the internal width and height, overriding the initial
     * parameter. The parameter is used so it won't need to make the array on its own.
     *
     * @param emojiManager The {@link DefaultEmojiManager}
     * @return The 2D array of emojis
     */
    Emoji[][] toEmoji(EmojiManager emojiManager, Emoji[][] initial);

    /**
     * Adds a newline with respecting line breaking, simulating the pressing "Enter" on a keyboard at given coordinates.
     *
     * @param x The X position of the grid
     * @param y The Y position of the grid
     */
    void newlineAt(int x, int y);

    /**
     * Adds an empty character line at the given Y position, moving all other lines downward, truncating the last line.
     *
     * @param y The Y position of the grid
     */
    void addEmpty(int y);

    /**
     * Adds the given char[] to the end of the content of a line.
     *
     * @param y The Y position of the grid
     * @param line The line to add
     */
    default void addAll(int y, char[] line) {
        throw new UnsupportedOperationException("This implementation does not support #addAll(int, char[])");
    }

    /**
     * Adds the given Character list to the end of the content of a line.
     *
     * @param y The Y position of the grid
     * @param line The line to add
     */
    default void addAll(int y, List<Character> line) {
        throw new UnsupportedOperationException("This implementation does not support #addAll(int, List<Character>)");
    }

    /**
     * Removes the character at the given coordinate, shifting all proceeding characters in the line.
     *
     * @param x The X position of the grid
     * @param y The Y position of the grid
     */
    void removeChar(int x, int y);
}
