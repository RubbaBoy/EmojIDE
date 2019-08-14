package com.uddernetworks.emojide.gui.tabs;

import com.uddernetworks.emojide.gui.components.EmojiComponent;
import com.uddernetworks.emojide.gui.components.EmojiContainer;
import com.uddernetworks.emojide.keyboard.KeyboardRaisable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tab {

    private static Logger LOGGER = LoggerFactory.getLogger(Tab.class);

    private String name;
    private EmojiComponent component;

    public Tab(String name, EmojiComponent component) {
        this.name = name;
        this.component = component;
    }

    public void activate() {
        LOGGER.info("The tab {} is now active!", name);
        if (component instanceof EmojiContainer) {
            var children = ((EmojiContainer) component).getChildren();
            if (children.isEmpty()) {
                KeyboardRaisable.get().addListener(component);
                return;
            }

            KeyboardRaisable.get().addListener(children.get(0).getComponent());
        } else {
            KeyboardRaisable.get().addListener(component);
        }
    }

    public void deactivate() {
        LOGGER.info("The tab {} is no longer active!", name);
        if (component instanceof EmojiContainer) {
            var children = ((EmojiContainer) component).getChildren();
            if (children.isEmpty()) {
                KeyboardRaisable.get().removeListener(component);
                return;
            }

            KeyboardRaisable.get().removeListener(children.get(0).getComponent());
        } else {
            KeyboardRaisable.get().removeListener(component);
        }
    }

    public String getName() {
        return name;
    }

    public EmojiComponent getComponent() {
        return component;
    }
}
