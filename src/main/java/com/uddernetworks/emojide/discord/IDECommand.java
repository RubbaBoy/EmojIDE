package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.discord.command.*;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(name = "ide", aliases = "i", minArgs = 0, maxArgs = 2)
public class IDECommand {

    private static Logger LOGGER = LoggerFactory.getLogger(IDECommand.class);

    @Argument()
    public void base(Member member, TextChannel channel) {
        EmbedUtils.sendEmbed(channel, member, "Base", "Base command");
    }

    @Argument(format = "start")
    public void start(Member member, TextChannel channel) {
        EmbedUtils.sendEmbed(channel, member, "Starting", "This is the starting command!");
    }

    @Argument(format = "lookup *")
    public void lookup(Member member, TextChannel channel, ArgumentList args) {
        EmbedUtils.sendEmbed(channel, member, "Looking up...", "Looking up " + args.nextArg().getString());
    }

    @ArgumentError
    public void error(Member member, TextChannel channel, String command) {
        EmbedUtils.error(channel, member, "Error " + command);
    }
}
