package com.uddernetworks.emojide.discord;

import java.util.Arrays;
import java.util.Optional;

/**
 * All the non-custom emojis by Discord
 */
public enum IconEmote {
    FILLER(new Emoji("black_large_square")),
    BORDER(new Emoji("white_large_square"));

    private Emoji emoji;

    IconEmote(Emoji emoji) {
        this.emoji = emoji;
    }

    public Emoji getEmoji() {
        return this.emoji;
    }

    public static Optional<IconEmote> getEmoji(String name) {
        return Arrays.stream(values()).filter(emote -> emote.getEmoji().getName().equalsIgnoreCase(name)).findFirst();
    }
}
