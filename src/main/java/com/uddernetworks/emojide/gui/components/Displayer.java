package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.DefaultEmojiManager;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.entities.TextChannel;

public interface Displayer {
    /**
     * Sets the child being displayed by the {@link Displayer} without invoking {@link #update()}.
     *
     * @param component The child to render
     */
    void setChild(EmojiComponent component);

    /**
     * Sets the child being displayed by the {@link Displayer} with invoking {@link #update()} if autoUpdate is true.
     *
     * @param component  The child to render
     * @param autoUpdate If the {@link #update()} method should be invoked immediately after
     */
    void setChild(EmojiComponent component, boolean autoUpdate);

    /**
     * Gets the child being displayed by the {@link Displayer}.
     *
     * @return The child being displayed
     */
    EmojiComponent getChild();

    /**
     * Re-renders the changed lines by invoking {@link EmojiComponent#getCachedRender()} and either sending or editing
     * (only changed) lines rendered.
     */
    void update();

    /**
     * Removes all rendered messages.
     *
     * @param channel The {@link TextChannel} the messages are in
     */
    void stop(TextChannel channel);

    /**
     * Gets the {@link EmojIDE}.
     *
     * @return The {@link EmojIDE}
     */
    EmojIDE getEmojIDE();

    /**
     * Gets the {@link DefaultEmojiManager}
     *
     * @return The {@link DefaultEmojiManager}
     */
    EmojiManager getEmojiManager();
}
