package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.emoji.Emoji;

/**
 * A component that is rendered by a {@link Displayer} upon invoking {@link #getCachedRender()}.
 */
public interface EmojiComponent {

    /**
     * Gets a 2D array of Emojis, [0].length being `height` and [][0].length being `width` set in the constructor.
     *
     * @param initial The initial Emoji grid to build on top of
     * @return The emojis to render
     */
    Emoji[][] render(Emoji[][] initial);

    /**
     * Gets the rendered view of the component, or the most recent cached version if applicable. This is preferred
     * over {@link #render(Emoji[][])} due to caching.
     *
     * @return The emojis to render
     */
    Emoji[][] getCachedRender();

    /**
     * Gets the width of the component.
     *
     * @return The width of the component.
     */
    int getWidth();

    /**
     * Gets the height of the component.
     *
     * @return The height of the component.
     */
    int getHeight();

    /**
     * Gets the parent of the component.
     *
     * @return The parent component
     */
    EmojiComponent getParent();

    /**
     * Sets the parent of the component.
     *
     * @param parent The parent to set
     */
    void setParent(EmojiComponent parent);

    /**
     * If the element should be updated during the next render. If the component has children that have cleared caches,
     * it's vital that all parents have cleared caches as well.
     */
    void clearCache();

    /**
     * Updates the current component if {@link #clearCache()} has been invoked. All children, children's children,
     * etc. will be updated forcibly. This could be expensive.
     */
    void update();
}
