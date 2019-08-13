package com.uddernetworks.emojide.main;

import com.uddernetworks.emojide.discord.DefaultEmojiManager;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.IDECommand;
import com.uddernetworks.emojide.discord.command.CommandManager;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import com.uddernetworks.emojide.keyboard.SimpleKeyboardInputManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class EmojIDE extends ListenerAdapter {

    private static ConfigManager configManager;
    private JDA jda;
    private EmojiManager emojiManager;
    private KeyboardInputManager keyboardInputManager;

    public static void main(String[] args) throws LoginException {
        (configManager = new DefaultConfigManager("secret.conf")).init();

        new DefaultShardManagerBuilder()
                .setToken(configManager.getPrimaryToken())
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Programming"))
                .addEventListeners(new EmojIDE())
                .build();
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        jda = event.getJDA();

        jda.addEventListener(this.keyboardInputManager = new SimpleKeyboardInputManager(this));
        emojiManager = new DefaultEmojiManager(this, configManager.getServers());
        jda.addEventListener(new CommandManager().registerCommand(new IDECommand()));
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public JDA getJda() {
        return jda;
    }

    public EmojiManager getEmojiManager() {
        return emojiManager;
    }

    public KeyboardInputManager getKeyboardInputManager() {
        return keyboardInputManager;
    }
}
