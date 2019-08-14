package com.uddernetworks.emojide.discord.commands;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.uddernetworks.emojide.data.document.Document;
import com.uddernetworks.emojide.discord.DefaultDocumentTabController;
import com.uddernetworks.emojide.discord.commands.manager.*;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.discord.font.Font;
import com.uddernetworks.emojide.discord.font.FontManager;
import com.uddernetworks.emojide.gui.EmptyContainerFrame;
import com.uddernetworks.emojide.gui.TabbedFrame;
import com.uddernetworks.emojide.gui.WelcomeFrame;
import com.uddernetworks.emojide.gui.components.CachedDisplayer;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.EmojiContainer;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.uddernetworks.emojide.discord.commands.CommandHelp.space;

@Command(name = "ide", aliases = "i", minArgs = 0, maxArgs = 2, permission = Permission.ADMINISTRATOR)
public class IDECommand {

    private static Logger LOGGER = LoggerFactory.getLogger(IDECommand.class);

    private EmojIDE emojIDE;
    private EmojiManager emojiManager;
    private FontManager fontManager;
    private FileConfig config;
    private Displayer displayer;
    private boolean displaying = false;

    public IDECommand(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        emojiManager = emojIDE.getEmojiManager();
        fontManager = emojIDE.getFontManager();
        config = EmojIDE.getConfigManager().getConfig();

        var callbackHandler = emojIDE.getWebCallbackHandler();
        callbackHandler.registerCommandCallback("setchannel", (member, channel, query) -> setChannel(member, channel));
        callbackHandler.registerCommandCallback("fonts", (member, channel, query) -> fonts(member, channel));
        callbackHandler.registerCommandCallback("apply", Arrays.asList("font", "originating"), (member, channel, query) -> {
            var activatingString = query.get("font");
            if (!StringUtils.isNumeric(activatingString)) return;
            var activating = Integer.parseInt(activatingString);
            if (activating < 0 || activating >= Font.values().length) return;
            var originatingString = query.get("originating");
            if (!StringUtils.isNumeric(originatingString)) return;
            activateFont(member, channel, Long.parseLong(originatingString), activating);
        });
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

    @Argument(format = "fonts")
    public void fonts(Member member, TextChannel channel) {
        var emptyQuery = Map.of("member", member.getId(), "channel", channel.getId());

        var editing = EmbedUtils.sendEmbed(channel, member, "Fonts", embed -> embed.setDescription("The following are the fonts used by the IDE. Either click the " + StaticEmoji.UNSELECTED.getDisplay() + " to apply the font, or type `!ide setfont \"name\"`"));
        editing.editMessage(createFontsEmbed(editing, emptyQuery)).queue();
    }

    private void activateFont(Member member, TextChannel channel, long messageId, int font) {
        var emptyQuery = Map.of("member", member.getId(), "channel", channel.getId());
        fontManager.setActive(Font.values()[font]);
        channel.retrieveMessageById(messageId).submit().thenAccept(message -> {
            if (message == null || message.getEmbeds().size() != 1 || message.getAuthor().getIdLong() != emojIDE.getJda().getSelfUser().getIdLong()) return;
            message.editMessage(createFontsEmbed(message, emptyQuery)).queue();
        });
    }

    private MessageEmbed createFontsEmbed(Message message, Map<String, String> emptyQuery) {
        var editingEmbed = new EmbedBuilder(message.getEmbeds().get(0));
        var webCallbackHandler = emojIDE.getWebCallbackHandler();
        editingEmbed.clearFields();

        var paddedLength = Arrays.stream(Font.values()).mapToInt(font -> font.getName().length()).max().orElse(0) + 2;

        Arrays.stream(Font.values()).forEach(font -> {
            boolean active = fontManager.getActive() == font;
            var emojiName = paddedEmoji(font.getName().chars().mapToObj(cha -> emojiManager.getTextEmoji((char) cha, font).getDisplay()).collect(Collectors.joining(" ")), paddedLength);

            var query = new HashMap<>(emptyQuery);
            query.put("font", String.valueOf(font.ordinal()));
            query.put("originating", message.getId());
            editingEmbed.addField(font.getName(), emojiName + space(4) +
                    (active ? StaticEmoji.SELECT.getDisplay() : webCallbackHandler.generateMdLink(StaticEmoji.UNSELECTED.getDisplay(), "apply", query)), true);
        });

        return editingEmbed.build();
    }

    private String paddedEmoji(String input, int length) {
        return input.replaceAll("\\s+", "") + StaticEmoji.TRANSPARENT.getDisplay().repeat(Math.max(0, length - input.split("\\s+").length));
    }

    @Argument(format = "setfont *")
    public void setFont(Member member, TextChannel channel, ArgumentList args) {
        var fontName = args.nextArg().getString();
        var fontOptional = Font.fromName(fontName);
        if (fontOptional.isEmpty()) {
            EmbedUtils.error(channel, member, "Unknown font `" + fontName + "`");
            return;
        }

        var font = fontOptional.get();
        fontManager.setActive(font);
        EmbedUtils.sendEmbed(channel, member, "Changed Font", "Changed active font to " + font.getName());
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
        if (displaying) {
            LOGGER.info("Already displaying!");
            return;
        }

        displaying = true;
        LOGGER.info("Creating displayer...");

        var text = "function myMethod(arg) {\n" +
                "   console.log('Arg is ' + arg + '!');\n" +
                "}\n" +
                "\n" +
                "myMethod('Hello World');\n";

        var documentManager = emojIDE.getDocumentManager();
        documentManager.getAllDocuments().thenAccept(documents -> {
            TabbedFrame tabbedFrame;
            (displayer = new CachedDisplayer(emojIDE, channel, true))
                    .setChild(new EmptyContainerFrame(displayer, 58, 23)
                                    .addChild(tabbedFrame = new TabbedFrame(displayer, 58, 23)
                                            .addTab("Welcome", new WelcomeFrame(displayer)), 0, 0));

            var tabController = new DefaultDocumentTabController(emojIDE, displayer, tabbedFrame);
            emojIDE.setDocumentTabController(tabController);

            documents.forEach(tabController::addTab);
            displayer.update();
        });
    }

    private void commandStop(TextChannel channel) {
        displaying = false;
        this.emojIDE.getKeyboardInputManager().removeKeyboard();
        if (this.displayer != null) this.displayer.stop(channel);
        var selfId = emojIDE.getJda().getSelfUser().getIdLong();
        channel.purgeMessages(channel.getIterableHistory()
                .stream()
                .filter(messageHistory -> messageHistory.getAuthor().getIdLong() == selfId)
                .collect(Collectors.toList()));
    }
}
