package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.DefaultEmojiManager;
import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TabbedFrame extends DefaultEmojiContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(TabbedFrame.class);

    private KeyboardInputManager keyboardInputManager;
    private int activeTab = 0;
    private List<Tab> tabs = new ArrayList<>();

    public TabbedFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height + 1); // Adds 2 due to header
        (this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager()).addListener(this);
        setOffset(1, 2);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        // Drawing the border without any tabs
        initial = drawBorder(initial);
        return super.render(initial);
    }

    private void onKeyPress(KeyPressEvent event) {
        if (this.keyboardInputManager.getState() == KeyboardInputManager.ActiveState.ALT) {
            if (event.isAlphanumeric()) return;
            if (event.getStaticEmoji() == StaticEmoji.RIGHT) {
                var from = getActive();
                if (++activeTab >= tabs.size()) activeTab = 0;
                switchToTab(from, getActive());
            } else if (event.getStaticEmoji() == StaticEmoji.LEFT) {
                var from = getActive();
                if (--activeTab < 0) activeTab = tabs.size() - 1;
                switchToTab(from, getActive());
            }
        }
    }

    private void switchToTab(Tab from, Tab to) {
        from.deactivate();
        removeChild(from.component);
        to.activate();
        addChild(to.component, 0, 0);
        clearCache();
        update();
        this.displayer.update();
    }

    private Tab getActive() {
        return this.tabs.get(this.activeTab);
    }

    public TabbedFrame addTab(String name, EmojiComponent component) {
        if (this.tabs.isEmpty()) addChild(component, 0, 0);
        this.tabs.add(new Tab(name, component));
        return this;
    }

    private Emoji[][] drawBorder(Emoji[][] rows) {
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
        return rows;
    }

    private Emoji[][] drawHeaders() {
        var thisWidth = width - 4;
        var upper = new ArrayList<Emoji>();
        var row = new ArrayList<Emoji>();

        var activeTab = getActive();
        for (Tab tab : tabs) {
            var active = tab.equals(activeTab);
            if (row.size() + 4 + tab.name.length() > thisWidth) break;

            row.add(active ? StaticEmoji.LTAB_SEPARATOR_SELECTED : StaticEmoji.LTAB_SEPARATOR);
            upper.add(StaticEmoji.TRANSPARENT);

            for (char cha : tab.name.toCharArray()) {
                row.add(this.emojiManager.getEmoji(String.valueOf((int) cha)));
                upper.add(active ? StaticEmoji.TTABBED_FRAME_SELECTED : StaticEmoji.TTABBED_FRAME);
            }

            row.add(active ? StaticEmoji.RTAB_SEPARATOR_SELECTED : StaticEmoji.RTAB_SEPARATOR);
            upper.add(StaticEmoji.TRANSPARENT);
        }

        for (int i = 0; i < thisWidth - row.size(); i++) {
            row.add(StaticEmoji.TTABBED_FRAME);
            upper.add(StaticEmoji.TRANSPARENT);
        }

        return Stream.of(upper, row).map(list -> list.toArray(Emoji[]::new)).collect(Collectors.toList()).toArray(Emoji[][]::new);
    }

    class Tab {
        private String name;
        private EmojiComponent component;

        public Tab(String name, EmojiComponent component) {
            this.name = name;
            this.component = component;
        }

        public void activate() {
            LOGGER.info("The tab {} is now active!", this.name);
            keyboardInputManager.addListener(this.component);
        }

        public void deactivate() {
            LOGGER.info("The tab {} is no longer active!", this.name);
            keyboardInputManager.removeListener(this.component);
        }
    }
}
