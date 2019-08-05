package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;
import com.uddernetworks.emojide.image.PreloadedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Discord's rate limiting kills this, it should not be used for any purpose.
 */
@Deprecated
public class ImageFrame extends StyledEmojiComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageFrame.class);

    private PreloadedImage image;

    public ImageFrame(Displayer displayer, PreloadedImage image) {
        super(displayer, image.getWidth(), image.getHeight());
        this.image = image;
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        return this.image.getPieces();
    }
}
