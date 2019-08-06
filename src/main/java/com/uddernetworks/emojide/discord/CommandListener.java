package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.EditableDynamicTextFrame;
import com.uddernetworks.emojide.gui.EditableStaticTextFrame;
import com.uddernetworks.emojide.gui.EmptyFrame;
import com.uddernetworks.emojide.gui.WindowFrame;
import com.uddernetworks.emojide.gui.components.CachedDisplayer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

public class CommandListener extends ListenerAdapter {

    private EmojIDE emojIDE;
    private Displayer displayer;

    public CommandListener(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        var message = event.getMessage().getContentRaw().toLowerCase();
        switch (message) {
            case "!start":
                event.getMessage().delete().queue();
                System.out.println("Creating displayer...");

                (displayer = new CachedDisplayer(emojIDE, event.getTextChannel(), true))
                        .setChild(new WindowFrame(displayer, 58, 10)
                                .addChild(new EditableDynamicTextFrame(displayer, 34, 6), 2, 2)
                                .addChild(new EmptyFrame(displayer, 19, 6)
                                        .setBackground(StaticEmoji.GREEN2), 37, 2), true);
                break;
            case "!stop":
                event.getMessage().delete().queue();
                this.emojIDE.getKeyboardInputManager().removeKeyboard();
                if (this.displayer != null) this.displayer.stop(event.getTextChannel());
                var selfId = emojIDE.getJda().getSelfUser().getIdLong();
                event.getTextChannel().purgeMessages(event.getTextChannel().getIterableHistory()
                        .stream()
                        .filter(messageHistory -> messageHistory.getAuthor().getIdLong() == selfId)
                        .collect(Collectors.toList()));
                break;
        }
    }
}
