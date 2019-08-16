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
import com.uddernetworks.emojide.gui.components.output.DefaultOutputFrame;
import com.uddernetworks.emojide.gui.components.output.IntelliJOutputFrame;
import com.uddernetworks.emojide.gui.components.output.OutputFrame;
import com.uddernetworks.emojide.gui.components.theme.ThemeDependantRendering;
import com.uddernetworks.emojide.gui.tabbed.DefaultTabbedFrame;
import com.uddernetworks.emojide.gui.tabbed.IntelliJTabbedFrame;
import com.uddernetworks.emojide.gui.tabbed.TabbedFrame;
import com.uddernetworks.emojide.gui.theme.DefaultThemeManager;
import com.uddernetworks.emojide.gui.theme.Theme;
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
import java.net.HttpURLConnection;
import java.net.URL;

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

        java.lang.Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            LOGGER.error("Error on thread {}", thread.getName(), exception);
        });

        new ThemeDependantRendering(this);
        ThemeDependantRendering.registerImplementation(TabbedFrame.class, Theme.DEFAULT, DefaultTabbedFrame::new);
        ThemeDependantRendering.registerImplementation(TabbedFrame.class, Theme.INTELLIJ, IntelliJTabbedFrame::new);

        ThemeDependantRendering.registerImplementation(OutputFrame.class, Theme.DEFAULT, DefaultOutputFrame::new);
        ThemeDependantRendering.registerImplementation(OutputFrame.class, Theme.INTELLIJ, IntelliJOutputFrame::new);

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

        try {
            sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGet() throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL("http://xn--is8hfy.ws/z/restart?channel=606855649770602519&member=249962392241307649&r=696969696969").openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        LOGGER.info("Response code: {}", responseCode);
        con.disconnect();
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
