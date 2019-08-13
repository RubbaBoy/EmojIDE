package com.uddernetworks.emojide.discord;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.uddernetworks.emojide.discord.command.Argument;
import com.uddernetworks.emojide.discord.command.ArgumentError;
import com.uddernetworks.emojide.discord.command.Command;
import com.uddernetworks.emojide.discord.command.EmbedUtils;
import com.uddernetworks.emojide.gui.*;
import com.uddernetworks.emojide.gui.components.CachedDisplayer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.ide.ConsolePiper;
import com.uddernetworks.emojide.ide.FunctionController;
import com.uddernetworks.emojide.ide.TabController;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

@Command(name = "ide", aliases = "i", minArgs = 0, maxArgs = 2, permission = Permission.ADMINISTRATOR)
public class IDECommand {

    private static Logger LOGGER = LoggerFactory.getLogger(IDECommand.class);

    private EmojIDE emojIDE;
    private FileConfig config;
    private Displayer displayer;

    public IDECommand(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        config = EmojIDE.getConfigManager().getConfig();

        var callbackHandler = emojIDE.getWebCallbackHandler();
        callbackHandler.registerCommandCallback("setchannel", (member, channel, query) -> setChannel(member, channel));
        callbackHandler.registerCommandCallback("start", (member, channel, query) -> start(member, channel));
        callbackHandler.registerCommandCallback("stop", (member, channel, query) -> stop(member, channel));
        callbackHandler.registerCommandCallback("restart", (member, channel, query) -> restart(member, channel));
    }

    @Argument()
    public void base(Member member, TextChannel channel) {
        CommandHelp.send(member, channel);
    }

    @Argument(format = "help")
    public void help(Member member, TextChannel channel) {
        CommandHelp.send(member, channel);
    }

    @Argument(format = "setchannel")
    public void setChannel(Member member, TextChannel channel) {
        config.set("ide.channel", channel.getIdLong());
        EmbedUtils.sendEmbed(channel, member, "Set channel", "Set IDE channel to " + channel.getAsMention());
    }

    @Argument(format = "start")
    public void start(Member member, TextChannel channel) {
        commandStart(getIDEChannel(channel));
    }

    @Argument(format = "stop")
    public void stop(Member member, TextChannel channel) {
        commandStop(getIDEChannel(channel));
    }

    @Argument(format = "restart")
    public void restart(Member member, TextChannel channel) {
        channel = getIDEChannel(channel);
        commandStop(channel);
        Thread.sleep(1000);
        commandStart(channel);
    }

    @ArgumentError
    public void error(Member member, TextChannel channel, String command) {
        EmbedUtils.error(channel, member, "Error " + command + "\nTry doing `!ide` for help");
    }

    private TextChannel getIDEChannel(TextChannel channel) {
        long ideChannel = config.getOrElse("ide.channel", -1L);
        if (ideChannel == -1) config.set("ide.channel", ideChannel = channel.getIdLong());
        return channel.getGuild().getTextChannelById(ideChannel);
    }

    private void commandStart(TextChannel channel) {
        LOGGER.info("Creating displayer...");

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
                                                                .addRenderer(initial -> Arrays.fill(initial[0], StaticEmoji.CTABBED_FRAME))
                                                                .addChild(outputFrame = new StaticTextFrame(displayer, 54, 4).setText("<Console Output>"), 1, 1), 0, 15), true), 0, 0)
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
