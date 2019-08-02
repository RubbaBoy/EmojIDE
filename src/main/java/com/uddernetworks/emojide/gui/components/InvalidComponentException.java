package com.uddernetworks.emojide.gui.components;

public class InvalidComponentException extends RuntimeException {
    public InvalidComponentException() {
    }

    public InvalidComponentException(String message) {
        super(message);
    }
}
