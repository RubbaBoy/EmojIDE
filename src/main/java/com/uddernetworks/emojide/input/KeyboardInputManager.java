package com.uddernetworks.emojide.input;

import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class KeyboardInputManager extends ListenerAdapter {

    private EmojIDE emojIDE;
    private boolean keyboardActive;
    private List<Message> keyboardRows = new ArrayList<>();

    private String BLANK = "padding";

    private String BACKSPACE = "backspace";
    private String INS = "ins";
    private String HOME = "home";
    private String PG_UP = "pg_up";

    private String TAB = "tab";
    private String DEL = "del";
    private String END = "end";
    private String PG_DOWN = "pg_down";

    private String CAPS_LOCK = "caps_lock";
    private String ENTER = "enter";

    private String SHIFT = "shift";
    private String UP = "up";

    private String CTRL = "ctrl";
    private String ICON = "icon";
    private String ALT = "alt";
    private String SPACE = "space";
    private String SPACE2 = "space2";
    private String SPACE3 = "space3";
    private String SPACE4 = "space4";
    private String SPACE5 = "space5";
    private String SPACE6 = "space6";
    private String FN = "fn";
    private String CONTEXT = "context";
    private String LEFT = "left";
    private String DOWN = "down";
    private String RIGHT = "right";

    public KeyboardInputManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;
    }

    public void createKeyboard(TextChannel textChannel) {
        if (this.keyboardActive) return;
        this.keyboardActive = true;

//        var discord = this.emojIDE.getEmojiManager().getEmoji("discord");
//
//        constructRow(textChannel.sendMessage(discord.toString()).complete(), '`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, BLANK, INS, HOME, PG_UP);
//        constructRow(textChannel.sendMessage(discord.toString()).complete(), );
//        constructRow(textChannel.sendMessage(discord.toString()).complete(), );
//        constructRow(textChannel.sendMessage(discord.toString()).complete(), );
//        constructRow(textChannel.sendMessage(discord.toString()).complete(), );
        System.out.println(constructRow(CTRL, ICON, ALT, SPACE, true, SPACE2, true, SPACE3, true, SPACE4, true, SPACE5, true, SPACE6, ALT, FN, CONTEXT, CTRL, LEFT, DOWN, RIGHT));

        var zws = Character.toString('\u200b');

//        var mention = emojIDE.getEmojiManager().getEmoji("enter").getEmote().getAsMention();
        textChannel.sendMessage(new EmbedBuilder()
                .addField(zws, constructRow('`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, BLANK, INS, HOME, PG_UP), false)
//                .addField("", constructRow('`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, BLANK, INS, HOME, PG_UP), false)
//                .addField("", constructRow('`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, BLANK, INS, HOME, PG_UP), false)
//                .addField("", constructRow('`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, BLANK, INS, HOME, PG_UP), false)
//                .addField("", constructRow('`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, BLANK, INS, HOME, PG_UP), false)
                .addField(zws, constructRow(TAB, 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\\', BLANK, DEL, END, PG_DOWN), false)
                .addField(zws, constructRow(CAPS_LOCK, 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\'', ENTER, ENTER), false)
                .addField(zws, constructRow(SHIFT, true, SHIFT, 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/', SHIFT, true, SHIFT, BLANK, UP), false)
                .addField(zws, constructRow(CTRL, ICON, ALT, SPACE, true, SPACE, true, SPACE, true, SPACE, true, SPACE, true, SPACE, true, SPACE, true, SPACE, ALT, FN, CONTEXT, CTRL, LEFT, DOWN, RIGHT), false)
                .build()).queue();


        System.out.println("Creating keyboard!");
    }

    private String constructRow(Object... keys) {
        var row = new StringBuilder();
        var emojiManager = this.emojIDE.getEmojiManager();
        for (Object key : keys) {
            var name = "discord";
            if (key instanceof Boolean) {
                if ((Boolean) key) {
                    row = new StringBuilder(row.toString().stripTrailing());
                }
            } else {
                if (key instanceof Character) {
                    name = String.valueOf((int) (Character) key);
                } else if (key instanceof String) {
                    name = (String) key;
                }
                row.append("[").append(emojiManager.getEmoji(name).getEmote().getAsMention()).append("](http://localhost/").append(name).append(") ");
            }
        }
        return row.toString();
    }

    @Override
    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {
        System.out.println(event.getUser().getName() + " added " + event.getReactionEmote().getName());
    }


}
