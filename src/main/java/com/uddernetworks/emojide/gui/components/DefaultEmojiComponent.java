package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;

import static com.uddernetworks.emojide.gui.components.ComponentUtils.getNullGrid;

/**
 * A superclass for every component that can be rendered on the screen, i.e. containers, text, etc.
 */
public abstract class DefaultEmojiComponent implements EmojiComponent {
    protected final Displayer displayer;
    protected final EmojiManager emojiManager;
    protected final int width;
    protected final int height;
    private boolean clearCache;
    private EmojiComponent parent;
    private Emoji[][] lastRender;

    public DefaultEmojiComponent(Displayer displayer, int width, int height) {
        this.displayer = displayer;
        this.emojiManager = displayer.getEmojiManager();
        this.width = width;
        this.height = height;
    }

    @Override
    public Emoji[][] getCachedRender() {
        if (!clearCache && this.lastRender != null) return this.lastRender;
        this.clearCache = false;
        return this.lastRender = render(getNullGrid(this.width, this.height));
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public EmojiComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(EmojiComponent parent) {
        this.parent = parent;
    }

    @Override
    public void clearCache() {
        this.clearCache = true;
        if (this.parent != null) this.parent.clearCache();
    }

    @Override
    public void update() {}

}
