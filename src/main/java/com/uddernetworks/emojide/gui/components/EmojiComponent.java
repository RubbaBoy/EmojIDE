package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.IconEmote;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.entities.Emote;

import java.util.Arrays;

/**
 * A superclass for every component that can be rendered on the screen, i.e. containers, text, etc.
 */
public abstract class EmojiComponent {
    protected final Displayer displayer;
    protected final EmojiManager emojiManager;
    protected final int width;
    protected final int height;
    private boolean clearCache;
    private EmojiComponent parent;
    private Emoji[][] lastRender;

    public EmojiComponent(Displayer displayer, int width, int height) {
        this.displayer = displayer;
        this.emojiManager = displayer.getEmojiManager();
        this.width = width;
        this.height = height;
    }

    /**
     * Gets a 2D array of Emojis, [0].length being `height` and [][0].length being `width` set in the constructor.
     *
     * @param initial The initial Emoji grid to build on top of
     * @return The emojis to render
     */
    public abstract Emoji[][] render(Emoji[][] initial);

    /**
     * Gets the rendered view of the component, or the most recent cached version if applicable. This is preferred
     * over {@link #render(Emoji[][])} due to caching.
     *
     * @return The emojis to render
     */
    public Emoji[][] getCachedRender() {
        if (!clearCache && this.lastRender != null) return this.lastRender;
        this.clearCache = false;
        return this.lastRender = render(getNullGrid(this.width, this.height));
    }

    /**
     * Gets the width of the component.
     *
     * @return The width of the component.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the component.
     *
     * @return The height of the component.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the parent of the component.
     *
     * @return The parent component
     */
    public EmojiComponent getParent() {
        return parent;
    }

    /**
     * Sets the parent of the component.
     *
     * @param parent The parent to set
     */
    public void setParent(EmojiComponent parent) {
        this.parent = parent;
    }

    /**
     * If the element should be updated during the next render. If the component has children that have cleared caches,
     * it's vital that all parents have cleared caches as well.
     */
    public void clearCache() {
        this.clearCache = true;
        if (this.parent != null) this.parent.clearCache();
    }

    /**
     * Updates the current component if {@link #clearCache()} has been invoked. All children, children's children,
     * etc. will be updated forcibly. This could be expensive.
     */
    public void update() {}

    public static Emoji[][] getNullGrid(int width, int height) {
        var rows = new Emoji[height][];
        for (int i = 0; i < height; i++) rows[i] = new Emoji[width];
        return rows;
    }

    public static Emoji[][] getEmptyGrid(EmojiManager emojiManager, int width, int height) {
        return getEmptyGrid(emojiManager.getEmoji("discord"), width, height);
    }

    public static Emoji[][] getEmptyGrid(Emoji emoji, int width, int height) {
        if (emoji == null) return getNullGrid(width, height);
        var rows = new Emoji[height][];
        for (int y = 0; y < height; y++) {
            rows[y] = new Emoji[width];
            Arrays.fill(rows[y], emoji);
        }
        return rows;
    }
}
