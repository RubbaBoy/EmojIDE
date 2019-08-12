package com.uddernetworks.emojide.gui;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.event.Handler;
import com.uddernetworks.emojide.gui.components.DefaultEmojiContainer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.KeyboardRaisable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import static com.uddernetworks.emojide.gui.components.styled.StyleUtils.drawThinBorder;

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
        this.keyboardInputManager = displayer.getEmojIDE().getKeyboardInputManager();
        KeyboardRaisable.get().addListener(this);
    }

    @Override
    public Emoji[][] render(Emoji[][] initial) {
        return drawThinBorder(super.render(initial));
    }

    @Handler(event = "keyboard")
    private void onKeyPress(KeyPressEvent event) {
        if (event.isAlphanumeric()) return;
        displayer.getEmojIDE().getKeyboardInputManager().getPair(event.getStaticEmoji()).filter(pair -> pair == KeyboardInputManager.Pair.ENTER).ifPresent(enter -> {
            KeyboardRaisable.get().resumeListeners();
            var text = textFrame.getTextBlock().getText();
            LOGGER.info("Received text: {}", text);
            if (enterText != null) enterText.accept(text);
        });
    }

    public void onEnter(Consumer<String> enterText) {
        this.enterText = enterText;
    }
}
