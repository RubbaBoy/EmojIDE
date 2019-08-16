package com.uddernetworks.emojide.gui.components.output;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.gui.StaticTextFrame;

import java.util.Arrays;

public class DefaultOutputFrame implements OutputFrameTheme {

    private OutputFrame frame;

    public DefaultOutputFrame(OutputFrame frame) {
        this.frame = frame;
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        Arrays.fill(initial[0], StaticEmoji.CTABBED_FRAME);
        return initial;
    }

    @Override
    public void setOutput(StaticTextFrame output) {
        frame.addChild(output, 1, 1);
    }
}
