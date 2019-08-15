package com.uddernetworks.emojide.discord.font;

public interface FontManager {

    /**
     * Gets the active {@link Font} being used
     *
     * @return The active {@link Font}
     */
    Font getActive();

    /**
     * Sets the given {@link Font} to be active.
     *
     * @param active The {@link Font} to be active
     */
    void setActive(Font active);
}
