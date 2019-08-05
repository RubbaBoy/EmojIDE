package com.uddernetworks.emojide.gui.render;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.function.Consumer;

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

    public boolean isEditing() {
        return this.editing != null;
    }

    public String getContent() {
        return content;
    }

    public MessageEmbed getEmbedContent() {
        return embedContent;
    }

    public Message getEditing() {
        return editing;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public void complete(Message message) {
        onComplete.accept(message);
    }

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
