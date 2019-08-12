package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.event.Handler;
import com.uddernetworks.emojide.event.Priority;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;
import com.uddernetworks.emojide.gui.text.DefaultTextBlock;
import com.uddernetworks.emojide.gui.text.TextBlock;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.KeyboardRaisable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A text frame that allows editing with a keyboard. Everything that goes over the width/height bounds are truncated.
 */
public class EditableStaticTextFrame extends StyledEmojiComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(EditableStaticTextFrame.class);

    private KeyboardInputManager keyboardInputManager;
    private TextBlock textBlock;

    private int cursorX;
    private int cursorY;

    public EditableStaticTextFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
        this.textBlock = new DefaultTextBlock(width, height);
        this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager();
        KeyboardRaisable.get().addListener(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        validateCursor();

        var textEmoji = textBlock.toEmoji(emojiManager, initial);
        textEmoji[Math.min(height, cursorY)][Math.min(width - 1, cursorX)] = StaticEmoji.CURSOR;

        return textEmoji;
    }

    @Handler(event = "keyboard", priority = Priority.LOW)
    private void onKeyPress(KeyPressEvent event) {
        LOGGER.info("Key pressed: {}", event);

        if (event.isAlphanumeric()) {
            addCharacter(event.getCharacter());
        } else {
            var emoji = event.getStaticEmoji();
            switch (emoji) {
                case UP:
                    cursorY--;
                    var curr = textBlock.getCharacter(cursorX, cursorY);
                    if (curr == ' ' || curr == 0) setCursorVertPos();
                    validateCursor();
                    refresh();
                    break;
                case DOWN:
                    cursorY++;
                    curr = textBlock.getCharacter(cursorX, cursorY);
                    if (curr == ' ' || curr == 0) setCursorVertPos();
                    validateCursor();
                    refresh();
                    break;
                case LEFT:
                    cursorX--;
                    validateCursor();
                    refresh();
                    break;
                case RIGHT:
                    cursorX++;
                    validateCursor();
                    refresh();
                    break;
                case BACKSPACE:
                    if (cursorX > 0) {
                        textBlock.removeChar(cursorX--, cursorY);
                    } else {
                        cursorX--;
                        cursorY--;
                        if (cursorY >= 0) {
                            textBlock.addAll(cursorY, textBlock.getCharArray()[cursorY + 1]);
                            setCursorToEnd();
                        } else {
                            cursorY = 0;
                            cursorX = 0;
                        }
                    }
                    validateCursor();
                    refresh();
                    break;
                case INS:
                    // Easy to implement, not sure how I'd want to change the cursor though
                    LOGGER.error("Unsupported action for EditableStaticTextFrame: INS");
                    break;
                case HOME:
                    cursorX = 0;
                    validateCursor();
                    refresh();
                    break;
                case PG_UP:
                    LOGGER.error("Unsupported action for EditableStaticTextFrame: PG_UP");
                    break;
                case DEL:
                    textBlock.removeChar(cursorX + 1, cursorY);
                    validateCursor();
                    refresh();
                    break;
                case END:
                    setCursorToEnd();
                    validateCursor();
                    refresh();
                    break;
                case PG_DOWN:
                    LOGGER.error("Unsupported action for EditableStaticTextFrame: PG_DOWN");
                    break;
                default:
                    this.keyboardInputManager.getPair(emoji).ifPresent(pair -> {
                        switch (pair) {
                            case SPACE:
                                addCharacter(' ');
                                break;
                            case ENTER:
                                textBlock.newlineAt(this.cursorX, this.cursorY++);
                                this.cursorX = 0;
                                refresh();
                                break;
                        }
                    });
                    break;
            }
        }
    }

    private void setCursorToEnd() {
        var chars = textBlock.getCharArray();
        for (int x = chars[cursorY].length - 1; x > 0; x--) {
            if (chars[cursorY][x] == ' ' || chars[cursorY][x] == 0) continue;
            cursorX = x + 1;
            return;
        }
        cursorX = 0;
    }

    private void setCursorVertPos() {
        var chars = textBlock.getCharArray();
        var lastChar = 0;

        for (int x = chars[cursorY].length - 1; x > 0; x--) {
            if (chars[cursorY][x] == ' ' || chars[cursorY][x] == 0) continue;
            lastChar = x;
            break;
        }
        cursorX = lastChar;
    }

    private void addCharacter(char character) {
        textBlock.addChar(character, this.cursorX, this.cursorY);

        if (++cursorX >= this.width) {
            this.cursorX = 0;
            this.cursorY++;
        }

        validateCursor();
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

    public TextBlock getTextBlock() {
        return textBlock;
    }
}
