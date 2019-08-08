package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.PositionedComponent;
import com.uddernetworks.emojide.ide.LanguageHighlighter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HighlightedTextFrame extends EditableDynamicTextFrame {

    private static Logger LOGGER = LoggerFactory.getLogger(HighlightedTextFrame.class);

    public HighlightedTextFrame(Displayer displayer, int width, int height, String text) {
        super(displayer, width, height);
        textBlock.setText(text);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        initial = super.render(initial);

        var highlighter = new LanguageHighlighter();
        drawHighlighted(initial, highlighter.highlightText(emojiManager, textBlock));
        initial[cursorY - scrollY][Math.min(initial[0].length - 1, cursorX) - scrollX] = StaticEmoji.CURSOR;

        return initial;
    }

    private void drawHighlighted(Emoji[][] initial, Emoji[][] highlighted) {
        var insertX = 0;
        var insertY = 0;

        for (int y = 0; y < highlighted.length; y++) {
            System.arraycopy(highlighted[y], 0, initial[y + insertY], insertX, highlighted[0].length);
        }
    }
}
