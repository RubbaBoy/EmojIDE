package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class DefaultEmojiContainer extends DefaultEmojiComponent implements EmojiContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultEmojiContainer.class);

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
            var componentSrc = inserting[y];

            var componentName = component.getClass().getSimpleName();
            var thisName = getClass().getSimpleName();
            if (y + insertY >= rows.length) {
                LOGGER.error("Error while rendering {} into {}. An overflow of {}e has occurred vertically. Here are the known details:\n" +
                                "==== Parent ({}) ====\n" +
                                "Width: {}\n" +
                                "Height: {}\n" +
                                "Inner offsets: ({}, {})\n" +
                                "==== Child ({}) ====\n" +
                                "Width: {}\n" +
                                "Height: {}\n" +
                                "Position: ({}, {})\n", componentName, thisName, rows.length - (y + insertY) + 1,
                        thisName, getWidth(), getHeight(), xOffset, yOffset,
                        componentName, component.getWidth(), component.getHeight(), positioned.getX(), positioned.getY());
                continue;
            }

            var dest = rows[y + insertY];

            var endingSrcPos = insertX + component.getWidth(); // The length that the dest must be for it to be inserted
            if (endingSrcPos > dest.length) {
                LOGGER.error("Error while rendering row {} of {} into {}. An overflow of {}e has occurred horizontally. Here are the known details:\n" +
                        "==== Parent ({}) ====\n" +
                        "Width: {}\n" +
                        "Height: {}\n" +
                        "Inner offsets: ({}, {})\n" +
                        "==== Child ({}) ====\n" +
                        "Width: {}\n" +
                        "Height: {}\n" +
                        "Position: ({}, {})\n", y, componentName, thisName, endingSrcPos - dest.length,
                        thisName, getWidth(), getHeight(), xOffset, yOffset,
                        componentName, component.getWidth(), component.getHeight(), positioned.getX(), positioned.getY());
                var redFill = new Emoji[dest.length - insertX];
                Arrays.fill(redFill, StaticEmoji.RED);
                System.arraycopy(redFill, 0, dest, insertX, redFill.length);
                continue;
            }
            System.arraycopy(componentSrc, 0, dest, insertX, component.getWidth());
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
