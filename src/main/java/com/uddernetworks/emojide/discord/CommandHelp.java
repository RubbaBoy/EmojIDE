package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.discord.command.EmbedUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.commons.lang3.StringUtils;

import static com.uddernetworks.emojide.main.EmojIDE.ZWS;

public class CommandHelp {

    public static void send(Member member, TextChannel channel) {
        EmbedUtils.sendEmbed(channel, member, "EmojIDE Command Help", embed ->
                embed.setDescription("Help for the EmojIDE commands")
                        .addField("!ide",
                                commandRow("start", "Starts the IDE without clearing any past messages") +
                                commandRow("stop", "Stops the IDE and removes its messages") +
                                commandRow("restart", "Restart the IDE by stopping and starting it again"), false)
                        .addField("!emoji",
                                commandRow("info", "Shows general information on the emojis and their servers") +
                                commandRow("inspect [emoji name]", "Inspects an emoji by its name") +
                                commandRow("rem [emoji name]", "Removes a single emoji that has been added by EmojIDE") +
                                commandRow("grem [group name]", "Removes all emojis in a group seen by `!emoji info`"), false));
    }

    private static String commandRow(String name, String description) {
        return ("    **" + name + "**" + " ".repeat(7)).replace(" ", ZWS + " ") + " - " + description + "\n";
    }

    public static String space(int amount) {
        return (ZWS + " ").repeat(amount);
    }

}
