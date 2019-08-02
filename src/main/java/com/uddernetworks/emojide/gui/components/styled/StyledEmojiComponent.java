package com.uddernetworks.emojide.gui.components.styled;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiComponent;

import static com.uddernetworks.emojide.gui.components.styled.StyleUtils.drawBorder;
import static com.uddernetworks.emojide.gui.components.styled.StyleUtils.renderInitialStyle;

public abstract class StyledEmojiComponent extends EmojiComponent {

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
