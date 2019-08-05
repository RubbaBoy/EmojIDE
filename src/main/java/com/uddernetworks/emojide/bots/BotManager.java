package com.uddernetworks.emojide.bots;

import com.uddernetworks.emojide.main.EmojIDE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class BotManager {

    private static Logger LOGGER = LoggerFactory.getLogger(BotManager.class);

    private EmojIDE emojIDE;
    private final Queue<BotTask> tasks = new LinkedBlockingQueue<>();
    private final List<Bot> bots = Collections.synchronizedList(new ArrayList<>());
    private AtomicInteger botIndex = new AtomicInteger();

    public BotManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    public void connectBots() {
        EmojIDE.getConfigManager().getUploadingTokens().forEach(token -> {
            try {
                this.bots.add(new Bot(token).start());
                LOGGER.info("Connected uploading bot");
            } catch (LoginException e) {
                LOGGER.error("There was a problem logging into an uploader bot", e);
            }
        });
    }

    public void startTasks() {
//        LOGGER.info("Starting bot task loop");
//        final int botCount = this.bots.size();
//        CompletableFuture.runAsync(() -> {
//            while (true) {
//                try {
//                    var task = this.tasks.poll();
//                    if (task != null) {
//                        this.bots.get(botIndex.getAndIncrement()).runTask(task);
//                        if (botIndex.get() >= botCount) botIndex.set(0);
//                    }
//                } catch (Exception e) {
//                    LOGGER.error("Error while running BotTask", e);
//                }
//            }
//        });
    }

    public <T> T queueTask(Function<Bot, T> task) {
        if (botIndex.get() >= this.bots.size()) botIndex.set(0);
        return this.bots.get(botIndex.getAndIncrement()).runTask(task);
    }
}
