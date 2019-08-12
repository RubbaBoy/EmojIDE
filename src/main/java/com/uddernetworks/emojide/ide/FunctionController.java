package com.uddernetworks.emojide.ide;

import com.uddernetworks.emojide.event.Handler;
import com.uddernetworks.emojide.gui.EditableDynamicTextFrame;
import com.uddernetworks.emojide.gui.EmptyContainerFrame;
import com.uddernetworks.emojide.gui.HighlightedTextFrame;
import com.uddernetworks.emojide.gui.TabbedFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.keyboard.KeyPressEvent;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager.ActiveState;
import com.uddernetworks.emojide.keyboard.KeyboardRaisable;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.utils.Commandline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FunctionController {

    private static Logger LOGGER = LoggerFactory.getLogger(FunctionController.class);

    private EmojIDE emojIDE;
    private KeyboardInputManager keyboardInputManager;
    private Displayer displayer;
    private TabbedFrame tabbedFrame;

    public FunctionController(EmojIDE emojIDE, Displayer displayer, TabbedFrame tabbedFrame) {
        this.emojIDE = emojIDE;
        this.displayer = displayer;
        this.tabbedFrame = tabbedFrame;

        this.keyboardInputManager = emojIDE.getKeyboardInputManager();
        KeyboardRaisable.get().addListener(this);
    }

    @Handler(event = "keyboard")
    private void onKeyPress(KeyPressEvent event) {
        if (event.isAlphanumeric()) {
            LOGGER.info("FunctionController state: {}", keyboardInputManager.getState().name());
            switch (keyboardInputManager.getState()) {
                case CTRL:
                    switch (Character.toLowerCase(event.getCharacter())) {
                        case 'r':
                            event.setCancelled(true);
                            var tab = tabbedFrame.getActive();
                            LOGGER.info("Running {}", tab.getName());

                            var container = (EmptyContainerFrame) tab.getComponent();
                            var textComponent = (HighlightedTextFrame) container.getChildren().get(0).getComponent();
                            var text = textComponent.getTextBlock().getText();
                            LOGGER.info("Code = \n{}", text);

                            try {
                                var file = new File("executing/" + tab.getName());
                                file.getParentFile().mkdirs();
                                file.createNewFile();
                                Files.write(file.toPath(), text.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                                Commandline.runLiveCommand(Arrays.asList("node", file.getAbsolutePath()), new File("/"), "Node");
                            } catch (IOException e) {
                                LOGGER.error("An error occurred while executing the code for tab " + tab.getName(), e);
                            }

                            break;
                    }
                    break;
            }
        }
    }

}
