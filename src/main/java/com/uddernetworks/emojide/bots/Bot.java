package com.uddernetworks.emojide.bots;

import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Bot extends ListenerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    private AtomicBoolean ready = new AtomicBoolean();

    private JDA jda;
    private boolean enabled = false;
    private String token;
    private String name;

    public Bot(String token) {
        this.token = token;
    }

    public Bot start() throws LoginException {
        this.jda = new JDABuilder()
                .setToken(this.token)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Uploading"))
                .addEventListeners(this)
                .build();
        while (!this.ready.get()) Thread.sleep(100);
        return this;
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        var servers = EmojIDE.getConfigManager().getServers();
        if (!this.jda.getGuilds().stream().map(Guild::getIdLong).collect(Collectors.toList()).containsAll(servers)) {
            LOGGER.error("The bot is not in all the uploading servers! It will be disabled and may cause issues in the future.");
        } else {
            LOGGER.info("Bot is ready!");
            this.enabled = true;
        }

        this.name = jda.getSelfUser().getName();

        this.ready.set(true);
    }

    public <T> T runTask(Function<Bot, T> task) {
        if (!enabled) throw new RuntimeException("Bot is disabled and can't run the given task.");
        return task.apply(this);
    }

    public JDA getJda() {
        return jda;
    }

    public String getName() {
        return name;
    }
}
