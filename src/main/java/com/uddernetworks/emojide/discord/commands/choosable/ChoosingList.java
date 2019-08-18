package com.uddernetworks.emojide.discord.commands.choosable;

import com.uddernetworks.emojide.main.ChoosableEnum;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A radio list of enums that may be selected via emojis. This is the same list that appears from the command
 * <code>!ide fonts</code> and others. The examples used in the class will derive from the following embed screenshot:
 * <br><br>
 * <img src="https://rubbaboy.me/images/0npwmcb" alt="The '!ide fonts' embed">
 *
 * @param <T> The enum to choose
 */
public interface ChoosingList<T extends ChoosableEnum> {

    /**
     * The unique name of the emoji, as short as possible is usually ideal (Due to embed limits). This is currently
     * just the {@link Object#hashCode()} of the class, but may need to just be ain incremental value for
     * conciseness.
     *
     * @return The unique name
     */
    String getName();

    /**
     * Initially sends a choosing embed to the given channel.
     *
     * @param member The requester of the embed
     * @param channel The channel to send the embed to
     * @param title The title of the embed, e.g. <code>Fonts</code>
     * @param description The description of the embed, e.g. <code>The following are the fonts used by the IDE. Either
     *                    click the :uns: to apply the font, or type `!ide setfont "name"`</code>
     */
    void sendEmbed(Member member, TextChannel channel, String title, String description);

    /**
     * Invoked by the {@link ChoosingListManager} when an enum is selected.
     *
     * @param member The requester of the interacted message
     * @param channel The channel in which the interacted message is in
     * @param messageId The Discord ID of the message being interacted with
     * @param ordinal The ordinal of the enum (Not yet validated)
     */
    void activateEnum(Member member, TextChannel channel, long messageId, int ordinal);

    /**
     * This is not specific towards the <i>list</i> portion of the chooser, but rather the manual selection. This makes
     * the manual choosing of an Enum just a single line in the command registration. The following screenshots will be
     * used as reference in the method's docs:
     * <br><br>
     * <img src="https://rubbaboy.me/images/s7d6pwo" alt="The success embed of `!ide setfont [name]`">
     * <br><br>
     * <img src="https://rubbaboy.me/images/zbzg88n" alt="The failure embed of `!ide setfont [name]`">
     *
     * @param channel The channel to send the embed
     * @param member The requester of the embed
     * @param enumName The argument of the command, set by the end user. This has no validation before this method
     * @param lowercaseDescriptor The lowercase description word of the enum shown in failures, e.g. <code>font</code>
     * @param onSelect The action to perform when a given Enum is selected
     * @param successTitle The title to display when an item was successfully chosen, e.g. <code>Changed Font</code>
     * @param successDescription The description (Supplied by the chosen Enum) to display when an item was successfully
     *                           chosen, e.g. <code>Changed active font to Fira Code</code>
     */
    void manualChoose(TextChannel channel, Member member, String enumName, String lowercaseDescriptor, Consumer<T> onSelect, String successTitle, Function<T, String> successDescription);
}
