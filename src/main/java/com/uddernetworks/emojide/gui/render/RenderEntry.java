package com.uddernetworks.emojide.gui.render;

/**
 * An entry for something to be processed in the {@link RenderEngine}.
 */
public interface RenderEntry {

    /**
     * If the entry is an instance of {@link RenderAction}.
     *
     * @return If the entry is an action
     */
    boolean isAction();
}
