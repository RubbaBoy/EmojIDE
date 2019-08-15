package com.uddernetworks.emojide.gui.tabbed;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.event.Handler;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.components.theme.ThemeDependantRendering;
import com.uddernetworks.emojide.gui.tabs.Tab;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.KeyboardRaisable;
import com.uddernetworks.emojide.main.EmojIDE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TabbedFrame extends DefaultEmojiContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(TabbedFrame.class);

    private TabbedFrameTheme theme;
    private KeyboardInputManager keyboardInputManager;
    int activeTab = 0;
    List<Tab> tabs = new ArrayList<>();

    public TabbedFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height); // Adds 2 due to header
        theme = ThemeDependantRendering.getImplementation(this);
        this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager();
        KeyboardRaisable.get().addListener(this);
        theme.settingOffset();
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
//        return theme.drawBorder(initial);
        return super.render(theme.drawBorder(initial));
    }

    @Handler(event = "keyboard")
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

    public void selectTab(EmojiComponent component) {
        if (getActive().getComponent().equals(component)) return;
        tabs.stream().filter(tab -> tab.getComponent().equals(component)).findFirst().ifPresent(to -> switchToTab(getActive(), to));
    }

    public Tab getFirst() {
        return tabs.get(0);
    }

    public void removeTab(Tab tab) {
        var active = getActive();
        tabs.remove(activeTab);
        if (active.equals(tab)) switchToTab(active, getFirst());
        refresh();
    }

    private void switchToTab(Tab from, Tab to) {
        for (int i = 0; i < tabs.size(); i++) if (tabs.get(i).equals(to)) activeTab = i;
        from.deactivate();
        removeChild(from.getComponent());
        from.getComponent().clearCache();
        to.activate();
        theme.addingChildTab(to.getComponent());
        to.getComponent().clearCache();
        refresh();
    }

    public Tab getActive() {
        return this.tabs.get(this.activeTab);
    }

    public TabbedFrame addTab(String name, EmojiComponent component) {
        addTab(name, component, false);
        return this;
    }

    public TabbedFrame addTab(String name, EmojiComponent component, boolean active) {
        if (this.tabs.isEmpty()) theme.addingChildTab(component);
        this.tabs.add(new Tab(name, component));
        if (active) {
            LOGGER.info("{} is active, tabs size: {} name of active: {}", name, tabs.size(), getActive().getName());
            selectTab(component);
        }
        return this;
    }

    public void refresh() {
        if (displayer.isDisplaying()) {
            clearCache();
            update();
            this.displayer.update();
        }
    }

    public EmojiManager getEmojiManager() {
        return emojiManager;
    }

    public EmojIDE getEmojIDE() {
        return displayer.getEmojIDE();
    }
}
