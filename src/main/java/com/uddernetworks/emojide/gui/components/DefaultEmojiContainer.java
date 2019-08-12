package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.Emoji;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class DefaultEmojiContainer extends DefaultEmojiComponent implements EmojiContainer {

    protected int xOffset = 0;
    protected int yOffset = 0;
    protected List<PositionedComponent> positionedComponents = new ArrayList<>();

    public DefaultEmojiContainer(Displayer displayer, int width, int height) {
        super(displayer, width, height);
    }

    @Override
    public EmojiContainer addChild(EmojiComponent component, int x, int y) {
        component.setParent(this);
        this.positionedComponents.add(new DefaultPositionedComponent(component, x, y));
        return this;
    }

    @Override
    public void removeChild(EmojiComponent component) {
        getPositioned(component).ifPresent(positioned -> this.positionedComponents.remove(positioned));
    }

    @Override
    public List<PositionedComponent> getChildren() {
        return List.copyOf(this.positionedComponents);
    }

    @Override
    public EmojiContainer positionChild(EmojiComponent component, int x, int y) {
        getPositioned(component).ifPresent(positioned -> positioned.setPosition(x, y));
        clearCache();
        update();
        return this;
    }

    @Override
    public void setOffset(int x, int y) {
        this.xOffset = x;
        this.yOffset = y;
    }

    @Override
    public int getXOffset() {
        return this.xOffset;
    }

    @Override
    public int getYOffset() {
        return this.yOffset;
    }

    private Optional<PositionedComponent> getPositioned(EmojiComponent component) {
        return this.positionedComponents.stream().filter(positionedComponent -> positionedComponent.getComponent().equals(component)).findFirst();
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        this.positionedComponents.forEach(positioned -> drawTo(positioned, initial));
        return initial;
    }

    private void drawTo(PositionedComponent positioned, Emoji[][] rows) {
        var insertX = positioned.getX() + this.xOffset;
        var insertY = positioned.getY() + this.yOffset;
        var component = positioned.getComponent();
        var inserting = component.getCachedRender(); // The component

        for (int y = 0; y < component.getHeight(); y++) {
            System.arraycopy(inserting[y], 0, rows[y + insertY], insertX, component.getWidth());
        }
    }

    @Override
    public void update() {
        this.positionedComponents.forEach(positioned -> {
            var component = positioned.getComponent();
            component.clearCache();
            component.update();
        });
    }
}
