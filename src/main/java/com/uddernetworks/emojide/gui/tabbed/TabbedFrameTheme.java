package com.uddernetworks.emojide.gui.tabbed;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.components.theme.ThemeImplementor;

public interface TabbedFrameTheme extends ThemeImplementor {
    void settingOffset();
    void addingChildTab(EmojiComponent component);
    Emoji[][] drawBorder(Emoji[][] initial);
}
