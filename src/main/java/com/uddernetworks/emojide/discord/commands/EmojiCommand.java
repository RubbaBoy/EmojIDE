package com.uddernetworks.emojide.discord.commands;

import com.uddernetworks.emojide.discord.commands.manager.*;
import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.Group;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.web.WebCallbackHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.uddernetworks.emojide.discord.commands.CommandHelp.space;
import static com.uddernetworks.emojide.main.EmojIDE.ZWS;
import static java.util.function.Predicate.not;

@Command(name = "emoji", aliases = "e", minArgs = 0, maxArgs = 3, permission = Permission.MESSAGE_WRITE)
public class EmojiCommand {

    private static Logger LOGGER = LoggerFactory.getLogger(EmojiCommand.class);

    private EmojIDE emojIDE;
    private EmojiManager emojiManager;
    private WebCallbackHandler callbackHandler;
    private Map<String, Emoji> emojis;
    private long importantChannel;

    public EmojiCommand(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        emojiManager = emojIDE.getEmojiManager();
        importantChannel = EmojIDE.getConfigManager().getConfig().getLong("show-important");

        callbackHandler = emojIDE.getWebCallbackHandler();
        callbackHandler.registerCommandCallback("info", (member, channel, query) -> info(member, channel));
        callbackHandler.registerCommandCallback("inspect", Collections.singletonList("emoji"), (member, channel, query) ->
                inspect(member, channel, new ArgumentList(new CommandArg(query.get("emoji")))));
        callbackHandler.registerCommandCallback("grem", Collections.singletonList("ordinal"), (member, channel, query) ->
                gremove(member, channel, new ArgumentList(new CommandArg(query.get("ordinal")))));
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
        var emojiDistribution = new HashMap<>(emojiManager.getEmojiDistribution());
        var existingIds = emojiDistribution.keySet().stream().map(Guild::getIdLong).collect(Collectors.toSet());
        EmojIDE.getConfigManager().getServers()
                .stream()
                .filter(not(existingIds::contains))
                .map(emojIDE.getJda()::getGuildById)
                .forEach(guild -> emojiDistribution.putIfAbsent(guild, 0));

        Comparator<Map.Entry<Guild, Integer>> comparator;
        if (emojiDistribution.keySet().stream().map(Guild::getName).allMatch(name -> name.matches(".*\\s+\\d+"))) {
            // Sorting by the ending number of a server
            comparator = Comparator.comparingInt(entry -> {
                var name = entry.getKey().getName();
                return Integer.parseInt(name.substring(name.lastIndexOf(' ') + 1));
            });
        } else {
            // Default sorting
            comparator = Comparator.comparing(entry -> entry.getKey().getName());
        }

        var sortedDistribution = new LinkedHashMap<Guild, Integer>();

        emojiDistribution
                .entrySet()
                .stream()
                .sorted(comparator)
                .forEach(entry -> sortedDistribution.put(entry.getKey(), entry.getValue()));

        var fields = splitToFields(sortedDistribution.entrySet().stream().map(entry ->
                entry.getKey().getName() + space(4) + "**" + entry.getValue() + "/50**\n").collect(Collectors.toList()));

        var emptyQuery = Map.of("member", member.getId(), "channel", channel.getId());
        var groupFields = splitToFields(Arrays.stream(Group.values())
                .map(group -> callbackHandler.generateMdLink(StaticEmoji.DELETE.getDisplay(), "grem", combine(emptyQuery, Map.of("ordinal", String.valueOf(group.ordinal())))) + space(2) + group.getName() + "\n")
                .collect(Collectors.toList()), 7);

        EmbedUtils.sendEmbed(channel, member, "Emoji Info", embed -> {
                    var realSize = emojis.size();
                    var totalSize = totalServers.size() * 50;
                    embed.setDescription("General information about emojis uploaded and used only by the EmojIDE.")
                            .addField("Emoji Count", realSize + "/" + totalSize + " emojis (" + (totalSize - realSize) + " open slots, max based off of emoji servers available)", false)
                            .addField("Emoji Guilds", "The following are the guilds used for uploading emojis:", false);

                    fields.forEach(section -> embed.addField(String.valueOf(ZWS), section, true));

                    embed.addField("Groups", "The following are groups you can remove by clicking the " + StaticEmoji.DELETE.getDisplay() + " or via `!emoji grem [group]`", false);
                    groupFields.forEach(section -> embed.addField(String.valueOf(ZWS), section, true));
                }
        );
    }

    private Map<String, String> combine(Map<String, String> one, Map<String, String> two) {
        var ret = new HashMap<>(one);
        ret.putAll(two);
        return ret;
    }

    private List<String> splitToFields(List<String> entries) {
        return splitToFields(entries, 20);
    }

    private List<String> splitToFields(List<String> entries, int maxEntryLength) {
        var fields = new ArrayList<String>();
        var bufferCount = new AtomicInteger();
        var buffer = new StringBuilder();
        entries.forEach(entry -> {
            if (bufferCount.get() >= maxEntryLength || buffer.length() + entry.length() > 1024) {
                fields.add(buffer.toString());
                buffer.setLength(0);
                bufferCount.set(0);
            }
            buffer.append(entry);
            bufferCount.incrementAndGet();
        });
        fields.add(buffer.toString());
        return fields;
    }

    @Argument(format = "inspect *")
    public void inspect(Member member, TextChannel channel, ArgumentList args) {
        var emojiName = args.nextArg().getString();
        var emojiOptional = getEmoji(emojiName);
        var emotes = emojIDE.getJda().getEmotesByName(emojiName, true);
        if (emojiOptional.isEmpty() || emotes.isEmpty()) {
            EmbedUtils.error(channel, member, "Unknown emoji name `" + emojiName + "`");
            return;
        }

        var emoji = (StaticEmoji) emojiOptional.get();
        var emote = emotes.get(0);

        EmbedUtils.sendEmbed(channel, member, "Emoji " + emoji.getName(), embed -> embed
                .setTitle("**:" + emote.getName() + ":**")
                .setThumbnail(emote.getImageUrl())
                .setDescription(emoji.getDisplay())
                .addField("Identifying",
                        "**Enum name**: " + emoji.name() +
                                "\n**Id**: " + emoji.getId() +
                                "\n**Image path**: " + emoji.getRelativePath(), false)
                .addField("Created on", emote.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField("Server", emote.getGuild().getName() + " (" + emote.getGuild().getId() + ")", false)
                .addField("Group", getGroupFor(emoji).map(Group::getName).orElse("N/A"), false));
    }

    @Argument(format = "cinspect *")
    public void cinspect(Member member, TextChannel channel, ArgumentList args) {
        var charString = args.nextArg().getString();
        char cha;
        if (charString.length() != 1 || (cha = charString.charAt(0)) < 32 || cha > 126) {
            EmbedUtils.error(channel, member, "Unknown character `" + charString + "`");
            return;
        }

        var emojis = Stream.of("", "o", "g", "b", "a", "l").map(pre -> (StaticEmoji) emojiManager.getEmoji(pre + ((int) cha))).collect(Collectors.toList());

        var dis = String.valueOf(cha);
        switch (cha) {
            case '\\':
                dis = "\\\\";
                break;
            case '`':
                dis = "\\`";
                break;
        }

        EmbedUtils.sendEmbed(channel, member, "Emojis for `" + dis + "`", embed ->
                emojis.forEach(emoji ->
                        embed.addField(emoji.getDisplay() + " **:" + emoji.getName() + ":**", "(**" + callbackHandler.generateMdLink("inspect", "inspect", Map.of("member", member.getId(), "channel", channel.getId(), "emoji", emoji.getName())) + "**) " + emoji.name() + " (" + emoji.getId() + ") " + getGroupFor(emoji).map(Group::getName).orElse("") + "\n" + ZWS, false)));
    }

    @Argument(format = "rem *")
    public void remove(Member member, TextChannel channel, ArgumentList args) {
        if (importantChannel != -1 && channel.getIdLong() != importantChannel) {
            EmbedUtils.error(channel, member, "Fuck you, Rubba disabled this for this channel");
            return;
        }

        var emojiName = args.nextArg().getString();
        var emojiOptional = getEmoji(emojiName);
        var emotes = emojIDE.getJda().getEmotesByName(emojiName, true);
        if (emojiOptional.isEmpty() || emotes.isEmpty()) {
            EmbedUtils.error(channel, member, "Unknown emoji name `" + emojiName + "`");
            return;
        }

        var emoji = emojiOptional.get();
        var emote = emotes.get(0);

        emote.delete().queue();

        EmbedUtils.sendEmbed(channel, member, "Queued Deletion", "Queued the deletion of the emoji `" + emote.getName() + "`");
    }

    @Argument(format = "grem *")
    public void gremove(Member member, TextChannel channel, ArgumentList args) {
        if (importantChannel != -1 && channel.getIdLong() != importantChannel) {
            EmbedUtils.error(channel, member, "Fuck you, Rubba disabled this for this channel");
            return;
        }

        var groupName = args.nextArg().getString();
        var groupOptional = getGroup(groupName);
        if (groupOptional.isEmpty()) {
            EmbedUtils.error(channel, member, "Unknown group name `" + groupName + "`");
            return;
        }

        bulkDeleteMessages(member, channel, groupOptional.get().getEmojis());
    }

    @Argument(format = "crem *")
    public void cremove(Member member, TextChannel channel, ArgumentList args) {
        if (importantChannel != -1 && channel.getIdLong() != importantChannel) {
            EmbedUtils.error(channel, member, "Fuck you, Rubba disabled this for this channel");
            return;
        }

        var charString = args.nextArg().getString();
        char removing;
        if (charString.length() != 1 || (removing = charString.charAt(0)) < 32 || removing > 126) {
            EmbedUtils.error(channel, member, "Unknown character `" + charString + "`");
            return;
        }

        bulkDeleteMessages(member, channel, Stream.of("", "o", "g", "b", "a", "l").map(pre -> emojiManager.getEmoji(pre + ((int) removing))).collect(Collectors.toList()));
    }

    @ArgumentError
    public void error(Member member, TextChannel channel, String command) {
        EmbedUtils.error(channel, member, "Error " + command + "\nTry doing `!emoji` for help");
    }

    private Optional<Emoji> getEmoji(String name) {
        if (emojis == null || emojis.isEmpty()) emojis = emojiManager.getEmojis();
        return Optional.ofNullable(emojis.get(name));
    }

    private Optional<Group> getGroup(String name) {
        return Arrays.stream(Group.values()).filter(group -> group.getName().equalsIgnoreCase(name)).findFirst();
    }

    private Optional<Group> getGroupFor(Emoji emoji) {
        return Arrays.stream(Group.values()).filter(group -> group.getEmojis().contains(emoji)).findFirst();
    }

    private void bulkDeleteMessages(Member member, TextChannel channel, List<Emoji> emojis) {
        var editing = queuedDeletion(member, 0, emojis.size());
        var message = channel.sendMessage(editing).complete();
        bulkDelete(emojis, (done, total) -> {
                    if (!done.equals(total)) message.editMessage(queuedDeletion(member, done, total)).queue();
                },
                () -> {
                    message.delete().queue();
                    EmbedUtils.sendEmbed(channel, member, "Completed deletion", "Successfully deleted " + emojis.size() + " emojis.");
                });
    }

    private MessageEmbed queuedDeletion(Member member, int done, int total) {
        return EmbedUtils.createEmbed(member, "Deleting Emojis", embed -> embed.setDescription("Deleting " + total + " emojis.\n\nProgress: " + done + "/" + total));
    }

    private void bulkDelete(List<Emoji> emojis, BiConsumer<Integer, Integer> deletedEmoji, Runnable onComplete) {
        AtomicInteger completed = new AtomicInteger();
        emojis.forEach(emoji -> {
            var emotes = emojIDE.getJda().getEmotesByName(emoji.getName(), true);
            if (emotes.isEmpty()) return;
            emotes.get(0).delete().complete();
            deletedEmoji.accept(completed.incrementAndGet(), emojis.size());
        });
        onComplete.run();
    }
}
