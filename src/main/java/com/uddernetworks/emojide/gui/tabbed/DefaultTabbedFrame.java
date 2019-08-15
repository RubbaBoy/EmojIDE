package com.uddernetworks.emojide.gui.tabbed;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.tabs.Tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultTabbedFrame implements TabbedFrameTheme {

    private TabbedFrame frame;

    public DefaultTabbedFrame(TabbedFrame frame) {
        this.frame = frame;
    }

    @Override
    public void settingOffset() {
        frame.setOffset(1, 2);
    }

    @Override
    public void addingChildTab(EmojiComponent component) {
        frame.addChild(component, 0, 0);
    }

    @Override
    public Emoji[][] drawBorder(Emoji[][] rows) {
        for (int y = 2; y < rows.length - 1; y++) {
            rows[y][0] = StaticEmoji.LTABBED_FRAME;
            rows[y][rows[0].length - 1] = StaticEmoji.RTABBED_FRAME;
        }

        Arrays.fill(rows[0], 1, rows[0].length - 1, StaticEmoji.TRANSPARENT);
        Arrays.fill(rows[1], 1, rows[1].length - 1, StaticEmoji.TTABBED_FRAME);

        var headers = drawHeaders();
        for (int i = 0; i < headers.length; i++) {
            System.arraycopy(headers[i], 0, rows[i], 2, headers[i].length);
        }

        Arrays.fill(rows[rows.length - 1], 1, rows[rows.length - 1].length - 1, StaticEmoji.BTABBED_FRAME);

        // Corners
        rows[1][0] = StaticEmoji.BR_FRAME;
        rows[1][rows[0].length - 1] = StaticEmoji.BL_FRAME;
        rows[rows.length - 1][0] = StaticEmoji.TR_FRAME;
        rows[rows.length - 1][rows[0].length - 1] = StaticEmoji.TL_FRAME;
        return rows;
    }

    private Emoji[][] drawHeaders() {
        var thisWidth = frame.getWidth() - 4;
        var upper = new ArrayList<Emoji>();
        var row = new ArrayList<Emoji>();

        var activeTab = frame.getActive();
        for (Tab tab : frame.tabs) {
            var active = tab.equals(activeTab);
            if (row.size() + 4 + tab.getName().length() > thisWidth) break;

            row.add(active ? StaticEmoji.LTAB_SEPARATOR_SELECTED : StaticEmoji.LTAB_SEPARATOR);
            upper.add(active ? StaticEmoji.LTAB_CORNER_SELECTED : StaticEmoji.BR_FRAME);

            for (char cha : tab.getName().toCharArray()) {
                row.add(frame.getEmojiManager().getTextEmoji(cha));
                upper.add(active ? StaticEmoji.TTABBED_FRAME_SELECTED : StaticEmoji.TTABBED_FRAME);
            }

            row.add(active ? StaticEmoji.RTAB_SEPARATOR_SELECTED : StaticEmoji.RTAB_SEPARATOR);
            upper.add(active ? StaticEmoji.RTAB_CORNER_SELECTED : StaticEmoji.BL_FRAME);
        }

        for (int i = 0; i < thisWidth - row.size(); i++) {
            row.add(StaticEmoji.TTABBED_FRAME);
            upper.add(StaticEmoji.TRANSPARENT);
        }

        return Stream.of(upper, row).map(list -> list.toArray(Emoji[]::new)).collect(Collectors.toList()).toArray(Emoji[][]::new);
    }
}
