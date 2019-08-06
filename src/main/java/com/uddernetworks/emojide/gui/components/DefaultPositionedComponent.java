package com.uddernetworks.emojide.gui.components;

public class DefaultPositionedComponent implements PositionedComponent {

    private final EmojiComponent component;

    private int x;
    private int y;

    public DefaultPositionedComponent(EmojiComponent component, int x, int y) {
        this.component = component;
        this.x = x;
        this.y = y;
    }

    @Override
    public EmojiComponent getComponent() {
        return component;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}