package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.*;
import com.uddernetworks.emojide.gui.components.CachedDisplayer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CommandListener extends ListenerAdapter {

    private EmojIDE emojIDE;
    private Displayer displayer;

    public CommandListener(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        switch (event.getMessage().getContentRaw().toLowerCase()) {
            case "!start":
                event.getMessage().delete().queue();
                commandStart(event);
                break;
            case "!stop":
                event.getMessage().delete().queue();
                commandStop(event);
                break;
            case "!r":
                event.getMessage().delete().queue();
                commandStop(event);
                Thread.sleep(1000);
                commandStart(event);
                break;
        }
    }

    private void commandStart(MessageReceivedEvent event) {
        System.out.println("Creating displayer...");

//                                .addChild(new EditableDynamicTextFrame(displayer, 34, 6), 2, 2)

        (displayer = new CachedDisplayer(emojIDE, event.getTextChannel(), true))
                .setChild(new TabbedFrame(displayer, 58, 4 /* 10 */)
                        .addTab("Red", new EmptyFrame(displayer, 56, 2).setBackground(StaticEmoji.RED))
                        .addTab("Green", new EmptyFrame(displayer, 56, 2).setBackground(StaticEmoji.GREEN1))
                        .addTab("Blue", new EmptyFrame(displayer, 56, 2).setBackground(StaticEmoji.BLUE1)), true);
    }

    private void commandStop(MessageReceivedEvent event) {
        this.emojIDE.getKeyboardInputManager().removeKeyboard();
        if (this.displayer != null) this.displayer.stop(event.getTextChannel());
        var selfId = emojIDE.getJda().getSelfUser().getIdLong();
        event.getTextChannel().purgeMessages(event.getTextChannel().getIterableHistory()
                .stream()
                .filter(messageHistory -> messageHistory.getAuthor().getIdLong() == selfId)
                .collect(Collectors.toList()));
    }
}
