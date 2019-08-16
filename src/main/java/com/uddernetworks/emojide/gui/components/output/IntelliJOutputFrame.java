package com.uddernetworks.emojide.gui.components.output;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.gui.StaticTextFrame;

public class IntelliJOutputFrame implements OutputFrameTheme {

    private OutputFrame frame;

    public IntelliJOutputFrame(OutputFrame frame) {
        this.frame = frame;
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        return initial;
    }

    @Override
    public void setOutput(StaticTextFrame output) {
        frame.addChild(output, 1, 1);
    }
}
