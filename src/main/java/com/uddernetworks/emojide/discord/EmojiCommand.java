package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.discord.command.*;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.uddernetworks.emojide.discord.CommandHelp.space;

@Command(name = "emoji", aliases = "e", minArgs = 0, maxArgs = 2, permission = Permission.ADMINISTRATOR)
public class EmojiCommand {

    enum Groups {
        WELCOME_TITLE("Welcome Title"),
        WHITE_TEXT("White Text"),
        ORANGE_TEXT("Orange Text"),
        GREEN_TEXT("Green Text"),
        BLUE_TEXT("Blue Text"),
        GRAY_TEXT("Gray Text"),
        LIGHT_GRAY_TEXT("Light Gray Text");

        private String name;

        Groups(String name) {
            this.name = name;
        }
    }

    private EmojIDE emojIDE;
    private EmojiManager emojiManager;

    public EmojiCommand(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        emojiManager = emojIDE.getEmojiManager();
    }

    @Argument()
    public void base(Member member, TextChannel channel) {
        CommandHelp.send(member, channel);
    }

    @Argument(format = "help")
    public void help(Member member, TextChannel channel) {
        CommandHelp.send(member, channel);
    }

    @Argument(format = "info")
    public void info(Member member, TextChannel channel) {
        var emojis = emojiManager.getEmojis();
        var totalServers = emojiManager.getEmojiServers();
        var emojiDistribution = new LinkedHashMap<Guild, Integer>();
        new HashMap<>(emojiManager.getEmojiDistribution()).entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey().getName())).forEach(entry -> emojiDistribution.put(entry.getKey(), entry.getValue()));
        var builder = new StringBuilder();
        emojiDistribution.forEach((guild, amount) -> {
            builder.append("**").append(guild.getName()).append("**").append(space(3)).append("(").append(guild.getId()).append(")").append(space(4)).append("**").append(amount).append("/50**\n");
        });

        var groups = new StringBuilder();
        Arrays.stream(Groups.values()).forEach(group -> groups.append(space(4)).append(group.name).append("\n"));

        EmbedUtils.sendEmbed(channel, member, "Emoji Info", embed ->
                embed.setDescription("General information about emojis uploaded and used only by the EmojIDE.")
                .addField("Emoji Count", emojis.size() + "/" + (totalServers.size() * 50) + " emojis (Max based off of emoji servers available)", false)
                .addField("Emoji Guilds", "The following are the guilds used for uploading emojis:\n\n" + builder, false)
                .addField("Groups", "The following are groups you can remove via `!emoji grem [group]`\n" + groups, false)
        );
    }

    @Argument(format = "inspect *")
    public void inspect(Member member, TextChannel channel, ArgumentList args) {
        var emoji = args.nextArg().getString();

    }

    @Argument(format = "rem *")
    public void remove(Member member, TextChannel channel, ArgumentList args) {
        var emoji = args.nextArg().getString();

    }

    @Argument(format = "grem *")
    public void gremove(Member member, TextChannel channel, ArgumentList args) {
        var group = args.nextArg().getString();

    }

    @ArgumentError
    public void error(Member member, TextChannel channel, String command) {
        EmbedUtils.error(channel, member, "Error " + command + "\nTry doing `!emoji` for help");
    }


}
