package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.discord.command.Argument;
import com.uddernetworks.emojide.discord.command.ArgumentError;
import com.uddernetworks.emojide.discord.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

@Command(name = "help", aliases = "h", minArgs = 0, maxArgs = 0)
public class HelpCommand {
    @Argument()
    public void base(Member member, TextChannel channel) {
        CommandHelp.send(member, channel);
    }

    @ArgumentError
    public void error(Member member, TextChannel channel, String command) {
        CommandHelp.send(member, channel);
    }
}
