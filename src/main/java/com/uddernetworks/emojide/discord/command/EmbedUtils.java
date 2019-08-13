package com.uddernetworks.emojide.discord.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.function.Consumer;

public class EmbedUtils {

    public static void error(TextChannel channel, Member author, String message) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Dammit", null);
        eb.setColor(new Color(0xFF0000));

        eb.setDescription(message);

        eb.setFooter("In response to " + author.getEffectiveName(), author.getUser().getAvatarUrl());
        channel.sendMessage(eb.build()).queue();
    }

    public static void sendEmbed(TextChannel channel, Member author, String title, String description) {
        sendEmbed(channel, author, title, embed -> embed.setDescription(description));
    }

    public static void sendEmbed(TextChannel channel, Member author, String title, Consumer<EmbedBuilder> embedBuilderConsumer) {
        channel.sendMessage(createEmbed(author, title, embedBuilderConsumer)).queue();
    }

    public static MessageEmbed createEmbed(Member author, String title, Consumer<EmbedBuilder> embedBuilderConsumer) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(title, null);
        eb.setColor(new Color(0x424BE9));

        eb.setFooter("Requested by " + author.getEffectiveName(), author.getUser().getAvatarUrl());

        embedBuilderConsumer.accept(eb);
        return eb.build();
    }

}
