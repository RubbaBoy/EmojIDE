package com.uddernetworks.emojide.discord.commands;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.discord.commands.manager.*;
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

import static com.uddernetworks.emojide.discord.emoji.StaticEmoji.*;
import static com.uddernetworks.emojide.discord.commands.CommandHelp.space;
import static com.uddernetworks.emojide.main.EmojIDE.ZWS;
import static java.util.function.Predicate.not;

@Command(name = "emoji", aliases = "e", minArgs = 0, maxArgs = 3, permission = Permission.ADMINISTRATOR)
public class EmojiCommand {

    private static Logger LOGGER = LoggerFactory.getLogger(EmojiCommand.class);

    private EmojIDE emojIDE;
    private EmojiManager emojiManager;
    private WebCallbackHandler callbackHandler;
    private Map<String, Emoji> emojis;

    public EmojiCommand(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        emojiManager = emojIDE.getEmojiManager();

        callbackHandler = emojIDE.getWebCallbackHandler();
        callbackHandler.registerCommandCallback("info", (member, channel, query) -> info(member, channel));
        callbackHandler.registerCommandCallback("inspect", Collections.singletonList("emoji"), (member, channel, query) ->
                inspect(member, channel, new ArgumentList(new CommandArg(query.get("emoji")))));
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

        var groups = new StringBuilder();
        Arrays.stream(Group.values()).forEach(group -> groups.append(space(4)).append(group.name).append("\n"));

        EmbedUtils.sendEmbed(channel, member, "Emoji Info", embed -> {
                    embed.setDescription("General information about emojis uploaded and used only by the EmojIDE.")
                            .addField("Emoji Count", emojis.size() + "/" + (totalServers.size() * 50) + " emojis (Max based off of emoji servers available)", false)
                            .addField("Emoji Guilds", "The following are the guilds used for uploading emojis:", false);

                    fields.forEach(section -> embed.addField(String.valueOf(ZWS), section, true));

                    embed.addField("Groups", "The following are groups you can remove via `!emoji grem [group]`\n" + groups, false);
                }
        );
    }

    private List<String> splitToFields(List<String> entries) {
        var fields = new ArrayList<String>();
        var bufferCount = new AtomicInteger();
        var buffer = new StringBuilder();
        entries.forEach(entry -> {
            if (bufferCount.get() >= 20 || buffer.length() + entry.length() > 1024) {
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
        var groupName = args.nextArg().getString();
        var groupOptional = getGroup(groupName);
        if (groupOptional.isEmpty()) {
            EmbedUtils.error(channel, member, "Unknown group name `" + groupName + "`");
            return;
        }

        bulkDeleteMessages(member, channel, groupOptional.get().emojis);
    }

    @Argument(format = "crem *")
    public void cremove(Member member, TextChannel channel, ArgumentList args) {
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
        return Arrays.stream(Group.values()).filter(group -> group.name.equalsIgnoreCase(name)).findFirst();
    }

    private Optional<Group> getGroupFor(Emoji emoji) {
        return Arrays.stream(Group.values()).filter(group -> group.emojis.contains(emoji)).findFirst();
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

    enum Group {
        WELCOME_TITLE("Welcome Title", W_0w0, W_1w0, W_2w0, W_3w0, W_4w0, W_5w0, W_6w0, W_7w0, W_0w1, W_1w1, W_2w1, W_3w1, W_4w1, W_5w1, W_6w1, W_7w1),
        WHITE_TEXT("White Text", A_32, A_33, A_34, A_35, A_36, A_37, A_38, A_39, A_40, A_41, A_42, A_43, A_44, A_45, A_46, A_47, A_48, A_49, A_50, A_51, A_52, A_53, A_54, A_55, A_56, A_57, A_58, A_59, A_60, A_61, A_62, A_63, A_64, A_65, A_66, A_67, A_68, A_69, A_70, A_71, A_72, A_73, A_74, A_75, A_76, A_77, A_78, A_79, A_80, A_81, A_82, A_83, A_84, A_85, A_86, A_87, A_88, A_89, A_90, A_91, A_92, A_93, A_94, A_95, A_96, A_97, A_98, A_99, A_100, A_101, A_102, A_103, A_104, A_105, A_106, A_107, A_108, A_109, A_110, A_111, A_112, A_113, A_114, A_115, A_116, A_117, A_118, A_119, A_120, A_121, A_122, A_123, A_124, A_125, A_126),
        ORANGE_TEXT("Orange Text", A_O32, A_O33, A_O34, A_O35, A_O36, A_O37, A_O38, A_O39, A_O40, A_O41, A_O42, A_O43, A_O44, A_O45, A_O46, A_O47, A_O48, A_O49, A_O50, A_O51, A_O52, A_O53, A_O54, A_O55, A_O56, A_O57, A_O58, A_O59, A_O60, A_O61, A_O62, A_O63, A_O64, A_O65, A_O66, A_O67, A_O68, A_O69, A_O70, A_O71, A_O72, A_O73, A_O74, A_O75, A_O76, A_O77, A_O78, A_O79, A_O80, A_O81, A_O82, A_O83, A_O84, A_O85, A_O86, A_O87, A_O88, A_O89, A_O90, A_O91, A_O92, A_O93, A_O94, A_O95, A_O96, A_O97, A_O98, A_O99, A_O100, A_O101, A_O102, A_O103, A_O104, A_O105, A_O106, A_O107, A_O108, A_O109, A_O110, A_O111, A_O112, A_O113, A_O114, A_O115, A_O116, A_O117, A_O118, A_O119, A_O120, A_O121, A_O122, A_O123, A_O124, A_O125, A_O126),
        GREEN_TEXT("Green Text", A_G32, A_G33, A_G34, A_G35, A_G36, A_G37, A_G38, A_G39, A_G40, A_G41, A_G42, A_G43, A_G44, A_G45, A_G46, A_G47, A_G48, A_G49, A_G50, A_G51, A_G52, A_G53, A_G54, A_G55, A_G56, A_G57, A_G58, A_G59, A_G60, A_G61, A_G62, A_G63, A_G64, A_G65, A_G66, A_G67, A_G68, A_G69, A_G70, A_G71, A_G72, A_G73, A_G74, A_G75, A_G76, A_G77, A_G78, A_G79, A_G80, A_G81, A_G82, A_G83, A_G84, A_G85, A_G86, A_G87, A_G88, A_G89, A_G90, A_G91, A_G92, A_G93, A_G94, A_G95, A_G96, A_G97, A_G98, A_G99, A_G100, A_G101, A_G102, A_G103, A_G104, A_G105, A_G106, A_G107, A_G108, A_G109, A_G110, A_G111, A_G112, A_G113, A_G114, A_G115, A_G116, A_G117, A_G118, A_G119, A_G120, A_G121, A_G122, A_G123, A_G124, A_G125, A_G126),
        BLUE_TEXT("Blue Text", A_B32, A_B33, A_B34, A_B35, A_B36, A_B37, A_B38, A_B39, A_B40, A_B41, A_B42, A_B43, A_B44, A_B45, A_B46, A_B47, A_B48, A_B49, A_B50, A_B51, A_B52, A_B53, A_B54, A_B55, A_B56, A_B57, A_B58, A_B59, A_B60, A_B61, A_B62, A_B63, A_B64, A_B65, A_B66, A_B67, A_B68, A_B69, A_B70, A_B71, A_B72, A_B73, A_B74, A_B75, A_B76, A_B77, A_B78, A_B79, A_B80, A_B81, A_B82, A_B83, A_B84, A_B85, A_B86, A_B87, A_B88, A_B89, A_B90, A_B91, A_B92, A_B93, A_B94, A_B95, A_B96, A_B97, A_B98, A_B99, A_B100, A_B101, A_B102, A_B103, A_B104, A_B105, A_B106, A_B107, A_B108, A_B109, A_B110, A_B111, A_B112, A_B113, A_B114, A_B115, A_B116, A_B117, A_B118, A_B119, A_B120, A_B121, A_B122, A_B123, A_B124, A_B125, A_B126),
        GRAY_TEXT("Gray Text", A_A32, A_A33, A_A34, A_A35, A_A36, A_A37, A_A38, A_A39, A_A40, A_A41, A_A42, A_A43, A_A44, A_A45, A_A46, A_A47, A_A48, A_A49, A_A50, A_A51, A_A52, A_A53, A_A54, A_A55, A_A56, A_A57, A_A58, A_A59, A_A60, A_A61, A_A62, A_A63, A_A64, A_A65, A_A66, A_A67, A_A68, A_A69, A_A70, A_A71, A_A72, A_A73, A_A74, A_A75, A_A76, A_A77, A_A78, A_A79, A_A80, A_A81, A_A82, A_A83, A_A84, A_A85, A_A86, A_A87, A_A88, A_A89, A_A90, A_A91, A_A92, A_A93, A_A94, A_A95, A_A96, A_A97, A_A98, A_A99, A_A100, A_A101, A_A102, A_A103, A_A104, A_A105, A_A106, A_A107, A_A108, A_A109, A_A110, A_A111, A_A112, A_A113, A_A114, A_A115, A_A116, A_A117, A_A118, A_A119, A_A120, A_A121, A_A122, A_A123, A_A124, A_A125, A_A126),
        LIGHT_GRAY_TEXT("Light Gray Text", A_L32, A_L33, A_L34, A_L35, A_L36, A_L37, A_L38, A_L39, A_L40, A_L41, A_L42, A_L43, A_L44, A_L45, A_L46, A_L47, A_L48, A_L49, A_L50, A_L51, A_L52, A_L53, A_L54, A_L55, A_L56, A_L57, A_L58, A_L59, A_L60, A_L61, A_L62, A_L63, A_L64, A_L65, A_L66, A_L67, A_L68, A_L69, A_L70, A_L71, A_L72, A_L73, A_L74, A_L75, A_L76, A_L77, A_L78, A_L79, A_L80, A_L81, A_L82, A_L83, A_L84, A_L85, A_L86, A_L87, A_L88, A_L89, A_L90, A_L91, A_L92, A_L93, A_L94, A_L95, A_L96, A_L97, A_L98, A_L99, A_L100, A_L101, A_L102, A_L103, A_L104, A_L105, A_L106, A_L107, A_L108, A_L109, A_L110, A_L111, A_L112, A_L113, A_L114, A_L115, A_L116, A_L117, A_L118, A_L119, A_L120, A_L121, A_L122, A_L123, A_L124, A_L125, A_L126),
        FIRA_WHITE_TEXT("Fira White Text", A_F32, A_F33, A_F34, A_F35, A_F36, A_F37, A_F38, A_F39, A_F40, A_F41, A_F42, A_F43, A_F44, A_F45, A_F46, A_F47, A_F48, A_F49, A_F50, A_F51, A_F52, A_F53, A_F54, A_F55, A_F56, A_F57, A_F58, A_F59, A_F60, A_F61, A_F62, A_F63, A_F64, A_F65, A_F66, A_F67, A_F68, A_F69, A_F70, A_F71, A_F72, A_F73, A_F74, A_F75, A_F76, A_F77, A_F78, A_F79, A_F80, A_F81, A_F82, A_F83, A_F84, A_F85, A_F86, A_F87, A_F88, A_F89, A_F90, A_F91, A_F92, A_F93, A_F94, A_F95, A_F96, A_F97, A_F98, A_F99, A_F100, A_F101, A_F102, A_F103, A_F104, A_F105, A_F106, A_F107, A_F108, A_F109, A_F110, A_F111, A_F112, A_F113, A_F114, A_F115, A_F116, A_F117, A_F118, A_F119, A_F120, A_F121, A_F122, A_F123, A_F124, A_F125, A_F126),
        FIRA_ORANGE_TEXT("Fira Orange Text", A_FO32, A_FO33, A_FO34, A_FO35, A_FO36, A_FO37, A_FO38, A_FO39, A_FO40, A_FO41, A_FO42, A_FO43, A_FO44, A_FO45, A_FO46, A_FO47, A_FO48, A_FO49, A_FO50, A_FO51, A_FO52, A_FO53, A_FO54, A_FO55, A_FO56, A_FO57, A_FO58, A_FO59, A_FO60, A_FO61, A_FO62, A_FO63, A_FO64, A_FO65, A_FO66, A_FO67, A_FO68, A_FO69, A_FO70, A_FO71, A_FO72, A_FO73, A_FO74, A_FO75, A_FO76, A_FO77, A_FO78, A_FO79, A_FO80, A_FO81, A_FO82, A_FO83, A_FO84, A_FO85, A_FO86, A_FO87, A_FO88, A_FO89, A_FO90, A_FO91, A_FO92, A_FO93, A_FO94, A_FO95, A_FO96, A_FO97, A_FO98, A_FO99, A_FO100, A_FO101, A_FO102, A_FO103, A_FO104, A_FO105, A_FO106, A_FO107, A_FO108, A_FO109, A_FO110, A_FO111, A_FO112, A_FO113, A_FO114, A_FO115, A_FO116, A_FO117, A_FO118, A_FO119, A_FO120, A_FO121, A_FO122, A_FO123, A_FO124, A_FO125, A_FO126),
        FIRA_GREEN_TEXT("Fira Green Text", A_FG32, A_FG33, A_FG34, A_FG35, A_FG36, A_FG37, A_FG38, A_FG39, A_FG40, A_FG41, A_FG42, A_FG43, A_FG44, A_FG45, A_FG46, A_FG47, A_FG48, A_FG49, A_FG50, A_FG51, A_FG52, A_FG53, A_FG54, A_FG55, A_FG56, A_FG57, A_FG58, A_FG59, A_FG60, A_FG61, A_FG62, A_FG63, A_FG64, A_FG65, A_FG66, A_FG67, A_FG68, A_FG69, A_FG70, A_FG71, A_FG72, A_FG73, A_FG74, A_FG75, A_FG76, A_FG77, A_FG78, A_FG79, A_FG80, A_FG81, A_FG82, A_FG83, A_FG84, A_FG85, A_FG86, A_FG87, A_FG88, A_FG89, A_FG90, A_FG91, A_FG92, A_FG93, A_FG94, A_FG95, A_FG96, A_FG97, A_FG98, A_FG99, A_FG100, A_FG101, A_FG102, A_FG103, A_FG104, A_FG105, A_FG106, A_FG107, A_FG108, A_FG109, A_FG110, A_FG111, A_FG112, A_FG113, A_FG114, A_FG115, A_FG116, A_FG117, A_FG118, A_FG119, A_FG120, A_FG121, A_FG122, A_FG123, A_FG124, A_FG125, A_FG126),
        FIRA_BLUE_TEXT("Fira Blue Text", A_FB32, A_FB33, A_FB34, A_FB35, A_FB36, A_FB37, A_FB38, A_FB39, A_FB40, A_FB41, A_FB42, A_FB43, A_FB44, A_FB45, A_FB46, A_FB47, A_FB48, A_FB49, A_FB50, A_FB51, A_FB52, A_FB53, A_FB54, A_FB55, A_FB56, A_FB57, A_FB58, A_FB59, A_FB60, A_FB61, A_FB62, A_FB63, A_FB64, A_FB65, A_FB66, A_FB67, A_FB68, A_FB69, A_FB70, A_FB71, A_FB72, A_FB73, A_FB74, A_FB75, A_FB76, A_FB77, A_FB78, A_FB79, A_FB80, A_FB81, A_FB82, A_FB83, A_FB84, A_FB85, A_FB86, A_FB87, A_FB88, A_FB89, A_FB90, A_FB91, A_FB92, A_FB93, A_FB94, A_FB95, A_FB96, A_FB97, A_FB98, A_FB99, A_FB100, A_FB101, A_FB102, A_FB103, A_FB104, A_FB105, A_FB106, A_FB107, A_FB108, A_FB109, A_FB110, A_FB111, A_FB112, A_FB113, A_FB114, A_FB115, A_FB116, A_FB117, A_FB118, A_FB119, A_FB120, A_FB121, A_FB122, A_FB123, A_FB124, A_FB125, A_FB126),
        FIRA_GRAY_TEXT("Fira Gray Text", A_FA32, A_FA33, A_FA34, A_FA35, A_FA36, A_FA37, A_FA38, A_FA39, A_FA40, A_FA41, A_FA42, A_FA43, A_FA44, A_FA45, A_FA46, A_FA47, A_FA48, A_FA49, A_FA50, A_FA51, A_FA52, A_FA53, A_FA54, A_FA55, A_FA56, A_FA57, A_FA58, A_FA59, A_FA60, A_FA61, A_FA62, A_FA63, A_FA64, A_FA65, A_FA66, A_FA67, A_FA68, A_FA69, A_FA70, A_FA71, A_FA72, A_FA73, A_FA74, A_FA75, A_FA76, A_FA77, A_FA78, A_FA79, A_FA80, A_FA81, A_FA82, A_FA83, A_FA84, A_FA85, A_FA86, A_FA87, A_FA88, A_FA89, A_FA90, A_FA91, A_FA92, A_FA93, A_FA94, A_FA95, A_FA96, A_FA97, A_FA98, A_FA99, A_FA100, A_FA101, A_FA102, A_FA103, A_FA104, A_FA105, A_FA106, A_FA107, A_FA108, A_FA109, A_FA110, A_FA111, A_FA112, A_FA113, A_FA114, A_FA115, A_FA116, A_FA117, A_FA118, A_FA119, A_FA120, A_FA121, A_FA122, A_FA123, A_FA124, A_FA125, A_FA126),
        FIRA_LIGHT_GRAY_TEXT("Fira Light Gray Text", A_FL32, A_FL33, A_FL34, A_FL35, A_FL36, A_FL37, A_FL38, A_FL39, A_FL40, A_FL41, A_FL42, A_FL43, A_FL44, A_FL45, A_FL46, A_FL47, A_FL48, A_FL49, A_FL50, A_FL51, A_FL52, A_FL53, A_FL54, A_FL55, A_FL56, A_FL57, A_FL58, A_FL59, A_FL60, A_FL61, A_FL62, A_FL63, A_FL64, A_FL65, A_FL66, A_FL67, A_FL68, A_FL69, A_FL70, A_FL71, A_FL72, A_FL73, A_FL74, A_FL75, A_FL76, A_FL77, A_FL78, A_FL79, A_FL80, A_FL81, A_FL82, A_FL83, A_FL84, A_FL85, A_FL86, A_FL87, A_FL88, A_FL89, A_FL90, A_FL91, A_FL92, A_FL93, A_FL94, A_FL95, A_FL96, A_FL97, A_FL98, A_FL99, A_FL100, A_FL101, A_FL102, A_FL103, A_FL104, A_FL105, A_FL106, A_FL107, A_FL108, A_FL109, A_FL110, A_FL111, A_FL112, A_FL113, A_FL114, A_FL115, A_FL116, A_FL117, A_FL118, A_FL119, A_FL120, A_FL121, A_FL122, A_FL123, A_FL124, A_FL125, A_FL126);

        private String name;
        private List<Emoji> emojis;

        Group(String name, Emoji... emojis) {
            this.name = name;
            this.emojis = Arrays.asList(emojis);
        }

        public String getName() {
            return name;
        }

        public List<Emoji> getEmojis() {
            return emojis;
        }
    }

}
