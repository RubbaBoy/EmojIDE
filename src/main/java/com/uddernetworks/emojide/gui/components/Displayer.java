package com.uddernetworks.emojide.gui.components;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.EmojiManager;
import com.uddernetworks.emojide.discord.IconEmote;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private List<Message> messages = new ArrayList<>();
    private Emoji filler;
    private TextChannel channel;

    public Displayer(EmojIDE emojIDE, TextChannel channel) {
        this.emojIDE = emojIDE;
        this.filler = emojIDE.getEmojiManager().getEmoji("discord");
        this.channel = channel;
    }

    public void setChild(EmojiComponent component) {
        this.child = component;
    }

    public void update() {
        boolean sendMessages = this.messages.isEmpty();
        if (sendMessages && this.child.width > MAX_EMOJIS_PER_LINE)
            throw new InvalidComponentException("Invalid width of " + this.child.width + " (Max is " + MAX_EMOJIS_PER_LINE + ")");
        var emotes = this.child.getCachedRender();
        if (emotes[0].length != this.child.width || emotes.length != this.child.height)
            throw new InvalidComponentException("Incorrect render dimensions received. Got " + emotes[0].length + "x" + emotes.length + ", expected " + this.child.width + "x" + this.child.height);
        for (int i = 0; i < emotes.length; i++) {
            var line = emotes[i];
            var builder = new StringBuilder();
            Arrays.stream(line)
                    .map(emote -> emote == null ? this.filler : emote)
                    .forEach(builder::append);
            if (sendMessages) {
                this.messages.add(this.channel.sendMessage(builder.toString()).complete());
            } else {
                this.messages.get(i).editMessage(builder.toString()).queue();
            }
        }
    }

    public EmojIDE getEmojIDE() {
        return emojIDE;
    }

    public void stop() {
        this.messages.forEach(message -> message.delete().queue());
    }

    public EmojiManager getEmojiManager() {
        return this.emojIDE.getEmojiManager();
    }
}
