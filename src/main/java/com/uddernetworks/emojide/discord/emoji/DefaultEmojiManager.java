package com.uddernetworks.emojide.discord.emoji;

import com.uddernetworks.emojide.discord.font.Font;
import com.uddernetworks.emojide.generator.DefaultEmojiGenerator;
import com.uddernetworks.emojide.main.CustomPool;
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
import java.util.stream.Collectors;

public class DefaultEmojiManager implements EmojiManager {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultEmojiManager.class);

    private JDA jda;
    private List<Guild> emojiServers;
    private Map<String, Emoji> emojis = new HashMap<>();
    private EmojIDE emojIDE;
    private Set<Long> maxServers = Collections.synchronizedSet(new HashSet<>());
    private Map<Guild, Integer> emojiDistribution = new HashMap<>();
    private Map<Long, Integer> cachedEmojiAmounts = new HashMap<>();

    public DefaultEmojiManager(EmojIDE emojIDE, List<Long> emojiServers) {
        this.emojIDE = emojIDE;
        this.jda = emojIDE.getJda();

        var unsortedServers = emojiServers.stream().map(jda::getGuildById).collect(Collectors.toUnmodifiableList());

        // From EmojiCommand.java
        Comparator<Guild> comparator;
        if (unsortedServers.stream().map(Guild::getName).allMatch(name -> name.matches(".*\\s+\\d+"))) {
            // Sorting by the ending number of a server
            comparator = Comparator.comparingInt(guild -> {
                var name = guild.getName();
                return Integer.parseInt(name.substring(name.lastIndexOf(' ') + 1));
            });
        } else {
            // Default sorting
            comparator = Comparator.comparing(Guild::getName);
        }

        this.emojiServers = unsortedServers.stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedList::new));

        LOGGER.info("Generating emojis...");

        new DefaultEmojiGenerator().generate();

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

                emojiDistribution.merge(uploaded.getGuild(), 1, Integer::sum);
            });
            return true;
        }).forEach(emoji -> this.emojis.put(emoji.getName().toLowerCase(), emoji));

        LOGGER.info("Done initializing {} emojis.", emojis.size());
    }

    private Optional<Emote> uploadEmote(String name, File file) {
        for (Guild server : emojiServers) {
            var serverId = server.getIdLong();
            if (maxServers.contains(serverId)) continue;

            if (!cachedEmojiAmounts.containsKey(serverId)) cachedEmojiAmounts.put(serverId, server.retrieveEmotes().complete().size());
            if (cachedEmojiAmounts.get(serverId) >= 50) {
                maxServers.add(serverId);
                LOGGER.info("{} Reached max!", server.getName());
                continue;
            }

            CustomPool.start = true;

            try {
                LOGGER.info("Uploading {} to {}", name, server.getName());
                var uploaded = server.createEmote(name, Icon.from(file)).complete();
                if (uploaded != null) {
                    cachedEmojiAmounts.merge(serverId, 1, Integer::sum);
                    return Optional.of(uploaded);
                }
            } catch (IOException e) {
                LOGGER.error("Error uploading emoji " + name + ", retrying on another server...", e);
            } catch (ErrorResponseException e) {
                if (e.getErrorCode() == 30008) {
                    maxServers.add(serverId);
                    continue;
                }

                LOGGER.error("Error while sending emoji request for " + name, e);
            }
        }

        LOGGER.error("It appears as though an emoji could not be uploaded to any of the " + emojiServers.size() + " servers. The program will now be terminated, please try again.");
        System.exit(0);
        return Optional.empty();
    }

    @Override
    public Emoji getEmoji(String name) {
        return this.emojis.getOrDefault(name.toLowerCase(), StaticEmoji.TRANSPARENT);
    }

    @Override
    public Emoji getTextEmoji(char character) {
        return getTextEmoji(String.valueOf((int) character));
    }

    @Override
    public Emoji getTextEmoji(String name) {
        return getTextEmoji(name, emojIDE.getFontManager().getActive());
    }

    @Override
    public Emoji getTextEmoji(char character, Font font) {
        return getTextEmoji(String.valueOf((int) character), font);
    }

    @Override
    public Emoji getTextEmoji(String name, Font font) {
        return getEmoji(font.getPrefix() + name);
    }

    @Override
    public Map<String, Emoji> getEmojis() {
        return Map.copyOf(emojis);
    }

    public Map<Guild, Integer> getEmojiDistribution() {
        return Map.copyOf(emojiDistribution);
    }

    @Override
    public List<Guild> getEmojiServers() {
        return List.copyOf(emojiServers);
    }
}
