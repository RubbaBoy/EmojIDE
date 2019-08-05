package com.uddernetworks.emojide.gui.render;

public class RenderBreakpoint implements RenderEntry {

    private Runnable runnable;

    public RenderBreakpoint(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();
    }

    @Override
    public boolean isAction() {
        return false;
    }
}
