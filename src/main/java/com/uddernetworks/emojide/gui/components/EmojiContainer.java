package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.Emoji;
import net.dv8tion.jda.api.entities.Emote;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class EmojiContainer extends EmojiComponent {

    protected List<PositionedComponent> positionedComponents = new ArrayList<>();

    public EmojiContainer(Displayer displayer, int width, int height) {
        super(displayer, width, height);
    }

    public EmojiComponent addChild(EmojiComponent component, int x, int y) {
        this.positionedComponents.add(new PositionedComponent(component, x, y));
        return component;
    }

    public void removeChild(EmojiComponent component) {
        getPositioned(component).ifPresent(positioned -> this.positionedComponents.remove(positioned));
    }

    public EmojiComponent positionChild(EmojiComponent component, int x, int y) {
        getPositioned(component).ifPresent(positioned -> positioned.setPosition(x, y));
        clearCache();
        update();
        return component;
    }

    private Optional<PositionedComponent> getPositioned(EmojiComponent component) {
        return this.positionedComponents.stream().filter(positionedComponent -> positionedComponent.getComponent().equals(component)).findFirst();
    }

    @Override
    public Emoji[][] render() {
        var rows = new Emoji[height][];
        for (int i = 0; i < height; i++) rows[i] = new Emoji[width];
        this.positionedComponents.forEach(positioned -> drawTo(positioned, rows));
        return rows;
    }

    protected void drawTo(PositionedComponent positioned, Emoji[][] rows) {
        var insertX = positioned.getX();
        var component = positioned.getComponent();
        var inserting = component.getCachedRender();

        for (int y = 0; y < component.height; y++) {
            System.arraycopy(inserting[y], 0, rows[y], insertX, component.width);
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
