package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CustomRenderedContainerFrame extends DefaultEmojiContainer {

    private List<Consumer<Emoji[][]>> renderers = new ArrayList<>();

    public CustomRenderedContainerFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        var initiala = new Emoji[][][] {super.render(initial)};
        renderers.forEach(t -> t.accept(initiala[0]));
        return initiala[0];
    }

    public CustomRenderedContainerFrame addRenderer(Consumer<Emoji[][]> renderer) {
        renderers.add(renderer);
        return this;
    }
}
