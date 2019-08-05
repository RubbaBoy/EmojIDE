package com.uddernetworks.emojide.main;

import com.uddernetworks.emojide.discord.CommandListener;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.image.ImageManager;
import com.uddernetworks.emojide.keyboard.KeyboardInputManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.List;

public class EmojIDE extends ListenerAdapter {

    private static final List<Long> EMOJI_SERVERS = List.of(606855649770602517L, 593887383431151647L, 593887422459412542L, 593887468189777922L, 593887502360772638L, 593986129376706560L, 593986159747530762L);

    private JDA jda;
    private EmojiManager emojiManager;
    private ImageManager imageManager;
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
        this.emojiManager = new EmojiManager(this.jda, EMOJI_SERVERS);
        this.imageManager = new ImageManager(this);
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
