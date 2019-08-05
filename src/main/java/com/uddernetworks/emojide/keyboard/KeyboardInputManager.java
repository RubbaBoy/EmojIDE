package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.gui.render.RenderEngine;
import com.uddernetworks.emojide.main.EmojIDE;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.uddernetworks.emojide.discord.Emoji.*;
import static com.uddernetworks.emojide.keyboard.KeyboardInputManager.Pair.*;

public class KeyboardInputManager extends ListenerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(KeyboardInputManager.class);
    private static final char ZWS = '\u200b';

    private WebListener webListener;

    private EmojIDE emojIDE;
    private boolean keyboardActive;
    private Map<Pair, List<Emoji>> pairs = new HashMap<>();
    private Message keyboardMessage;
    private Map<Object, List<Method>> eventClasses = new HashMap<>();

    public enum Pair {
        SPACE, SHIFT, ENTER
    }

    public KeyboardInputManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;

        this.webListener = new WebListener(emojIDE);
        this.webListener.start(this);
    }

    public void createKeyboard(TextChannel textChannel) {
        if (this.keyboardActive) return;
        this.keyboardActive = true;

        var builder = new EmbedBuilder();
        addRow(builder, '`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, TRANSPARENT, INS, HOME, PG_UP);
        addRow(builder, TAB, 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\\', TRANSPARENT, DEL, END, PG_DOWN);
        addRow(builder, CAPS_LOCK, 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\'', addPair(ENTER, ENTERL, ENTERR));
        addRow(builder, addPair(SHIFT, SHIFTL, SHIFTR), 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/', addPair(SHIFT, SHIFTL, SHIFTR), TRANSPARENT, UP);
        addSpecialRow(builder, 10, CTRL, ICON, ALT, addNestedPair(SPACE, SPACEL, addQuantity(SPACEC, SPACE, 6), SPACER), ALT, FN, CONTEXT, CTRL, LEFT, DOWN, RIGHT, TRANSPARENT);

        RenderEngine.queueSend(textChannel, builder.build(), message -> {
            this.keyboardMessage = message;
        });
    }

    public void removeKeyboard() {
        if (!this.keyboardActive) return;
        if (this.keyboardMessage != null) this.keyboardMessage.delete().queue();
        this.keyboardActive = false;
    }

    private List<Emoji> addQuantity(Emoji emoji, Pair pair, int quantity) {
        var list = new ArrayList<Emoji>();
        addPair(pair, emoji);
        for (int i = 0; i < quantity; i++) list.add(emoji);
        return list;
    }

    private List<Emoji> addNestedPair(Pair pair, Object... emojis) {
        return addPair(pair, Arrays.stream(emojis).flatMap(obj -> obj instanceof List ? ((Stream<Emoji>) ((List<Emoji>) obj).stream()) : Stream.of((Emoji) obj)).toArray(Emoji[]::new));
    }

    private List<Emoji> addPair(Pair pair, Emoji... emojis) {
        var list = Arrays.asList(emojis);
        this.pairs.put(pair, list);
        return list;
    }

    public Optional<Pair> getPair(Emoji emoji) {
        return this.pairs.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(emoji))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    private void addRow(EmbedBuilder builder, Object... keys) {
        addSpecialRow(builder, 9, keys);
    }

    private void addSpecialRow(EmbedBuilder builder, int center, Object... keys) {
        addSpecialRow(builder, center, new ArrayList<>(Arrays.asList(keys)));
    }

    private void addSpecialRow(EmbedBuilder builder, int center, List<Object> keyList) {
        keyList = keyList.stream().flatMap(obj -> obj instanceof List ? ((Stream<Object>) ((List) obj).stream()) : Stream.of(obj)).collect(Collectors.toList());
        var row = new StringBuilder();
        var emojiManager = this.emojIDE.getEmojiManager();
        final var initialSize = 18 - keyList.size();
        for (int i = 0; i < initialSize; i++) keyList.add(TRANSPARENT);

        for (int i = 0; i < keyList.size(); i++) {
            var current = keyList.get(i);
            if (i == center) {
                builder.addField(String.valueOf(ZWS), row.toString(), true);
                row.setLength(0);
            }

            if (current instanceof Boolean) {
                if ((Boolean) current) {
                    row = new StringBuilder(row.toString().stripTrailing());
                }
            } else {
                var display = "";
                var id = "";
                var removeSpace = false;
                if (current instanceof Character) {
                    display = emojiManager.getEmoji(String.valueOf((int) (Character) current)).getDisplay();
                    id = "A" + (int) (Character) current;
                } else if (current instanceof Emoji) {
                    var emoji = (Emoji) current;
                    display = emoji.getDisplay();
                    id = "E" + emoji.ordinal();

                    var next = keyList.size() > i + 1 ? keyList.get(i + 1) : null;
                    if (next instanceof Emoji) removeSpace = this.pairs.values().stream().anyMatch(pair -> pair.contains(current) && pair.contains(next));
                }

                row.append("[").append(display).append("](http://localhost:6969/s?k=").append(id).append(")").append(removeSpace ? "" : " ");
            }
        }

        builder.addField(String.valueOf(ZWS), row.toString(), true);
    }

    public void handleKey(String key) {
        try {
            if (!this.keyboardActive) return;
            var type = key.charAt(0);
            int remaining = Integer.parseInt(key.substring(1));

            if (type == 'A') {
                char clickedChar = (char) remaining;
                raiseEvent(new KeyPressEvent(clickedChar));
            } else {
                Emoji clickedEmoji = Emoji.values()[remaining];
                raiseEvent(new KeyPressEvent(clickedEmoji));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addListener(Object object) {
        Arrays.stream(object.getClass().getDeclaredMethods()).forEach(method -> {
            if (method.getParameterCount() != 1) return;
            var type = method.getParameters()[0].getType();
            if (method.getParameterCount() == 1 && type.equals(KeyPressEvent.class)) {
                method.setAccessible(true);
                LOGGER.info("Found method {} in {}", method.getName(), object.getClass().getCanonicalName());
                eventClasses.computeIfAbsent(object, i -> new ArrayList<>()).add(method);
            }
        });
    }

    private void raiseEvent(KeyPressEvent event) {
        this.eventClasses.forEach((object, methods) -> {
            methods.forEach(method -> {
                try {
                    method.invoke(object, event);
                } catch (ReflectiveOperationException e) {
                    LOGGER.error("Error while invoking event on " + object.getClass().getCanonicalName() + "#" + method.getName(), e);
                }
            });
        });
    }

}
