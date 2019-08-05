package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.EditableTextFrame;
import com.uddernetworks.emojide.gui.EmptyFrame;
import com.uddernetworks.emojide.gui.StaticTextFrame;
import com.uddernetworks.emojide.gui.WindowFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandListener extends ListenerAdapter {

    private EmojIDE emojIDE;
    private Displayer displayer;

    public CommandListener(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        var message = event.getMessage().getContentRaw().toLowerCase();
        var emojiManager = emojIDE.getEmojiManager();;

        switch (message) {
            case "!start":
                event.getMessage().delete().queue();
                System.out.println("Creating displayer...");

                displayer = new Displayer(emojIDE, event.getTextChannel());

                var window = new WindowFrame(displayer, 58, 10); // 58 max
                var textEdit = new EditableTextFrame(displayer, 34, 6);
                window.addChild(textEdit, 2, 2);

                var emptyPane = new EmptyFrame(displayer, 19, 6)
                        .setBackground(emojiManager.getEmoji("green2"));

                window.addChild(emptyPane, 37, 2);

                displayer.setChild(window);
                displayer.update();

                this.emojIDE.getKeyboardInputManager().createKeyboard(event.getTextChannel());
                break;
            case "!stop":
                event.getMessage().delete().queue();
                this.emojIDE.getKeyboardInputManager().removeKeyboard();
                this.displayer.stop();
                break;
        }
    }
}
