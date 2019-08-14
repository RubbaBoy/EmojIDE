package com.uddernetworks.emojide.main;

import com.uddernetworks.emojide.discord.*;
import com.uddernetworks.emojide.discord.command.CommandManager;
import com.uddernetworks.emojide.discord.command.EmbedUtils;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.SimpleKeyboardInputManager;
import com.uddernetworks.emojide.web.BasicWebCallbackHandler;
import com.uddernetworks.emojide.web.WebCallbackHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EmojIDE extends ListenerAdapter {

    public static final char ZWS = '\u200b';

    private static ConfigManager configManager;
    private JDA jda;
    private EmojiManager emojiManager;
    private WebCallbackHandler webCallbackHandler;
    private KeyboardInputManager keyboardInputManager;

    public static void main(String[] args) throws LoginException {
        (configManager = new DefaultConfigManager("src/main/resources/secret.conf")).init();

        new JDABuilder()
                .setToken(configManager.getPrimaryToken())
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Programming"))
                .addEventListeners(new EmojIDE())
                .setRateLimitPool(new CustomPool())
                .build();
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        jda = event.getJDA();

        webCallbackHandler = new BasicWebCallbackHandler(this);
        CommandHelp.initHelp(this);
        jda.addEventListener(this.keyboardInputManager = new SimpleKeyboardInputManager(this));
        emojiManager = new DefaultEmojiManager(this, configManager.getServers());
        jda.addEventListener(new CommandManager().registerCommands(new HelpCommand(), new PurgeCommand(this), new IDECommand(this), new EmojiCommand(this)));
        jda.addEventListener(new EmbedUtils());
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public JDA getJda() {
        return jda;
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
}
