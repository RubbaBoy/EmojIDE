package com.uddernetworks.emojide.discord.commands;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.uddernetworks.emojide.discord.DefaultDocumentTabController;
import com.uddernetworks.emojide.discord.commands.choosable.ChoosingList;
import com.uddernetworks.emojide.discord.commands.manager.*;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.discord.font.Font;
import com.uddernetworks.emojide.discord.font.FontManager;
import com.uddernetworks.emojide.gui.EmptyContainerFrame;
import com.uddernetworks.emojide.gui.WelcomeFrame;
import com.uddernetworks.emojide.gui.components.Displayer;
import com.uddernetworks.emojide.gui.components.MockupWebpageDisplayer;
import com.uddernetworks.emojide.gui.tabbed.TabbedFrame;
import com.uddernetworks.emojide.gui.theme.Theme;
import com.uddernetworks.emojide.gui.theme.ThemeManager;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@Command(name = "ide", aliases = "i", minArgs = 0, maxArgs = 2, permission = Permission.ADMINISTRATOR)
public class IDECommand {

    private static Logger LOGGER = LoggerFactory.getLogger(IDECommand.class);

    private EmojIDE emojIDE;
    private EmojiManager emojiManager;
    private FontManager fontManager;
    private ThemeManager themeManager;
    private FileConfig config;
    private Displayer displayer;
    private boolean displaying = false;

    private ChoosingList<Font> fontChooser;
    private ChoosingList<Theme> themeChooser;

    public IDECommand(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
        emojiManager = emojIDE.getEmojiManager();
        fontManager = emojIDE.getFontManager();
        themeManager = emojIDE.getThemeManager();
        config = EmojIDE.getConfigManager().getConfig();

        fontChooser = emojIDE.getChoosingListManager().createChoosingList(emojIDE, fontManager::getActive, fontManager::setActive, Font.class);
        themeChooser = emojIDE.getChoosingListManager().createChoosingList(emojIDE, themeManager::getActive, themeManager::setActive, Theme.class);

        var callbackHandler = emojIDE.getWebCallbackHandler();
        callbackHandler.registerCommandCallback("setchannel", (member, channel, query) -> setChannel(member, channel));
        callbackHandler.registerCommandCallback("fonts", (member, channel, query) -> fonts(member, channel));
        callbackHandler.registerCommandCallback("themes", (member, channel, query) -> themes(member, channel));
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
        fontChooser.sendEmbed(member, channel, "Fonts", "The following are the fonts used by the IDE. Either click the " + StaticEmoji.UNSELECTED.getDisplay() + " to apply the font, or type `!ide setfont \"name\"`");
    }

    @Argument(format = "setfont *")
    public void setFont(Member member, TextChannel channel, ArgumentList args) {
        fontChooser.manualChoose(channel, member, args.nextArg().getString(), "font", fontManager::setActive, "Changed Font", font -> "Changed active font to " + font.getName());
    }

    @Argument(format = "themes")
    public void themes(Member member, TextChannel channel) {
        themeChooser.sendEmbed(member, channel, "Themes", "The following are the themes available in the IDE. Either click the " + StaticEmoji.UNSELECTED.getDisplay() + " to apply the theme, or type `!ide settheme \"name\"`");
    }

    @Argument(format = "settheme *")
    public void setTheme(Member member, TextChannel channel, ArgumentList args) {
        themeChooser.manualChoose(channel, member, args.nextArg().getString(), "theme", themeManager::setActive, "Changed Theme", font -> "Changed active theme to " + font.getName());
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

        var documentManager = emojIDE.getDocumentManager();
        documentManager.getAllDocuments().thenAccept(documents -> {
            TabbedFrame tabbedFrame;
            // TODO: Change Displayer Implementations
            (displayer = new MockupWebpageDisplayer(emojIDE, channel))
//            (displayer = new MockupImageDisplayer(emojIDE, channel))
//            (displayer = new CachedDisplayer(emojIDE, channel, true))
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
