package com.uddernetworks.emojide.gui.render;

import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Has an internal loop to process {@link RenderAction}s, to make the creation and editing of emoji messages much more
 * efficient and consistent.
 */
public class RenderEngine {

    private static Logger LOGGER = LoggerFactory.getLogger(RenderEngine.class);
    private static final Consumer<Message> EMPTY = i -> {};
    private static final List<RenderEntry> renderQueue = Collections.synchronizedList(new ArrayList<>());

    /**
     * Starts the internal render loop of 1 {@link RenderAction} per second, processing {@link RenderBreakpoint}s as
     * soon as possible when they come up. If multiple {@link RenderAction}s come up with the same editing status,
     * message IDs, etc. the most recent one is processed to allow for much faster rendering, skipping useless 'frames'
     * essential due to Discord's heavy rate limiting.
     */
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

                    try {
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
                    } catch (ErrorResponseException e) {
                        LOGGER.error("Unbknown message!");
                    }

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
        return entryAction.getEditingId() == entry1.getEditingId();
    }

    /**
     * Queues the sending of a message in the given {@link TextChannel} and content.
     *
     * @param channel The {@link TextChannel} to send the message to
     * @param content The content of the message
     */
    public static void queueSend(TextChannel channel, String content) {
        queueSend(channel, content, EMPTY);
    }

    /**
     * Queues the sending of a message in the given {@link TextChannel} and content, invoking the given consumer when
     * the message has been sent.
     *
     * @param channel    The {@link TextChannel} to send the message to
     * @param content    The content of the message
     * @param onComplete The consumer to invoke when the action has been completed, giving the sent message
     */
    public static void queueSend(TextChannel channel, String content, Consumer<Message> onComplete) {
        LOGGER.debug("Queuing send {}", content);
        renderQueue.add(new RenderAction(channel, content, onComplete));
    }

    /**
     * Queues the sending of a message in the given {@link TextChannel} and content.
     *
     * @param channel      The {@link TextChannel} to send the message to
     * @param embedContent The content of the message
     */
    public static void queueSend(TextChannel channel, MessageEmbed embedContent) {
        queueSend(channel, embedContent, EMPTY);
    }

    /**
     * Queues the sending of a message in the given {@link TextChannel} and content, invoking the given consumer when
     * the message has been sent.
     *
     * @param channel      The {@link TextChannel} to send the message to
     * @param embedContent The content of the message
     * @param onComplete   The consumer to invoke when the action has been completed, giving the sent message
     */
    public static void queueSend(TextChannel channel, MessageEmbed embedContent, Consumer<Message> onComplete) {
        LOGGER.debug("Queuing send {}", embedContent);
        renderQueue.add(new RenderAction(channel, embedContent, onComplete));
    }

    /**
     * Queues the editing of a message to the given content.
     *
     * @param message The {@link Message} to send edit
     * @param content The new content of the message
     */
    public static void queueEdit(Message message, String content) {
        queueEdit(message, content, EMPTY);
    }

    /**
     * Queues the editing of a message to the given content.
     *
     * @param message    The {@link Message} to send edit
     * @param content    The new content of the message
     * @param onComplete The consumer to invoke when the action has been completed, giving the edited message
     */
    public static void queueEdit(Message message, String content, Consumer<Message> onComplete) {
        LOGGER.debug("Queuing edit");
        renderQueue.add(new RenderAction(message, content, onComplete));
    }

    /**
     * Queues the editing of a message to the given content.
     *
     * @param message      The {@link Message} to send edit
     * @param embedContent The new content of the message
     */
    public static void queueEdit(Message message, MessageEmbed embedContent) {
        queueEdit(message, embedContent, EMPTY);
    }

    /**
     * Queues the editing of a message to the given content.
     *
     * @param message      The {@link Message} to send edit
     * @param embedContent The new content of the message
     * @param onComplete   The consumer to invoke when the action has been completed, giving the edited message
     */
    public static void queueEdit(Message message, MessageEmbed embedContent, Consumer<Message> onComplete) {
        LOGGER.debug("Queuing edit");
        renderQueue.add(new RenderAction(message, embedContent, onComplete));
    }

    /**
     * Queues a {@link Runnable} to invoke when the queue reaches it. This can be used for things like unblocking a
     * method when a group of messages has been edited, among many other things, as the time taken to complete a group
     * of actions may not be proportional to the amount of actions.
     *
     * @param runnable The {@link Runnable} to invoke
     */
    public static void breakpoint(Runnable runnable) {
        LOGGER.debug("Queuing breakpoint");
        renderQueue.add(new RenderBreakpoint(runnable));
    }
}
