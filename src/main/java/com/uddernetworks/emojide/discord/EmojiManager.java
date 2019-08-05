package com.uddernetworks.emojide.discord;

import com.uddernetworks.emojide.generator.EmojiGenerator;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class EmojiManager {

    private static Logger LOGGER = LoggerFactory.getLogger(EmojiManager.class);

    private JDA jda;
    private List<Guild> emojiServers;
    private Map<String, Emoji> emojis = new HashMap<>();
    private EmojIDE emojIDE;

    public EmojiManager(EmojIDE emojIDE, List<Long> emojiServers) {
        this.emojIDE = emojIDE;
        this.jda = emojIDE.getJda();
        this.emojiServers = emojiServers.stream().map(jda::getGuildById).collect(Collectors.toUnmodifiableList());

        LOGGER.info("Generating emojis...");

        new EmojiGenerator().generate();

        LOGGER.info("Initializing all emojis...");

        Arrays.stream(StaticEmoji.values()).filter(emoji -> {
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
                var uploadedOptional = uploadEmote(emoji.getName(), file);
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

    public Optional<Emote> uploadEmote(String name, File file) {
        var done = new AtomicBoolean();
        AtomicReference<Emote> uploaded = new AtomicReference<>();

        emojiServers.forEach(server -> {
            if (server.retrieveEmotes().complete().size() >= 50) {
                LOGGER.info("Reached max!");
                return;
            }

            if (uploaded.get() != null) return;
            done.set(false);

//            uploaded.set(this.emojIDE.getBotManager().queueTask(bot -> {
                try {
                    LOGGER.info("Uploading {} to {}", name, server.getName());
                    uploaded.set(server.createEmote(name, Icon.from(file)).complete());
                } catch (IOException e) {
                    LOGGER.error("Error uploading emoji " + name + ", retrying on another server...", e);
                } catch (ErrorResponseException e) {
                    if (e.getErrorCode() == 30008) return; // Maximum number of emojis reached
                    LOGGER.error("Error while sending emoji request for " + name, e);
                }
                return;
//            }));
        });

        return Optional.ofNullable(uploaded.get());
    }

    public Emoji getEmoji(String name) {
        return this.emojis.getOrDefault(name.toLowerCase(), this.emojis.get("discord"));
    }

}
