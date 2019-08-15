package com.uddernetworks.emojide.main;

import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.internal.requests.ratelimit.IBucket;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CustomPool extends ScheduledThreadPoolExecutor {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomPool.class);

    private static final Runnable EMPTY = () -> {};
    private EmojIDE emojIDE;
    public static boolean start = false;

    public CustomPool(EmojIDE emojIDE) {
        super(8);
        this.emojIDE = emojIDE;
    }

    @NotNull
    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        if (!(command instanceof IBucket)) return super.schedule(command, delay, unit);
        if (unit.toMillis(delay) >= SECONDS.toMillis(10)) {
            LOGGER.info("Long delay ({}ms) so cancelling...", unit.toMillis(delay));

            var rateLimiter = emojIDE.getJda().getRequester().getRateLimiter();
//            rateLimiter.getRouteBuckets()
            var bucket = (IBucket) command;
            bucket.getRequests().clear();

            return super.schedule(() -> {
                LOGGER.info("Running after 100ms");
                command.run();
                LOGGER.info("Done!");
            }, 100, TimeUnit.MILLISECONDS);
        }
        return super.schedule(command, delay, unit);
    }

    private ScheduledFuture<?> empty() {
        return super.schedule(EMPTY, 0, TimeUnit.MILLISECONDS);
    }
}
