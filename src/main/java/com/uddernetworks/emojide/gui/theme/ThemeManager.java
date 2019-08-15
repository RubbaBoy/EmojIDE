package com.uddernetworks.emojide.gui.theme;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ThemeManager {

    /**
     * Gets the active {@link Theme} for the IDE.
     *
     * @return The active {@link Theme}
     */
    Theme getActive();

    /**
     * Sets the given {@link Theme} to active.
     *
     * @param active The {@link Theme} to activate
     */
    void setActive(Theme active);

    /**
     * Executes code depending on what the active theme is; only one of the runnables will be invoked.
     *
     * @param def The runnable to invoke if {@link Theme#DEFAULT} is active
     * @param intellij The runnable to invoke if {@link Theme#INTELLIJ} is active
     */
    void forTheme(Runnable def, Runnable intellij);

    /**
     * Executes a supplier to get avalid depending on what the active theme is; only one of the suppliers will be
     * invoked.
     *
     * @param def The supplier to invoke if {@link Theme#DEFAULT} is active
     * @param intellij The supplier to invoke if {@link Theme#INTELLIJ} is active
     * @return The value from one of the suppliers
     */
    <T> T getForTheme(Supplier<T> def, Supplier<T> intellij);

}
