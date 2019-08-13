package com.uddernetworks.emojide.gui.components;

import java.util.List;

/**
 * A component that is rendered by a {@link Displayer} upon invoking {@link #getCachedRender()}. A container can also
 * render other children {@link EmojiComponent}s at arbitrary positions.
 */
public interface EmojiContainer extends EmojiComponent {

    /**
     * Adds a child {@link EmojiComponent} to render at the given coordinates.
     *
     * @param component The {@link EmojiComponent} to render
     * @param x         The relative X coordinate to render at
     * @param y         The relative Y coordinate to render at
     * @return The current {@link EmojiContainer} for builder-style invocations
     */
    EmojiContainer addChild(EmojiComponent component, int x, int y);

    /**
     * Removes the given {@link EmojiComponent} as a child.
     *
     * @param component The {@link EmojiComponent} to remove
     */
    void removeChild(EmojiComponent component);

    /**
     * Gets an immutable list of all {@link PositionedComponent} children.
     *
     * @return An immutable list of all children
     */
    List<PositionedComponent> getChildren();

    /**
     * Positions an existing {@link EmojiComponent} to the given coordinates.
     *
     * @param component The {@link EmojiComponent} to mutate the location of
     * @param x         The relative X coordinate to render at
     * @param y         The relative Y coordinate to render at
     * @return The current {@link EmojiContainer} for builder-style invocations
     */
    EmojiContainer positionChild(EmojiComponent component, int x, int y);

    /**
     * Sets the offset elements will be placed at internally.
     *
     * @param x The X offset
     * @param y The Y offset
     */
    void setOffset(int x, int y);

    /**
     * Gets the X offset set by {@link #setOffset(int, int)};
     *
     * @return The X offset
     */
    int getXOffset();

    /**
     * Gets the Y offset set by {@link #setOffset(int, int)};
     *
     * @return The Y offset
     */
    int getYOffset();
}
