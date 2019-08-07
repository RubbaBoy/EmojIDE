package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.*;
import com.uddernetworks.emojide.gui.components.CachedDisplayer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
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

        var channel = emojIDE.getJda().getTextChannelById(606856180773421061L);
        commandStop(channel);
        Thread.sleep(1000);
        commandStart(channel);
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        var channel = event.getTextChannel();
        switch (event.getMessage().getContentRaw().toLowerCase()) {
            case "!start":
                event.getMessage().delete().queue();
                commandStart(channel);
                break;
            case "!stop":
                event.getMessage().delete().queue();
                commandStop(channel);
                break;
            case "!r":
                event.getMessage().delete().queue();
                commandStop(channel);
                Thread.sleep(1000);
                commandStart(channel);
                break;
        }
    }

    private void commandStart(TextChannel channel) {
        System.out.println("Creating displayer...");

        (displayer = new CachedDisplayer(emojIDE, channel, true))
                .setChild(new TabbedFrame(displayer, 58, 23 /* 10 */)
                        .addTab("Welcome", new WelcomeFrame(displayer)), true);
    }

    private void commandStop(TextChannel channel) {
        this.emojIDE.getKeyboardInputManager().removeKeyboard();
        if (this.displayer != null) this.displayer.stop(channel);
        var selfId = emojIDE.getJda().getSelfUser().getIdLong();
        channel.purgeMessages(channel.getIterableHistory()
                .stream()
                .filter(messageHistory -> messageHistory.getAuthor().getIdLong() == selfId)
                .collect(Collectors.toList()));
    }
}
