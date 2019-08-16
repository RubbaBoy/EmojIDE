package com.uddernetworks.emojide.gui.components.output;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.gui.StaticTextFrame;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.theme.ThemeDependantRendering;

public class OutputFrame extends DefaultEmojiContainer {

    private OutputFrameTheme theme;

    public OutputFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
        theme = ThemeDependantRendering.getImplementation(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        return super.render(theme.render(initial));
    }

    public OutputFrame setOutput(StaticTextFrame output) {
        theme.setOutput(output);
        return this;
    }
}
