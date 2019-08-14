package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.discord.command.EmbedUtils;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.web.WebCallbackHandler;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Map;

import static com.uddernetworks.emojide.main.EmojIDE.ZWS;

public class CommandHelp {

    private static WebCallbackHandler callbackHandler;

    public static void initHelp(EmojIDE emojIDE) {
        callbackHandler = emojIDE.getWebCallbackHandler();
        callbackHandler.registerCommandCallback("help", (member, channel, query) -> send(member, channel));
    }

    public static void send(Member member, TextChannel channel) {
        var emptyQuery = Map.of("member", member.getId(), "channel", channel.getId());
        EmbedUtils.sendEmbed(channel, member, "EmojIDE Command Help", embed -> embed.setDescription("Help for the EmojIDE commands (**base** is just the base command, and no arguments)")
                .addField("!help",
                        commandRow(callbackHandler.generateMdLink("base", "help", emptyQuery), "Show this help menu"), false)
                .addField("!purge",
                        commandRow(callbackHandler.generateMdLink("base", "purge", emptyQuery), "Purges all messages by the bot in the current channel") +
                        commandRow("user [user mention]", "Purges all messages by a user in the current channel"), false)
                .addField("!ide",
                        commandRow(callbackHandler.generateMdLink("setchannel", "setchannel", emptyQuery), "Sets the current channel to the channel displaying the IDE") +
                                commandRow(callbackHandler.generateMdLink("fonts", "fonts", emptyQuery), "Lists available fonts with the option to select one") +
                                commandRow("setfont [font]", "Sets the font with the given name from `!ide fonts`") +
                                commandRow(callbackHandler.generateMdLink("start", "start", emptyQuery), "Starts the IDE without clearing any past messages") +
                                commandRow(callbackHandler.generateMdLink("stop", "stop", emptyQuery), "Stops the IDE and removes its messages") +
                                commandRow(callbackHandler.generateMdLink("restart", "restart", emptyQuery), "Restart the IDE by stopping and starting it again"), false)
                .addField("!emoji",
                        commandRow(callbackHandler.generateMdLink("info", "info", emptyQuery), "Shows general information on the emojis and their servers") +
                                commandRow("inspect [emoji name]", "Inspects an emoji by its name") +
                                commandRow("cinspect [character]", "Shows all emojis for the given character") +
                                commandRow("rem [emoji name]", "Removes a single emoji that has been added by EmojIDE") +
                                commandRow("grem [group name]", "Removes all emojis in a group seen by `!emoji info`") +
                                commandRow("crem [character]", "Removes all colors (And fonts) of a given character"), false));
    }

    private static String commandRow(String name, String description) {
        return ("    **" + name + "**" + " ".repeat(7)).replace(" ", ZWS + " ") + " - " + description + "\n";
    }

    public static String space(int amount) {
        return (ZWS + " ").repeat(amount);
    }

}
