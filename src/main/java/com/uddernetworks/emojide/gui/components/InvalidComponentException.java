package com.uddernetworks.emojide.gui.components;

/**
 * Called when a component has invalid dimensions or is in an otherwise un-renderable state.
 */
public class InvalidComponentException extends RuntimeException {
    public InvalidComponentException(String message) {
        super(message);
    }
}
