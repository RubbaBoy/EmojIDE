package com.uddernetworks.emojide.gui.components.styled;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;

import static com.uddernetworks.emojide.gui.components.styled.StyleUtils.renderInitialStyle;

/**
 * A {@link DefaultEmojiContainer} with the ability to have a border and background.
 */
public abstract class StyledEmojiContainer extends DefaultEmojiContainer {

    private Emoji border;
    private Emoji background;

    public StyledEmojiContainer(Displayer displayer, int width, int height) {
        super(displayer, width, height);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        return super.render(renderInitialStyle(this.background, this.border, this.width, this.height));
    }

    public Emoji getBorder() {
        return border;
    }

    public StyledEmojiContainer setBorder(Emoji border) {
        this.border = border;
        return this;
    }

    public Emoji getBackground() {
        return background;
    }

    public StyledEmojiContainer setBackground(Emoji background) {
        this.background = background;
        return this;
    }
}
