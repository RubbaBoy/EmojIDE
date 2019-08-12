package com.uddernetworks.emojide.event;

public abstract class Cancellable extends Event {

    private boolean cancel;

    /**
     * If the event has been cancelled, it will go through no other handlers.
     *
     * @return If the event has been cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets if the event should be cancelled or not, and if no other event handlers should receive it.
     *
     * @param cancel If the event is cancelled
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
