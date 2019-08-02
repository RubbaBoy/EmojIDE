package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class StaticTextFrame extends EmojiComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(StaticTextFrame.class);

    private String text;

    public StaticTextFrame(Displayer displayer, int width, int height) {
        this(displayer, width, height, "");
    }

    public StaticTextFrame(Displayer displayer, int width, int height, String text) {
        super(displayer, width, height);
    }

    @Override
    public Emoji[][] render() {
        var emojiManager = this.displayer.getEmojiManager();
        var empty = getEmptyGrid(emojiManager, this.width, this.height);
        var wrapped = getWrapped();
        System.out.println(Arrays.toString(wrapped));
        LOGGER.info(Arrays.toString(wrapped));
        var loopTo = Math.min(empty.length, wrapped.length);
        for (int y = 0; y < loopTo; y++) {
            System.out.println("y = " + y);
            var line = wrapped[y].toCharArray();
            var emojiLine = empty[y];
            for (int x = 0; x < line.length; x++) {
                emojiLine[x] = emojiManager.getEmoji(String.valueOf((int) line[x]));
            }
            empty[y] = emojiLine;
        }
        return empty;
    }

    private String[] getWrapped() {
        return WordUtils.wrap(this.text, this.width, "\n", true).split("\n");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
