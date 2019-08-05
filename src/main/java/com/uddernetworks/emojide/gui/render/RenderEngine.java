package com.uddernetworks.emojide.gui.render;

import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RenderEngine {

    private static Logger LOGGER = LoggerFactory.getLogger(RenderEngine.class);

    private static final List<RenderEntry> renderQueue = Collections.synchronizedList(new ArrayList<>());

    public static void start() {
        CompletableFuture.runAsync(() -> {
            try {
                while (true) {
                    if (renderQueue.isEmpty()) continue;
                    RenderEntry entry = renderQueue.get(0);
                    if (entry == null) continue;

                    if (!entry.isAction()) {
                        ((RenderBreakpoint) entry).run();
                        continue;
                    }

                    var render = (RenderAction) entry;

                    // See if the next head is with the same message as the previous. If so, only do the newer one.

                    var similar = new ArrayList<>(renderQueue).stream().filter(renderEntry -> areSimilar((RenderAction) entry, renderEntry)).collect(Collectors.toList());
                    if (!similar.contains(render)) similar.add(render);
                    renderQueue.removeAll(similar);
                    render = (RenderAction) similar.get(similar.size() - 1);

                    Message message;
                    if (render.isEditing()) {
                        if (render.getEmbedContent() == null) {
                            message = render.getEditing().editMessage(render.getContent()).complete();
                        } else {
                            message = render.getEditing().editMessage(render.getEmbedContent()).complete();
                        }
                    } else {
                        if (render.getEmbedContent() == null) {
                            message = render.getChannel().sendMessage(render.getContent()).complete();
                        } else {
                            message = render.getChannel().sendMessage(render.getEmbedContent()).complete();
                        }
                    }

                    render.complete(message);

                    RenderEntry render2 = get(renderQueue, 0);

                    while (render2 != null && !render2.isAction()) {
                        renderQueue.remove(render2);
                        ((RenderBreakpoint) render2).run();
                        render2 = get(renderQueue, 0);
                    }

                    // TODO: Improve render calculation
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static <T> T get(List<T> list, int index) {
        if (list.size() <= index) return null;
        return list.get(index);
    }

    private static boolean areSimilar(RenderAction entry1, RenderEntry entry2) {
        if (entry1 == entry2) return true;
        if (entry1.equals(entry2)) return true;
        if (!entry1.isEditing()) return false;
        if (entry2 == null) return false;
        if (!entry2.isAction()) return false;
        var entryAction = (RenderAction) entry2;
        if (!entryAction.isEditing()) return false;
        if (entryAction.getEditingId() != entry1.getEditingId()) return false;
        return true;
    }

    public static void queueSend(TextChannel channel, String content, Consumer<Message> onComplete) {
        LOGGER.info("Queuing send {}", content);
        renderQueue.add(new RenderAction(channel, content, onComplete));
    }

    public static void queueSend(TextChannel channel, MessageEmbed embedContent, Consumer<Message> onComplete) {
        LOGGER.info("Queuing send {}", embedContent);
        renderQueue.add(new RenderAction(channel, embedContent, onComplete));
    }

    public static void queueEdit(Message message, String content, Consumer<Message> onComplete) {
        LOGGER.info("Queuing edit");
        renderQueue.add(new RenderAction(message, content, onComplete));
    }

    public static void queueEdit(Message message, MessageEmbed embedContent, Consumer<Message> onComplete) {
        LOGGER.info("Queuing edit");
        renderQueue.add(new RenderAction(message, embedContent, onComplete));
    }

    public static void breakpoint(Runnable runnable) {
        LOGGER.info("Queuing breakpoint");
        renderQueue.add(new RenderBreakpoint(runnable));
    }
}
