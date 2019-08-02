package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiContainer;

public class WindowFrame extends StyledEmojiContainer {

    public WindowFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
        setBorder(this.emojiManager.getEmoji("red"));
    }

}
