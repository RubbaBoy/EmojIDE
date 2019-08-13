package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.gui.*;
import com.uddernetworks.emojide.gui.components.CachedDisplayer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.ide.ConsolePiper;
import com.uddernetworks.emojide.ide.FunctionController;
import com.uddernetworks.emojide.ide.TabController;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Arrays;
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
        var raw = event.getMessage().getContentRaw().toLowerCase();
        if (!raw.startsWith("!")) return;
        var args = raw.split("\\s+");
        switch (args[0]) {
            case "start":
                event.getMessage().delete().queue();
                commandStart(channel);
                break;
            case "stop":
                event.getMessage().delete().queue();
                commandStop(channel);
                break;
            case "r":
                event.getMessage().delete().queue();
                commandStop(channel);
                Thread.sleep(1000);
                commandStart(channel);
                break;
        }
    }

    private void commandStart(TextChannel channel) {
        System.out.println("Creating displayer...");

        var text = "function myMethod(arg) {\n" +
                "   console.log('Arg is ' + arg + '!');\n" +
                "}\n" +
                "\n" +
                "myMethod('Hello World');\n";

        TabbedFrame tabbedFrame;
        StaticTextFrame outputFrame;
        (displayer = new CachedDisplayer(emojIDE, channel, true))
                .setChild(
                        new EmptyContainerFrame(displayer, 58, 23)
                                .addChild(tabbedFrame = new TabbedFrame(displayer, 58, 23)
                                        .addTab("Welcome", new WelcomeFrame(displayer))
                                        .addTab("script.js",
                                                new EmptyContainerFrame(displayer, 56, 20)
                                                        .addChild(new HighlightedTextFrame(displayer, 54, 18, text), 1, 1)
                                                        .addChild(new CustomRenderedContainerFrame(displayer, 56, 5)
                                                                .addRenderer(initial -> Arrays.fill(initial[0], StaticEmoji.TTABBED_FRAME))
                                                                .addChild(outputFrame = new StaticTextFrame(displayer, 54, 4).setText("Hello World from console!"), 1, 1), 0, 15), true), 0, 0)
                        , true);

        new TabController(emojIDE, displayer, tabbedFrame);
        new FunctionController(emojIDE, displayer, tabbedFrame, new ConsolePiper(outputFrame));
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
