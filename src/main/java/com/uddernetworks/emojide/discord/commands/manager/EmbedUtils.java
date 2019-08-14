package com.uddernetworks.emojide.discord.commands.manager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.function.Consumer;

public class EmbedUtils extends ListenerAdapter {

    public static void error(TextChannel channel, Member author, String message) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Dammit", null);
        eb.setColor(new Color(0xFF0000));

        eb.setDescription(message);

        eb.setFooter("In response to " + author.getEffectiveName(), author.getUser().getAvatarUrl());
        channel.sendMessage(eb.build()).submit().thenAccept(sent -> sent.addReaction("U+1F5D1").queue());
    }

    public static Message sendEmbed(TextChannel channel, Member author, String title, String description) {
        return sendEmbed(channel, author, title, embed -> embed.setDescription(description));
    }

    public static Message sendEmbed(TextChannel channel, Member author, String title, Consumer<EmbedBuilder> embedBuilderConsumer) {
        var message = channel.sendMessage(createEmbed(author, title, embedBuilderConsumer)).complete();
        message.addReaction("U+1F5D1").queue();
        return message;
    }

    public static MessageEmbed createEmbed(Member author, String title, Consumer<EmbedBuilder> embedBuilderConsumer) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(title, null);
        eb.setColor(new Color(0x424BE9));

        eb.setFooter("Requested by " + author.getEffectiveName(), author.getUser().getAvatarUrl());

        embedBuilderConsumer.accept(eb);
        return eb.build();
    }

    @Override
    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {
        if (event.getUser().isBot()) return;
        if (!event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+1F5D1")) return;
        event.getChannel().retrieveMessageById(event.getMessageIdLong()).submit().thenAccept(message -> {
            if (message.getAuthor().getIdLong() == event.getJDA().getSelfUser().getIdLong()) {
                message.delete().queue();
            }
        });
    }
}
