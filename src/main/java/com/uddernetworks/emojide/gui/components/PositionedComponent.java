package com.uddernetworks.emojide.gui.components;

/**
 * An object to store an {@link EmojiComponent}'s coordinates among whatever container using it.
 */
public interface PositionedComponent {

    /**
     * Gets the {@link EmojiComponent} stored.
     *
     * @return The {@link EmojiComponent} stored
     */
    EmojiComponent getComponent();

    /**
     * Sets the position of the component.
     *
     * @param x The X position
     * @param y The Y position
     */
    void setPosition(int x, int y);

    /**
     * Gets the X coordinate of the component.
     *
     * @return The X coordinate of the component
     */
    int getX();

    /**
     * Sets the X coordinate of the component.
     *
     * @param x The X coordinate to set
     */
    void setX(int x);

    /**
     * Gets the Y coordinate of the component.
     *
     * @return The Y coordinate of the component
     */
    int getY();

    /**
     * Sets the Y coordinate of the component.
     *
     * @param y The Y coordinate to set
     */
    void setY(int y);
}
