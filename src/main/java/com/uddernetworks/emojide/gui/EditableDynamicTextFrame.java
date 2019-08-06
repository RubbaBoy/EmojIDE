package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.styled.StyledEmojiComponent;
import com.uddernetworks.emojide.gui.text.DefaultTextBlock;
import com.uddernetworks.emojide.gui.text.DynamicTextBlock;
import com.uddernetworks.emojide.gui.text.TextBlock;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * A text frame that allows editing with a keyboard. Everything that goes over the width/height bounds are truncated.
 */
public class EditableDynamicTextFrame extends StyledEmojiComponent {

    private static Logger LOGGER = LoggerFactory.getLogger(EditableDynamicTextFrame.class);

    private KeyboardInputManager keyboardInputManager;
    private TextBlock textBlock;

    private int cursorX;
    private int cursorY;

    private int scrollX; // The amount of non-visible columns on the left
    private int scrollY; // The amount of non-visible rows on the top

    public EditableDynamicTextFrame(Displayer displayer, int width, int height) {
        super(displayer, width, height);
        this.textBlock = new DynamicTextBlock();
        (this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager()).addListener(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        validateCursor();

        var textEmoji = expandToDimensions(textBlock.toEmoji(emojiManager));
        textEmoji[cursorY][Math.min(textEmoji[0].length - 1, cursorX)] = StaticEmoji.CURSOR;

        return textEmoji;
    }

    private Emoji[][] expandToDimensions(Emoji[][] grid) {
        var result = new Emoji[height][];
        for (int y = 0; y < result.length; y++) {
            var line = new Emoji[width];
            Arrays.fill(line, StaticEmoji.DISCORD);
            if (y < grid.length) System.arraycopy(grid[y], 0, line, 0, grid[y].length);
            result[y] = line;
        }
        return result;
    }

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
                            textBlock.addAll(cursorY, textBlock.getCharList().get(cursorY + 1));
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
                    LOGGER.error("Unsupported action: INS");
                    break;
                case HOME:
                    cursorX = 0;
                    validateCursor();
                    refresh();
                    break;
                case PG_UP:
                    LOGGER.error("Unsupported action: PG_UP");
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
                    LOGGER.error("Unsupported action: PG_DOWN");
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
        var line = textBlock.getCharList().get(cursorY);
        for (int x = line.size() - 1; x > 0; x--) {
            if (line.get(x) == ' ' || line.get(x) == 0) continue;
            cursorX = x + 1;
            return;
        }
        cursorX = 0;
    }

    private void setCursorVertPos() {
        var chars = textBlock.getCharList();
        for (int x = chars.get(cursorY).size() - 1; x > 0; x--) {
            if (chars.get(cursorY).get(x) == ' ' || chars.get(cursorY).get(x) == 0) continue;
            cursorX = x;
            return;
        }
        cursorX = 0;
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
}
