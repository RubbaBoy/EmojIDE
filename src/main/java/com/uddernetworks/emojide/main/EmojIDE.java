package com.uddernetworks.emojide.main;

import com.uddernetworks.emojide.bots.BotManager;
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

public class EmojIDE extends ListenerAdapter {

    private static ConfigManager configManager;
    private JDA jda;
    private BotManager botManager;
    private EmojiManager emojiManager;
//    private ImageManager imageManager;
    private KeyboardInputManager keyboardInputManager;

    public static void main(String[] args) throws LoginException {
        (configManager = new ConfigManager("secret.conf")).init();

        new DefaultShardManagerBuilder()
            .setToken(configManager.getPrimaryToken())
            .setStatus(OnlineStatus.ONLINE)
            .setActivity(Activity.playing("Programming"))
            .addEventListeners(new EmojIDE())
            .build();
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        this.jda = event.getJDA();

        this.botManager = new BotManager(this);
        this.botManager.connectBots();
        this.botManager.startTasks();

        this.jda.addEventListener(new CommandListener(this));
        this.emojiManager = new EmojiManager(this, configManager.getServers());
//        this.imageManager = new ImageManager(this);
        this.jda.addEventListener(this.keyboardInputManager = new KeyboardInputManager(this));
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public JDA getJda() {
        return jda;
    }

    public BotManager getBotManager() {
        return botManager;
    }

    public EmojiManager getEmojiManager() {
        return emojiManager;
    }

//    public ImageManager getImageManager() {
//        return imageManager;
//    }

    public KeyboardInputManager getKeyboardInputManager() {
        return keyboardInputManager;
    }
}
