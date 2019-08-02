package com.uddernetworks.emojide.gui.components;

public class PositionedComponent {

    private final EmojiComponent component;

    private int x;
    private int y;

    public PositionedComponent(EmojiComponent component, int x, int y) {
        this.component = component;
        this.x = x;
        this.y = y;
    }

    public EmojiComponent getComponent() {
        return component;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}