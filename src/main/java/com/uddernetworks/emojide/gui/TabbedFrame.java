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

public class TabbedFrame extends DefaultEmojiContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(TabbedFrame.class);

    private KeyboardInputManager keyboardInputManager;
    private int activeTab = 0;
    private List<Tab> tabs = new ArrayList<>();

    public TabbedFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
        (this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager()).addListener(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        // Drawing the border without any tabs
        drawBorder(initial);
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
        addChild(to.component, 1, 1);
        clearCache();
        update();
        this.displayer.update();
    }

    private Tab getActive() {
        return this.tabs.get(this.activeTab);
    }

    public TabbedFrame addTab(String name, EmojiComponent component) {
        if (this.tabs.isEmpty()) addChild(component, 1, 1);
        this.tabs.add(new Tab(name, component));
        return this;
    }

    private Emoji[][] drawBorder(Emoji[][] rows) {
        for (int y = 1; y < rows.length - 1; y++) {
            rows[y][0] = StaticEmoji.LTABBED_FRAME;
            rows[y][rows[0].length - 1] = StaticEmoji.RTABBED_FRAME;
        }
        Arrays.fill(rows[0], 1, rows[0].length - 1, StaticEmoji.TTABBED_FRAME);
        Arrays.fill(rows[rows.length - 1], 1, rows[rows.length - 1].length - 1, StaticEmoji.BTABBED_FRAME);
        return rows;
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

        public String getName() {
            return name;
        }

        public EmojiComponent getComponent() {
            return component;
        }
    }
}
