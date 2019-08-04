package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.generator.EmojiGenerator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EmojiManager {

    private static Logger LOGGER = LoggerFactory.getLogger(EmojiManager.class);

    private JDA jda;
    private List<Guild> emojiServers;
    private Map<String, Emoji> emojis = new HashMap<>();

    public EmojiManager(JDA jda, List<Long> emojiServers) {
        this.jda = jda;
        this.emojiServers = emojiServers.stream().map(jda::getGuildById).collect(Collectors.toUnmodifiableList());

        LOGGER.info("Generating emojis...");

        new EmojiGenerator().generate();

        LOGGER.info("Initializing all emojis...");

        Arrays.stream(Emoji.values()).parallel().filter(emoji -> {
            if (!emoji.isCustom()) {
                emoji.setDisplay(":" + emoji.getId() + ":");
                return true;
            }

            var file = new File(emoji.getRelativePath());
            if (!file.exists()) {
                LOGGER.error("Couldn't find emoji file {}", file.getAbsolutePath());
                return false;
            }

            var existingEmotes = jda.getEmotesByName(emoji.getName(), false);

            Optional.ofNullable(existingEmotes.stream().findFirst().orElseGet(() -> {
                LOGGER.info("Uploading emoji {}", emoji.getName());
                var uploadedOptional = uploadEmote(emoji, file);
                if (uploadedOptional.isEmpty()) {
                    LOGGER.error("The emoji {} couldn't be uploaded! This could be crucial to the bot's usage, and may break things.", emoji.getName());
                    return null;
                }

                var uploaded = uploadedOptional.get();
                LOGGER.info("Successfully uploaded {}, now with the ID of {}", emoji.getName(), emoji.getId());

                return uploaded;
            })).ifPresent(uploaded -> {
                LOGGER.info("Registering emoji {}", emoji.getName());
                emoji.setId(uploaded.getIdLong());
                emoji.setDisplay(uploaded.getAsMention());
            });
            return true;
        }).forEach(emoji -> this.emojis.put(emoji.getName().toLowerCase(), emoji));

        LOGGER.info("Done initializing emojis.");
    }

    private Optional<Emote> uploadEmote(Emoji emoji, File file) {
        AtomicReference<Emote> uploaded = new AtomicReference<>();
        emojiServers.forEach(server -> {
            if (server.getEmotes().size() >= 50) return;
            if (uploaded.get() != null) return;
            try {
                LOGGER.info("Uploading {} to {}", emoji.getName(), server.getName());
                uploaded.set(server.createEmote(emoji.getName(), Icon.from(file)).complete());
            } catch (IOException e) {
                LOGGER.error("Error uploading emoji " + emoji.getName() + ", retrying on another server...", e);
            }
        });
        return Optional.ofNullable(uploaded.get());
    }

    public Emoji getEmoji(String name) {
        return this.emojis.getOrDefault(name.toLowerCase(), this.emojis.get("discord"));
    }

}
