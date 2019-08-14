package com.uddernetworks.emojide.discord.emoji;

import com.uddernetworks.emojide.discord.font.Font;
import net.dv8tion.jda.api.entities.Guild;

import java.util.List;
import java.util.Map;

/**
 * The manager for all {@link Emoji}s. Upon initialization, any emojis from {@link StaticEmoji} are uploaded to an emoji
 * server in the config list, if the emoji is non existant anywhere.
 */
public interface EmojiManager {

    /**
     * Gets the {@link Emoji} by the given name, case insensitive.
     *
     * @param name The name of the emoji
     * @return The {@link Emoji}
     */
    Emoji getEmoji(String name);

    /**
     * Gets the text emoji for the given character, in the currently applied font.
     *
     * @param character The character to get the emoji of
     * @return The emoji
     */
    Emoji getTextEmoji(char character);

    /**
     * Gets the text emoji for the given character, in the currently applied font. This assumes the <code>name</code> argument
     * is the emoji name, i.e. <code>o96</code>
     *
     * @param name The character to get the emoji of
     * @return The emoji
     */
    Emoji getTextEmoji(String name);

    /**
     * Gets the text emoji for the given character, in the currently applied typeface.
     *
     * @param character The character to get the emoji of
     * @param font The font to render in
     * @return The emoji
     */
    Emoji getTextEmoji(char character, Font font);

    /**
     * Gets the text emoji for the given character, in the given font. This assumes the <code>name</code> argument
     * is the emoji name, i.e. <code>o96</code>
     *
     * @param name The character to get the emoji of
     * @param font The font to render in
     * @return The emoji
     */
    Emoji getTextEmoji(String name, Font font);

    /**
     * Gets an immutable map of all custom emojis uploaded and used by EmojIDE.
     *
     * @return All emojis by EmojIDE
     */
    Map<String, Emoji> getEmojis();

    /**
     * Gets an immutable map of the amount of custom emojis by EmojIDE in each emoji server.
     *
     * @return The distribution of emojis among emoji servers
     */
    Map<Guild, Integer> getEmojiDistribution();

    /**
     * Gets an immutable list of servers used solely for emoji uploading and fetching.
     *
     * @return The emoji servers
     */
    List<Guild> getEmojiServers();
}
