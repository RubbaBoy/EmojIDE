package com.uddernetworks.emojide.main;

import com.uddernetworks.emojide.discord.CommandListener;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.input.KeyboardInputManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class EmojIDE extends ListenerAdapter {

    private JDA jda;
    private EmojiManager emojiManager;
    private KeyboardInputManager keyboardInputManager;

    public static void main(String[] args) throws LoginException {
        new DefaultShardManagerBuilder()
            .setToken(System.getProperty("discord.token"))
            .setStatus(OnlineStatus.ONLINE)
            .setActivity(Activity.playing("Programming"))
            .addEventListeners(new EmojIDE())
            .build();
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        this.jda = event.getJDA();

        this.jda.addEventListener(new CommandListener(this));
        this.emojiManager = new EmojiManager(this.jda);
        this.jda.addEventListener(this.keyboardInputManager = new KeyboardInputManager(this));
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
