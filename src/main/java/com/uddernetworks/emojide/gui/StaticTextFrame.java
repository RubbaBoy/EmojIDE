package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticTextFrame extends StyledEmojiComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(StaticTextFrame.class);

    private String text;

    public StaticTextFrame(Displayer displayer, int width, int height) {
        this(displayer, width, height, "");
    }

    public StaticTextFrame(Displayer displayer, int width, int height, String text) {
        super(displayer, width, height);
        this.text = text;
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        var wrapped = getWrapped();
        var loopTo = Math.min(initial.length, wrapped.length);
        for (int y = 0; y < loopTo; y++) {
            var line = wrapped[y].toCharArray();
            var emojiLine = initial[y];
            for (int x = 0; x < line.length; x++) {
                emojiLine[x] = this.emojiManager.getEmoji(String.valueOf((int) line[x]));
            }
            initial[y] = emojiLine;
        }
        return initial;
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
