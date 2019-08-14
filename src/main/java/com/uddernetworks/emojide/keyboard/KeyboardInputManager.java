package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.discord.emoji.Emoji;
import com.uddernetworks.emojide.web.WebListener;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.Optional;

public interface KeyboardInputManager extends EventListener {

    /**
     * Creates a keyboard in the given {@link TextChannel}. The keyboard works by making an embed with two inline fields
     * next to each other in each row, with 8 emojis per row, normal emojis being padded by :discord: to meet this
     * criteria. This is to make each row look uniform. Each emoji is a link to the webserver hosted by this bot, which
     * is why each field is only half of the embed width, as there is a low character count for fields, and even lower
     * for the full description.
     * <p>
     * When an emoji's link is clicked, the page opened sends another request to the webserver with a random number that
     * then counts the request. If this random link has not been registered yet, events are fired. This is to prevent
     * double presses from browsers sending things twice.
     *
     * @param textChannel The {@link TextChannel} to send the keyboard in
     */
    void createKeyboard(TextChannel textChannel);

    /**
     * Changes the keyboard to uppercase letters.
     */
    void changeToUpper();

    /**
     * Changes the keyboard to lowercase letters.
     */
    void changeToLower();

    /**
     * Removes the keyboard message.
     */
    void removeKeyboard();

    /**
     * Gets a pair of {@link Emoji}s in the keyboard, e.g. the left and right emojis for the shift key.
     *
     * @param emoji An {@link Emoji} from a pair
     * @return The {@link Pair} name of the emoji
     */
    Optional<Pair> getPair(Emoji emoji);

    /**
     * Gets the state of the keyboard, i.e. generally what key is pressed down to modify future keypresses.
     *
     * @return The {@link ActiveState} of the keyboard.
     */
    ActiveState getState();

    /**
     * Invoked by the {@link WebListener}, and is in charge of raising events.
     *
     * @param key The key clicked, in the format by the {@link WebListener}
     */
    void handleKey(String key);

    enum Pair {
        SPACE, SHIFT, ENTER, CAPS, CTRL, ALT, FN
    }

    enum ActiveState {
        NONE, SHIFT, CTRL, ALT, FN
    }
}
