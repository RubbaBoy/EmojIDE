package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.discord.command.*;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Command(name = "purge", minArgs = 0, maxArgs = 2, permission = Permission.ADMINISTRATOR)
public class PurgeCommand {

    private static Logger LOGGER = LoggerFactory.getLogger(PurgeCommand.class);

    private EmojIDE emojIDE;
    private Pattern idPattern = Pattern.compile("[0-9]{18}");

    public PurgeCommand(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;

        var callbackHandler = emojIDE.getWebCallbackHandler();
        callbackHandler.registerCommandCallback("purge", (member, channel, query) -> purge(channel));
    }

    @Argument()
    public void base(Member member, TextChannel channel) {
        purge(channel);
    }

    @Argument(format = "user *")
    public void user(Member member, TextChannel channel, ArgumentList args) {
        var userTag = args.nextArg().getString();
        Member user = null;
        var matcher = idPattern.matcher(userTag);
        if (matcher.find()) {
            var found = matcher.group();
            user = channel.getGuild().getMemberById(found);
        }

        if (user == null) {
            EmbedUtils.error(channel, member, "Couldn't find user `" + userTag + "`");
            return;
        }

        var userId = user.getIdLong();
        channel.purgeMessages(channel.getIterableHistory().stream().filter(message -> message.getAuthor().getIdLong() == userId).collect(Collectors.toList()));
    }

    @ArgumentError
    public void error(Member member, TextChannel channel, String command) {
        EmbedUtils.error(channel, member, "Error " + command + "\nTry doing `!emoji` for help");
    }

    private void purge(TextChannel textChannel) {
        var selfId = emojIDE.getJda().getSelfUser().getIdLong();
        textChannel.purgeMessages(textChannel.getIterableHistory().stream().filter(message -> message.getAuthor().getIdLong() == selfId).collect(Collectors.toList()));
    }
}
