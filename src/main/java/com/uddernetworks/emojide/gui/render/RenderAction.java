package com.uddernetworks.emojide.gui.render;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.function.Consumer;

/**
 * An action for the {@link RenderEngine} to do, either editing or creating a message with given contents.
 */
public class RenderAction implements RenderEntry {

    private String content;
    private MessageEmbed embedContent;

    private Message editing;
    private TextChannel channel;

    private Consumer<Message> onComplete;

    public RenderAction(Message editing, String content, Consumer<Message> onComplete) {
        this(editing, content, null, onComplete);
    }

    public RenderAction(TextChannel channel, String content, Consumer<Message> onComplete) {
        this(null, content, channel, onComplete);
    }

    public RenderAction(Message editing, MessageEmbed embedContent, Consumer<Message> onComplete) {
        this(editing, embedContent, null, onComplete);
    }

    public RenderAction(TextChannel channel, MessageEmbed embedContent, Consumer<Message> onComplete) {
        this(null, embedContent, channel, onComplete);
    }

    public RenderAction(Message editing, MessageEmbed embedContent, TextChannel channel, Consumer<Message> onComplete) {
        this.editing = editing;
        this.embedContent = embedContent;
        this.channel = channel;
        this.onComplete = onComplete;
    }

    public RenderAction(Message editing, String content, TextChannel channel, Consumer<Message> onComplete) {
        this.editing = editing;
        this.content = content;
        this.channel = channel;
        this.onComplete = onComplete;
    }

    /**
     * Gets if the action is editing a message.
     *
     * @return If the action is editing a message
     */
    public boolean isEditing() {
        return this.editing != null;
    }

    /**
     * Gets the new content of the message.
     *
     * @return The new content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the new embedded content of the message.
     *
     * @return The new embedded content of the message
     */
    public MessageEmbed getEmbedContent() {
        return embedContent;
    }

    /**
     * Gets the message to be edited, if editing.
     *
     * @return The message being edited
     */
    public Message getEditing() {
        return editing;
    }

    /**
     * Gets the {@link TextChannel} to send the message, if sending.
     *
     * @return The {@link TextChannel} to send the message
     */
    public TextChannel getChannel() {
        return channel;
    }

    /**
     * Invokes the (If existent) given completed consumer, giving the edited or created {@link Message}.
     *
     * @param message The edited or created {@link Message}
     */
    public void complete(Message message) {
        onComplete.accept(message);
    }

    /**
     * Gets the message ID of the message being edited, or -1 if the action is not editing.
     *
     * @return The message ID
     */
    public long getEditingId() {
        return editing == null ? -1 : editing.getIdLong();
    }

    @Override
    public boolean isAction() {
        return true;
    }

    @Override
    public String toString() {
        return "Action[" + content + "]";
    }
}
