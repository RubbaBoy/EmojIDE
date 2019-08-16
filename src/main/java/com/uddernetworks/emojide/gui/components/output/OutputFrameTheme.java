package com.uddernetworks.emojide.gui.components.output;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.gui.StaticTextFrame;
import com.uddernetworks.emojide.gui.components.theme.ThemeImplementor;

public interface OutputFrameTheme extends ThemeImplementor {
    Emoji[][] render(Emoji[][] initial);
    void setOutput(StaticTextFrame output);
}
