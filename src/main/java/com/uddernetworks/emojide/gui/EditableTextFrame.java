package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;
import com.uddernetworks.emojide.gui.text.TextBlock;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditableTextFrame extends StyledEmojiComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(EditableTextFrame.class);

    private KeyboardInputManager keyboardInputManager;
    private TextBlock textBlock;

    private int cursorX;
    private int cursorY;

    public EditableTextFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
        this.textBlock = new TextBlock(width, height);
        (this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager()).addListener(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        validateCursor();

        var textEmoji = textBlock.toEmoji(emojiManager, initial);
        textEmoji[cursorY][cursorX] = Emoji.CURSOR;

        return textEmoji;
    }

    private void onKeyPress(KeyPressEvent event) {
        LOGGER.info("Key pressed: {}", event);

        if (event.isAlphanumeric()) {
            addCharacter(event.getCharacter());
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
                default:
                    this.keyboardInputManager.getPair(emoji).ifPresent(pair -> {
                        switch (pair) {
                            case SPACE:
                                addCharacter(' ');
                                break;
                            case ENTER:
                                textBlock.addEmpty(this.cursorY++);
                                this.cursorX = 0;
                                refresh();
                                break;
                            case SHIFT:

                                break;
                        }
                    });
                    break;
            }
        }
    }

    private void addCharacter(char character) {
        textBlock.setChar(character, this.cursorX, this.cursorY);

        if (++cursorX >= this.width) {
            this.cursorX = 0;
            this.cursorY++;
        }

        refresh();
    }

    private void refresh() {
        clearCache();
        displayer.update();
    }

    private void validateCursor() {
        this.cursorX = Math.max(this.cursorX, 0);
        this.cursorY = Math.max(this.cursorY, 0);

        this.cursorX = Math.min(this.cursorX, this.width);
        this.cursorY = Math.min(this.cursorY, this.height);
    }
}
