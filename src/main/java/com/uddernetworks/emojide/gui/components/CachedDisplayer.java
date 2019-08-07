package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.gui.render.RenderEngine;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Displays a single EmojiComponent across as many required messages
 */
public class CachedDisplayer implements Displayer {

    private static final int EMOJI_LENGTH = 18;
    private static final int MAX_MESSAGE_LENGTH = 2000;
    private static final int MAX_EMOJIS_PER_LINE = 111;

    private EmojIDE emojIDE;
    private EmojiComponent child;
    private AtomicBoolean waiting = new AtomicBoolean();
    private List<Message> messages = new ArrayList<>();
    private Emoji filler;
    private TextChannel channel;
    private boolean insertKeyboard;

    private List<String> cachedLines = new ArrayList<>();

    /**
     * Creates a {@link CachedDisplayer} in the given channel.
     *
     * @param emojIDE The {@link EmojIDE} instance
     * @param channel The {@link TextChannel} to place everything in
     */
    public CachedDisplayer(EmojIDE emojIDE, TextChannel channel) {
        this(emojIDE, channel, false);
    }

    /**
     * Creates a {@link CachedDisplayer} in the given channel. If insertKeyboard is true, a keyboard will be set after the
     * first update invocation.
     *
     * @param emojIDE The {@link EmojIDE} instance
     * @param channel The {@link TextChannel} to place everything in
     * @param insertKeyboard If a keyboard should be set afterwards
     */
    public CachedDisplayer(EmojIDE emojIDE, TextChannel channel, boolean insertKeyboard) {
        this.emojIDE = emojIDE;
        this.filler = StaticEmoji.DISCORD;
        this.channel = channel;
        this.insertKeyboard = insertKeyboard;

        RenderEngine.start();
    }

    @Override
    public void setChild(EmojiComponent component) {
        setChild(component, false);
    }

    @Override
    public void setChild(EmojiComponent component, boolean autoUpdate) {
        if (this.child != null) throw new RuntimeException("Child has already been set for this Displayer!");
        this.child = component;
        if (autoUpdate) update();
    }

    @Override
    public EmojiComponent getChild() {
        return child;
    }

    @Override
    public void update() {
        while (this.waiting.get()) {}
        boolean sendMessages = this.messages.isEmpty();
        if (sendMessages && this.child.getWidth() > MAX_EMOJIS_PER_LINE)
            throw new InvalidComponentException("Invalid width of " + this.child.getWidth() + " (Max is " + MAX_EMOJIS_PER_LINE + ")");
        var emotes = this.child.getCachedRender();
        if (emotes[0].length != this.child.getWidth() || emotes.length != this.child.getHeight())
            throw new InvalidComponentException("Incorrect render dimensions received. Got " + emotes[0].length + "x" + emotes.length + ", expected " + this.child.getWidth() + "x" + this.child.getHeight());
        if (cachedLines.isEmpty()) this.cachedLines = IntStream.range(0, emotes.length).mapToObj(i -> "").collect(Collectors.toList());
        for (int i = 0; i < emotes.length; i++) {
            var line = emotes[i];
            var message = Arrays.stream(line)
                    .map(emote -> emote == null ? this.filler : emote)
                    .map(Emoji::getDisplay)
                    .collect(Collectors.joining());
            if (i == 0) message = "\u200b\n\u200b\n\u200b\n" + message;
            if (sendMessages) {
                RenderEngine.queueSend(this.channel, message, completed -> this.messages.add(completed));
            } else {
                if (!this.cachedLines.get(i).equals(message)) RenderEngine.queueEdit(this.messages.get(i), message, ignored -> {});
            }
            this.cachedLines.set(i, message);
        }

        if (sendMessages && insertKeyboard) {
            this.emojIDE.getKeyboardInputManager().createKeyboard(channel);
        }

        RenderEngine.breakpoint(() -> this.waiting.set(false));
    }

    @Override
    public void stop(TextChannel channel) {
        channel.purgeMessages(this.messages);
        this.messages.clear();
    }

    @Override
    public EmojIDE getEmojIDE() {
        return emojIDE;
    }

    @Override
    public EmojiManager getEmojiManager() {
        return this.emojIDE.getEmojiManager();
    }
}
