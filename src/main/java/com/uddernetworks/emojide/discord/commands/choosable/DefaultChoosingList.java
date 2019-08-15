package com.uddernetworks.emojide.discord.commands.choosable;

import com.uddernetworks.emojide.discord.commands.manager.EmbedUtils;
import com.uddernetworks.emojide.discord.emoji.EmojiManager;
import com.uddernetworks.emojide.discord.emoji.StaticEmoji;
import com.uddernetworks.emojide.discord.font.Font;
import com.uddernetworks.emojide.main.ChoosableEnum;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.uddernetworks.emojide.discord.commands.CommandHelp.space;

public class DefaultChoosingList<T extends ChoosableEnum> implements ChoosingList<T> {

    private EmojIDE emojIDE;
    private EmojiManager emojiManager;
    private Supplier<T> active;
    private Consumer<T> onSelect;
    private Class<T> choosableEnum;

    private String name;

    DefaultChoosingList(EmojIDE emojIDE, Supplier<T> active, Consumer<T> onSelect, Class<T> choosableEnum) {
        this.emojIDE = emojIDE;
        this.emojiManager = emojIDE.getEmojiManager();
        this.active = active;
        this.onSelect = onSelect;
        this.choosableEnum = choosableEnum;
        this.name = String.valueOf(choosableEnum.hashCode());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sendEmbed(Member member, TextChannel channel, String title, String description) {
        var emptyQuery = Map.of("member", member.getId(), "channel", channel.getId());
        var editing = EmbedUtils.sendEmbed(channel, member, title, embed -> embed.setDescription(description));
        editing.editMessage(createListEmbed(editing, emptyQuery)).queue();
    }

    @Override
    public void activateEnum(Member member, TextChannel channel, long messageId, int ordinal) {
        if (ordinal >= choosableEnum.getEnumConstants().length) return;
        var emptyQuery = Map.of("member", member.getId(), "channel", channel.getId());

        onSelect.accept(choosableEnum.getEnumConstants()[ordinal]);
        channel.retrieveMessageById(messageId).submit().thenAccept(message -> {
            if (message == null || message.getEmbeds().size() != 1 || message.getAuthor().getIdLong() != emojIDE.getJda().getSelfUser().getIdLong()) return;
            message.editMessage(createListEmbed(message, emptyQuery)).queue();
        });
    }

    @Override
    public void manualChoose(TextChannel channel, Member member, String enumName, String lowercaseDescriptor, Consumer<T> onSelect, String successTitle, Function<T, String> successDescription) {
        var fontOptional = Arrays.stream(choosableEnum.getEnumConstants()).filter(enu -> enu.getName().equalsIgnoreCase(enumName)).findFirst();
        if (fontOptional.isEmpty()) {
            EmbedUtils.error(channel, member, "Unknown " + lowercaseDescriptor + " `" + enumName + "`");
            return;
        }

        var selected = fontOptional.get();
        onSelect.accept(selected);
        EmbedUtils.sendEmbed(channel, member, successTitle, successDescription.apply(selected));
    }

    private MessageEmbed createListEmbed(Message message, Map<String, String> emptyQuery) {
        var editingEmbed = new EmbedBuilder(message.getEmbeds().get(0));
        var webCallbackHandler = emojIDE.getWebCallbackHandler();
        editingEmbed.clearFields();

        var paddedLength = Arrays.stream(Font.values()).mapToInt(font -> font.getName().length()).max().orElse(0) + 2;

        Arrays.stream(choosableEnum.getEnumConstants()).forEach(enu -> {
            boolean thisActive = active.get() == enu;
            var usingFont = enu instanceof Font ? (Font) enu : emojIDE.getFontManager().getActive();
            var emojiName = paddedEmoji(enu.getName().chars().mapToObj(cha -> emojiManager.getTextEmoji((char) cha, usingFont).getDisplay()).collect(Collectors.joining(" ")), paddedLength);

            var query = new HashMap<>(emptyQuery);
            query.put("id", getName());
            query.put("ordinal", String.valueOf(enu.ordinal()));
            query.put("originating", message.getId());
            editingEmbed.addField(enu.getName(), emojiName + space(4) +
                    (thisActive ? StaticEmoji.SELECT.getDisplay() : webCallbackHandler.generateMdLink(StaticEmoji.UNSELECTED.getDisplay(), "apply", query)), true);
        });

        return editingEmbed.build();
    }

    private String paddedEmoji(String input, int length) {
        return input.replaceAll("\\s+", "") + StaticEmoji.TRANSPARENT.getDisplay().repeat(Math.max(0, length - input.split("\\s+").length));
    }
}
