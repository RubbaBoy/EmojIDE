package com.uddernetworks.emojide.main;

import com.uddernetworks.emojide.data.BasicDatabaseManager;
import com.uddernetworks.emojide.data.DatabaseManager;
import com.uddernetworks.emojide.data.document.DefaultDocumentManager;
import com.uddernetworks.emojide.data.document.DocumentManager;
import com.uddernetworks.emojide.discord.DocumentTabController;
import com.uddernetworks.emojide.discord.commands.*;
import com.uddernetworks.emojide.discord.commands.choosable.ChoosingListManager;
import com.uddernetworks.emojide.discord.commands.choosable.DefaultChoosingListManager;
import com.uddernetworks.emojide.discord.commands.manager.CommandManager;
import com.uddernetworks.emojide.discord.commands.manager.EmbedUtils;
import com.uddernetworks.emojide.discord.emoji.DefaultEmojiManager;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.font.DefaultFontManager;
import com.uddernetworks.emojide.discord.font.FontManager;
import com.uddernetworks.emojide.gui.theme.DefaultThemeManager;
import com.uddernetworks.emojide.gui.theme.ThemeManager;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.SimpleKeyboardInputManager;
import com.uddernetworks.emojide.web.BasicWebCallbackHandler;
import com.uddernetworks.emojide.web.WebCallbackHandler;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.JDAImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class EmojIDE extends ListenerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(EmojIDE.class);

    public static final char ZWS = '\u200b';

    private static ConfigManager configManager;
    private JDAImpl jda;
    private FontManager fontManager;
    private ThemeManager themeManager;
    private ChoosingListManager choosingListManager;
    private EmojiManager emojiManager;
    private WebCallbackHandler webCallbackHandler;
    private KeyboardInputManager keyboardInputManager;
    private DatabaseManager databaseManager;
    private DocumentManager documentManager;
    private DocumentTabController documentTabController;

    public static void main(String[] args) throws LoginException {
        new EmojIDE().main();
    }

    private void main() throws LoginException {
        (configManager = new DefaultConfigManager("src/main/resources/secret.conf")).init();

        new JDABuilder()
                .setToken(configManager.getPrimaryToken())
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Programming"))
                .addEventListeners(this)
                .setRateLimitPool(new CustomPool(this))
                .build();
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        jda = (JDAImpl) event.getJDA();

        databaseManager = new BasicDatabaseManager(this);
        documentManager = new DefaultDocumentManager(databaseManager);
        databaseManager.init();
        fontManager = new DefaultFontManager(this);
        themeManager = new DefaultThemeManager(this);
        webCallbackHandler = new BasicWebCallbackHandler(this);
        choosingListManager = new DefaultChoosingListManager(this);
        CommandHelp.initHelp(this);
        jda.addEventListener(this.keyboardInputManager = new SimpleKeyboardInputManager(this));
        emojiManager = new DefaultEmojiManager(this, configManager.getServers());
        jda.addEventListener(new CommandManager().registerCommands(new HelpCommand(), new PurgeCommand(this), new IDECommand(this), new EmojiCommand(this)));
        jda.addEventListener(new EmbedUtils());
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public JDAImpl getJda() {
        return jda;
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public ThemeManager getThemeManager() {
        return themeManager;
    }

    public ChoosingListManager getChoosingListManager() {
        return choosingListManager;
    }

    public WebCallbackHandler getWebCallbackHandler() {
        return webCallbackHandler;
    }

    public EmojiManager getEmojiManager() {
        return emojiManager;
    }

    public KeyboardInputManager getKeyboardInputManager() {
        return keyboardInputManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    public DocumentTabController getDocumentTabController() {
        return documentTabController;
    }

    public void setDocumentTabController(DocumentTabController documentTabController) {
        this.documentTabController = documentTabController;
    }
}
