package com.uddernetworks.emojide.gui.tabbed;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.Group;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.components.theme.ThemeDependantRendering;
import com.uddernetworks.emojide.gui.tabs.Tab;
import com.uddernetworks.emojide.gui.theme.Theme;

import static com.uddernetworks.emojide.gui.tabbed.TabbedFrameConstants.AVAILABLE_TEXT_HEIGHT;

public class IntelliJTabbedFrame implements TabbedFrameTheme {

    static {
        ThemeDependantRendering.setThemeConstant(TabbedFrame.class, Theme.INTELLIJ, AVAILABLE_TEXT_HEIGHT, 11);
    }

    private TabbedFrame frame;

    public IntelliJTabbedFrame(TabbedFrame frame) {
        this.frame = frame;
    }

    @Override
    public void settingOffset() {
        frame.setOffset(1, 4);
    }

    @Override
    public void addingChildTab(EmojiComponent component) {
        frame.addChild(component, 0, 0);
    }

    @Override
    public Emoji[][] drawBorder(Emoji[][] rows) {
        Group.INTELLIJ_BASE.getEmojis().forEach(emoji -> {
            var split = emoji.getName().split("j");
            rows[Integer.parseInt(split[1])][Integer.parseInt(split[0])] = emoji;
        });

        var headers = drawHeaders();
        for (int y = 0; y < headers.length; y++) {

            var thisRow = headers[y];
            var copyingTo = rows[y + 1];
            for (int x = 0; x < thisRow.length; x++) {
                var thisEmoji = thisRow[x];
                if (thisEmoji != null && thisEmoji != StaticEmoji.TRANSPARENT) copyingTo[x] = thisEmoji;
            }

            rows[y + 1] = copyingTo;
        }
        return rows;
    }

    private Emoji[][] drawHeaders() {
        var top = new Emoji[frame.getWidth()];
        var row = new Emoji[frame.getWidth()];
        var bottom = new Emoji[frame.getWidth()];

        var activeFont = frame.getEmojIDE().getFontManager().getActive().ordinal();
        var unselectedFontUsing = new String[]{"t", "ft"}[activeFont];
        var selectedFontUsing = new String[]{"e", "fe"}[activeFont];

        int emojiIndex = 0;
        var activeTab = frame.getActive();
        for (Tab tab : frame.tabs) {
            var active = tab.equals(activeTab);

            if (active) {
                top[emojiIndex] = StaticEmoji.IJ_SELECTED_TOP_LEFT;
                row[emojiIndex] = StaticEmoji.IJ_SELECTED_MIDDLE_LEFT;
                bottom[emojiIndex] = StaticEmoji.IJ_SELECTED_BOTTOM_LEFT;
            }
            emojiIndex++;

            for (char cha : tab.getName().toCharArray()) {
                var prefix = active ? selectedFontUsing : unselectedFontUsing;
                row[emojiIndex] = frame.getEmojiManager().getEmoji(prefix + (int) cha);

                if (active) {
                    top[emojiIndex] = StaticEmoji.IJ_SELECTED_TOP;
                    bottom[emojiIndex] = StaticEmoji.IJ_SELECTED_BOTTOM;
                }
                emojiIndex++;
            }

            if (active) {
                top[emojiIndex] = StaticEmoji.IJ_SELECTED_TOP_RIGHT;
                row[emojiIndex] = StaticEmoji.IJ_SELECTED_MIDDLE_RIGHT;
                bottom[emojiIndex] = StaticEmoji.IJ_SELECTED_BOTTOM_RIGHT;
            }
            emojiIndex++;
        }

        return new Emoji[][] {top, row, bottom};
    }
}
