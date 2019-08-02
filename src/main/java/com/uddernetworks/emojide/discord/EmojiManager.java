package com.uddernetworks.emojide.discord;

import net.dv8tion.jda.api.JDA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EmojiManager {

    private static Logger LOGGER = LoggerFactory.getLogger(EmojiManager.class);

    private JDA jda;
    private Map<String, Emoji> emojis = new HashMap<>();

    public EmojiManager(JDA jda) {
        this.jda = jda;

        LOGGER.info("Initializing all emojis...");

        IntStream.range(33, 126).forEach(i -> {
            var name = String.valueOf(i);
            emojis.put(name, new Emoji(jda.getEmotesByName(name, false).get(0)));
        });

        Arrays.stream(IconEmote.values()).forEach(icon -> emojis.put(icon.getEmoji().getName(), icon.getEmoji()));

        Stream.of("gray1", "gray2", "gray3", "gray4", "gray5", "gray6", "red", "orange", "yellow", "green1", "green2", "green3", "blue1", "blue2", "blue3", "purple1", "purple2", "discord").forEach(name -> {
            emojis.put(name, new Emoji(jda.getEmotesByName(name, false).get(0)));
        });

        LOGGER.info("Done initializing emojis.");
    }

    public Emoji getEmoji(String name) {
        var emoteOptional = IconEmote.getEmoji(name);
        if (emoteOptional.isPresent()) return emoteOptional.get().getEmoji();
        return this.emojis.getOrDefault(name, this.emojis.get("discord"));
    }

}
