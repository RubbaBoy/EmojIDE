package com.uddernetworks.emojide.discord;

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
        switch (message) {
            case "!start":
                event.getMessage().delete().queue();
                System.out.println("Creating displayer...");

                this.displayer = new Displayer(emojIDE, event.getTextChannel());
                var window = new WindowFrame(displayer, 58, 3); // 58 max
                displayer.setChild(window);
                displayer.update();
                break;
            case "!stop":
                event.getMessage().delete().queue();
                this.displayer.stop();
                break;
        }
    }
}
