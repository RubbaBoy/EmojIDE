package com.uddernetworks.emojide.ide;

import com.uddernetworks.emojide.gui.EditableDynamicTextFrame;
import com.uddernetworks.emojide.gui.TabbedFrame;
import com.uddernetworks.emojide.gui.TextPromptFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager.ActiveState;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TabController extends ListenerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(TabController.class);

    private EmojIDE emojIDE;
    private KeyboardInputManager keyboardInputManager;
    private Displayer displayer;
    private TabbedFrame tabbedFrame;

    public TabController(EmojIDE emojIDE, Displayer displayer) {
        this(emojIDE, displayer, null);
    }

    public TabController(EmojIDE emojIDE, Displayer displayer, TabbedFrame tabbedFrame) {
        this.emojIDE = emojIDE;
        this.displayer = displayer;
        this.tabbedFrame = tabbedFrame;

        (this.keyboardInputManager = emojIDE.getKeyboardInputManager()).addListener(this);
    }

    public TabController setTabbedFrame(TabbedFrame tabbedFrame) {
        this.tabbedFrame = tabbedFrame;
        return this;
    }

    private void onKeyPress(KeyPressEvent event) {
        if (event.isAlphanumeric()) {

            switch (Character.toLowerCase(event.getCharacter())) {
                case 'n':
                    if (keyboardInputManager.getState() != ActiveState.CTRL) break;
                    keyboardInputManager.suspendListeners();

                    var innerWidth = tabbedFrame.getWidth() - tabbedFrame.getXOffset();
                    var innerHeight = tabbedFrame.getHeight() - tabbedFrame.getYOffset();

                    var prompt = new TextPromptFrame(displayer, "Name:");
                    tabbedFrame.addChild(prompt, (innerWidth / 2) - (prompt.getWidth() / 2), (innerHeight / 2) - (prompt.getHeight() / 2));
                    prompt.onEnter(text -> {
                        LOGGER.info("Name of file: {}", text);
                        tabbedFrame.removeChild(prompt);
                        var textFrameTab = new EditableDynamicTextFrame(displayer, 56, 20);
                        tabbedFrame.addTab(text, textFrameTab);
                        tabbedFrame.selectTab(textFrameTab);
                    });

                    tabbedFrame.clearCache();
                    displayer.update();

                    break;
            }

            return;
        }
        keyboardInputManager.getPair(event.getStaticEmoji()).ifPresent(pair -> {
            switch (pair) {
                case SPACE:
                    break;
                case SHIFT:
                    break;
                case ENTER:
                    break;
                case CAPS:
                    break;
                case CTRL:
                    break;
                case ALT:
                    break;
                case FN:
                    break;
            }
        });
    }
}
