package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.gui.components.DefaultEmojiComponent;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.StringConcatFactory;
import java.util.Arrays;
import java.util.function.Consumer;

public class TextPromptFrame extends DefaultEmojiContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(TextPromptFrame.class);

    private KeyboardInputManager keyboardInputManager;
    private EditableStaticTextFrame textFrame;
    private Consumer<String> enterText;

    public TextPromptFrame(Displayer displayer, String prompt) {
        this(displayer, 14, 4, prompt);
    }

    public TextPromptFrame(Displayer displayer, int width, int height, String prompt) {
        super(displayer, width, height);
        addChild(new StaticTextFrame(displayer, 10, 1, prompt), 1, 1);
        addChild(this.textFrame = new EditableStaticTextFrame(displayer, 10, 1), 1, 2);
        (this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager()).addListener(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        return drawBorder(super.render(initial));
    }

    private Emoji[][] drawBorder(Emoji[][] rows) {
        for (int y = 1; y < rows.length - 1; y++) {
            rows[y][0] = StaticEmoji.LTABBED_FRAME;
            rows[y][rows[0].length - 1] = StaticEmoji.RTABBED_FRAME;
        }

        Arrays.fill(rows[0], 1, rows[0].length - 1, StaticEmoji.TTABBED_FRAME);
        Arrays.fill(rows[rows.length - 1], 1, rows[rows.length - 1].length - 1, StaticEmoji.BTABBED_FRAME);
        return rows;
    }

    private void onKeyPress(KeyPressEvent event) {
        if (event.isAlphanumeric()) return;
        displayer.getEmojIDE().getKeyboardInputManager().getPair(event.getStaticEmoji()).filter(pair -> pair == KeyboardInputManager.Pair.ENTER).ifPresent(enter -> {
            keyboardInputManager.resumeListeners();
            var text = textFrame.getTextBlock().getText();
            LOGGER.info("Received text: {}", text);
            if (enterText != null) enterText.accept(text);
        });
    }

    public void onEnter(Consumer<String> enterText) {
        this.enterText = enterText;
    }
}
