package com.uddernetworks.emojide.gui.components.styled;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.gui.components.DefaultEmojiComponent;
import com.uddernetworks.emojide.gui.components.Displayer;

import static com.uddernetworks.emojide.gui.components.styled.StyleUtils.renderInitialStyle;

/**
 * A {@link DefaultEmojiComponent} with the ability to have a border and background.
 */
public abstract class StyledEmojiComponent extends DefaultEmojiComponent {

    private Emoji border;
    private Emoji background;

    public StyledEmojiComponent(Displayer displayer, int width, int height) {
        super(displayer, width, height);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        return renderInitialStyle(this.background, this.border, this.width, this.height);
    }

    public Emoji getBorder() {
        return border;
    }

    public StyledEmojiComponent setBorder(Emoji border) {
        this.border = border;
        return this;
    }

    public Emoji getBackground() {
        return background;
    }

    public StyledEmojiComponent setBackground(Emoji background) {
        this.background = background;
        return this;
    }
}
