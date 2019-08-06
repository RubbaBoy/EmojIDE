package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;

/**
 * An empty {@link StyledEmojiComponent} containing nothing. USed for setting borders, backgrounds, or other styled data.
 */
public class EmptyFrame extends StyledEmojiComponent {
    public EmptyFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
    }
}
