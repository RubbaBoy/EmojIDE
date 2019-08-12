package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.event.EventRaiser;
import com.uddernetworks.emojide.event.Raisable;

public class KeyboardRaisable extends Raisable<KeyPressEvent> {
    public KeyboardRaisable() {
        super("keyboard", KeyPressEvent.class);
    }

    public static KeyboardRaisable get() {
        return (KeyboardRaisable) EventRaiser.getRaisable("keyboard");
    }
}
