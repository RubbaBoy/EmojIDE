package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;
import com.uddernetworks.emojide.gui.text.TextBlock;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditableTextFrame extends StyledEmojiComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(EditableTextFrame.class);

    private TextBlock textBlock;

    private int cursorX;
    private int cursorY;

    public EditableTextFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
        this.textBlock = new TextBlock(width, height);
        displayer.getEmojIDE().getKeyboardInputManager().addListener(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        LOGGER.info("RENDERING AGAIN!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        validateCursor();

        LOGGER.info("Text = {}", textBlock);

        var textEmoji = textBlock.toEmoji(emojiManager, initial);
        textEmoji[cursorY][cursorX] = Emoji.CURSOR;

        return textEmoji;
    }

    private void onKeyPress(KeyPressEvent event) {
        LOGGER.info("Key pressed: {}", event);

        if (event.isAlphanumeric()) {
            textBlock.setChar(event.getCharacter(), this.cursorX, this.cursorY);

            LOGGER.info("Text = {}", textBlock);

            if (++cursorX >= this.width) {
                this.cursorX = 0;
                this.cursorY++;
            }

            clearCache();
            displayer.update();
        } else {
            var emoji = event.getEmoji();
            switch (emoji) {
                case UP:
                    cursorY--;
                    break;
                case DOWN:
                    cursorY++;
                    break;
                case LEFT:
                    cursorX--;
                    break;
                case RIGHT:
                    cursorX++;
                    break;
            }
        }
    }

    private void validateCursor() {
        this.cursorX = Math.max(this.cursorX, 0);
        this.cursorY = Math.max(this.cursorY, 0);

        this.cursorX = Math.min(this.cursorX, this.width);
        this.cursorY = Math.min(this.cursorY, this.height);
    }
}
