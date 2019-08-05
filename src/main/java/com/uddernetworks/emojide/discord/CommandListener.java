package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.EditableTextFrame;
import com.uddernetworks.emojide.gui.EmptyFrame;
import com.uddernetworks.emojide.gui.ImageFrame;
import com.uddernetworks.emojide.gui.WindowFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.image.PreloadedImage;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class CommandListener extends ListenerAdapter {

    private EmojIDE emojIDE;
    private Displayer displayer;

    public CommandListener(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        var message = event.getMessage().getContentRaw().toLowerCase();
        var emojiManager = emojIDE.getEmojiManager();

        switch (message) {
            case "!start":
                event.getMessage().delete().queue();
                System.out.println("Creating displayer...");

                (displayer = new Displayer(emojIDE, event.getTextChannel(), true))
                        .setChild(new WindowFrame(displayer, 58, 10)
                                .addChild(new EditableTextFrame(displayer, 34, 6), 2, 2)
                                .addChild(new EmptyFrame(displayer, 19, 6)
                                        .setBackground(StaticEmoji.GREEN2), 37, 2), true);
                break;
            case "!stop":
                event.getMessage().delete().queue();
                this.emojIDE.getKeyboardInputManager().removeKeyboard();
                if (this.displayer != null) this.displayer.stop(event.getTextChannel());
                var selfId = emojIDE.getJda().getSelfUser().getIdLong();
                var removing = new ArrayList<Message>();
                event.getTextChannel().getIterableHistory().forEach(messageHistory -> {
                    if (messageHistory.getAuthor().getIdLong() != selfId) return;
                    removing.add(messageHistory);
                });
                event.getTextChannel().purgeMessages(removing);
                break;
        }
    }
}
