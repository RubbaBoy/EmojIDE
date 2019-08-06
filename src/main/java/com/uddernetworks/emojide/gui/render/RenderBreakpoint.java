package com.uddernetworks.emojide.gui.render;

/**
 * Stores a {@link Runnable} to run when the queue reaches it.
 */
public class RenderBreakpoint implements RenderEntry {

    private Runnable runnable;

    public RenderBreakpoint(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * Runs the runnable
     */
    public void run() {
        runnable.run();
    }

    @Override
    public boolean isAction() {
        return false;
    }
}
