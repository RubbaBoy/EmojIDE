package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.gui.render.RenderAction;
import com.uddernetworks.emojide.gui.render.RenderEngine;
import com.uddernetworks.emojide.gui.render.RenderEntry;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Displays a single EmojiComponent across as many required messages
 */
public class Displayer {

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
     * Creates a {@link Displayer} in the given channel.
     *
     * @param emojIDE The {@link EmojIDE} instance
     * @param channel The {@link TextChannel} to place everything in
     */
    public Displayer(EmojIDE emojIDE, TextChannel channel) {
        this(emojIDE, channel, false);
    }

    /**
     * Creates a {@link Displayer} in the given channel. If insertKeyboard is true, a keyboard will be set after the
     * first update invocation.
     *
     * @param emojIDE The {@link EmojIDE} instance
     * @param channel The {@link TextChannel} to place everything in
     * @param insertKeyboard If a keyboard should be set afterwards
     */
    public Displayer(EmojIDE emojIDE, TextChannel channel, boolean insertKeyboard) {
        this.emojIDE = emojIDE;
        this.filler = emojIDE.getEmojiManager().getEmoji("discord");
        this.channel = channel;
        this.insertKeyboard = insertKeyboard;

        RenderEngine.start();
    }

    public void setChild(EmojiComponent component) {
        setChild(component, false);
    }

    public void setChild(EmojiComponent component, boolean autoUpdate) {
        if (this.child != null) throw new RuntimeException("Child has already been set for this Displayer!");
        this.child = component;
        if (autoUpdate) update();
    }

    public EmojiComponent getChild() {
        return child;
    }

    public void update() {
        while (this.waiting.get()) {}
        boolean sendMessages = this.messages.isEmpty();
        if (sendMessages && this.child.width > MAX_EMOJIS_PER_LINE)
            throw new InvalidComponentException("Invalid width of " + this.child.width + " (Max is " + MAX_EMOJIS_PER_LINE + ")");
        var emotes = this.child.getCachedRender();
        if (emotes[0].length != this.child.width || emotes.length != this.child.height)
            throw new InvalidComponentException("Incorrect render dimensions received. Got " + emotes[0].length + "x" + emotes.length + ", expected " + this.child.width + "x" + this.child.height);
        if (cachedLines.isEmpty()) this.cachedLines = IntStream.range(0, emotes.length).mapToObj(i -> "").collect(Collectors.toList());
        for (int i = 0; i < emotes.length; i++) {
            var line = emotes[i];
            var message = Arrays.stream(line)
                    .map(emote -> emote == null ? this.filler : emote)
                    .map(Emoji::getDisplay)
                    .collect(Collectors.joining());
            if (sendMessages) {
                RenderEngine.queueSend(this.channel, message, completed -> this.messages.add(completed));
            } else {
                if (!this.cachedLines.get(i).equals(message)) RenderEngine.queueEdit(this.messages.get(i), message, ignored -> {});;
            }
            this.cachedLines.set(i, message);
        }

        if (sendMessages && insertKeyboard) {
            this.emojIDE.getKeyboardInputManager().createKeyboard(channel);
        }

        RenderEngine.breakpoint(() -> this.waiting.set(false));
    }

    public EmojIDE getEmojIDE() {
        return emojIDE;
    }

    public void stop(TextChannel channel) {
        channel.purgeMessages(this.messages);
        this.messages.clear();
    }

    public EmojiManager getEmojiManager() {
        return this.emojIDE.getEmojiManager();
    }
}
