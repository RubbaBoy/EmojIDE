package com.uddernetworks.emojide.keyboard;

import com.uddernetworks.emojide.discord.Emoji;
import com.uddernetworks.emojide.discord.StaticEmoji;
import com.uddernetworks.emojide.gui.render.RenderEngine;
import com.uddernetworks.emojide.main.EmojIDE;
import com.uddernetworks.emojide.main.Thread;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.uddernetworks.emojide.discord.StaticEmoji.*;

public class SimpleKeyboardInputManager extends ListenerAdapter implements KeyboardInputManager {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleKeyboardInputManager.class);
    private static final char ZWS = '\u200b';

    private WebListener webListener;

    private EmojIDE emojIDE;
    private boolean keyboardActive;
    private Message keyboardMessage;
    private boolean lowercaseMode = true;
    private ActiveState state = ActiveState.NONE;

    private Supplier<MessageEmbed> lowercaseSupplier;
    private Supplier<MessageEmbed> uppercaseSupplier;

    private Map<Pair, List<Emoji>> pairs = new HashMap<>();
    private Map<Object, List<Method>> eventClasses = new HashMap<>();
    private Map<Object, List<Method>> suspended = new HashMap<>();

    /**
     * Creates a {@link SimpleKeyboardInputManager} and starts the {@link WebListener}.
     *
     * @param emojIDE The {@link EmojIDE}
     */
    public SimpleKeyboardInputManager(EmojIDE emojIDE) {
        this.emojIDE = emojIDE;

        this.webListener = new SimpleWebListener(emojIDE);
        this.webListener.start(this);
        addListener(this);
    }

    @Override
    public void createKeyboard(TextChannel textChannel) {
        if (this.keyboardActive) return;
        this.keyboardActive = true;

        addPair(Pair.CAPS, CAPS_LOCK, CAPS_LOCK_ACTIVE);
        addPair(Pair.ALT, ALT, ALT_ACTIVE);
        addPair(Pair.CTRL, CTRL, CTRL_ACTIVE);
        addPair(Pair.FN, FN, FN_ACTIVE);

        this.lowercaseSupplier = () -> {
            var alt = this.state == ActiveState.ALT ? ALT_ACTIVE : ALT;
            var ctrl = this.state == ActiveState.CTRL ? CTRL_ACTIVE : CTRL;
            var fn = this.state == ActiveState.FN ? FN_ACTIVE : FN;

            var lower = new EmbedBuilder();
            addRow(lower, '`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', BACKSPACE, TRANSPARENT, INS, HOME, PG_UP);
            addRow(lower, TAB, 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\\', TRANSPARENT, DEL, END, PG_DOWN);
            addRow(lower, this.lowercaseMode ? CAPS_LOCK : CAPS_LOCK_ACTIVE, 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\'', addPair(Pair.ENTER, ENTERL, ENTERR));
            addRow(lower, addPair(Pair.SHIFT, SHIFTL, SHIFTR), 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/', addPair(Pair.SHIFT, SHIFTL, SHIFTR), TRANSPARENT, UP);
            addSpecialRow(lower, 10, ctrl, ICON, alt, addNestedPair(Pair.SPACE, SPACEL, addQuantity(SPACEC, Pair.SPACE, 6), SPACER), alt, fn, CONTEXT, ctrl, LEFT, DOWN, RIGHT, TRANSPARENT);
            return lower.build();
        };

        this.uppercaseSupplier = () -> {
            var alt = this.state == ActiveState.ALT ? ALT_ACTIVE : ALT;
            var ctrl = this.state == ActiveState.CTRL ? CTRL_ACTIVE : CTRL;
            var fn = this.state == ActiveState.FN ? FN_ACTIVE : FN;

            var upper = new EmbedBuilder();
            addRow(upper, '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', BACKSPACE, TRANSPARENT, INS, HOME, PG_UP);
            addRow(upper, TAB, 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', '{', '}', '|', TRANSPARENT, DEL, END, PG_DOWN);
            addRow(upper, this.lowercaseMode ? CAPS_LOCK : CAPS_LOCK_ACTIVE, 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ':', '"', addPair(Pair.ENTER, ENTERL, ENTERR));
            addRow(upper, addPair(Pair.SHIFT, SHIFTL, SHIFTR), 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '<', '>', '?', addPair(Pair.SHIFT, SHIFTL, SHIFTR), TRANSPARENT, UP);
            addSpecialRow(upper, 10, ctrl, ICON, alt, addNestedPair(Pair.SPACE, SPACEL, addQuantity(SPACEC, Pair.SPACE, 6), SPACER), alt, fn, CONTEXT, ctrl, LEFT, DOWN, RIGHT, TRANSPARENT);
            return upper.build();
        };

        RenderEngine.queueSend(textChannel, this.lowercaseSupplier.get(), message -> this.keyboardMessage = message);
    }

    @Override
    public void changeToUpper() {
        this.lowercaseMode = false;
        RenderEngine.queueEdit(this.keyboardMessage, this.uppercaseSupplier.get());
    }

    @Override
    public void changeToLower() {
        this.lowercaseMode = true;
        RenderEngine.queueEdit(this.keyboardMessage, this.lowercaseSupplier.get());
    }

    @Override
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

    @Override
    public Optional<Pair> getPair(Emoji emoji) {
        return this.pairs.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(emoji))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    @Override
    public ActiveState getState() {
        return this.state;
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
                } else if (current instanceof StaticEmoji) {
                    var emoji = (StaticEmoji) current;
                    display = emoji.getDisplay();
                    id = "E" + emoji.ordinal();

                    var next = keyList.size() > i + 1 ? keyList.get(i + 1) : null;
                    if (next instanceof Emoji)
                        removeSpace = this.pairs.values().stream().anyMatch(pair -> pair.contains(current) && pair.contains(next));
                }

                row.append("[").append(display).append("](http://localhost:6969/s?k=").append(id).append(")").append(removeSpace ? "" : " ");
            }
        }

        builder.addField(String.valueOf(ZWS), row.toString(), true);
    }

    private CompletableFuture stateChange;
    private Runnable undoPreviousState = () -> {};

    private void onKeyPress(KeyPressEvent event) {
        if (event.isAlphanumeric()) return;
        getPair(event.getStaticEmoji()).ifPresent(pair -> {
            switch (pair) {
                case SHIFT:
                    changeToUpper();
                    unsetStateAfter(this::changeToLower, ActiveState.SHIFT, 3);
                    break;
                case CAPS:
                    if (this.lowercaseMode) {
                        changeToUpper();
                    } else {
                        changeToLower();
                    }
                    break;
                case CTRL:
                    unsetStateAfter(ActiveState.CTRL, 3);
                    refresh();
                    break;
                case ALT:
                    unsetStateAfter(ActiveState.ALT, 3);
                    refresh();
                    break;
                case FN:
                    // Lol there's no function keys to do anything with
                    break;
            }
        });
    }

    private void refresh() {
        if (this.lowercaseMode) {
            changeToLower();
        } else {
            changeToUpper();
        }
    }

    private void unsetStateAfter(ActiveState changingState, long time) {
        unsetStateAfter(() -> {}, changingState, time);
    }

    private void unsetStateAfter(Runnable runnable, ActiveState changingState, long time) {
        unsetStateAfter(runnable, changingState, time, TimeUnit.SECONDS);
    }

    private void unsetStateAfter(Runnable runnable, ActiveState changingState, long time, TimeUnit unit) {
        if (state != ActiveState.NONE) {
            if (stateChange != null) stateChange.cancel(true);
            undoPreviousState.run();
        }
        state = changingState;
        LOGGER.info("State is now {}", state.name());
        undoPreviousState = runnable;
        stateChange = CompletableFuture.runAsync(() -> {
            Thread.sleep(unit.toMillis(time));
            undoPreviousState.run();
            state = ActiveState.NONE;
            refresh();
            LOGGER.info("State is now {}", state.name());
        });
    }

    @Override
    public void handleKey(String key) {
        try {
            if (!this.keyboardActive) return;
            var type = key.charAt(0);
            int remaining = Integer.parseInt(key.substring(1));

            if (type == 'A') {
                char clickedChar = (char) remaining;
                raiseEvent(new KeyPressEvent(clickedChar));
            } else {
                StaticEmoji clickedEmoji = StaticEmoji.values()[remaining];
                raiseEvent(new KeyPressEvent(clickedEmoji));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addListener(Object object) {
        LOGGER.info("Adding listener {}", object.getClass().getCanonicalName());

        Class<?> current = object.getClass();
        while(current.getSuperclass() != null){ // we don't want to process Object.class
            // do something with current's fields
            Class<?> finalCurrent = current = current.getSuperclass();
            Arrays.stream(finalCurrent.getDeclaredMethods()).forEach(method -> {
                LOGGER.info("Method = {}", method.getName());
                if (method.getParameterCount() != 1) return;
                var type = method.getParameters()[0].getType();
                if (method.getParameterCount() == 1 && type.equals(KeyPressEvent.class)) {
                    method.setAccessible(true);
                    LOGGER.info("Found method {} in {}", method.getName(), finalCurrent.getCanonicalName());
                    eventClasses.computeIfAbsent(object, i -> new ArrayList<>()).add(method);
                }
            });
        }
    }

    @Override
    public void removeListener(Object object) {
        eventClasses.remove(object);
    }

    @Override
    public void suspendListeners() {
        if (!suspended.isEmpty()) return;
        suspended = new HashMap<>(eventClasses);
        new HashSet<>(eventClasses.keySet()).stream().filter(obj -> !obj.equals(this)).forEach(eventClasses::remove);
        LOGGER.info("All keyboard listeners have been suspended");
    }

    @Override
    public void resumeListeners() {
        new HashSet<>(eventClasses.keySet()).stream().filter(obj -> !obj.equals(this)).forEach(eventClasses::remove);
        suspended.forEach(eventClasses::put);
        suspended.clear();
        LOGGER.info("All keyboard listeners have been reset back to the last suspend");
    }

    private void raiseEvent(KeyPressEvent event) {
        new HashMap<>(this.eventClasses).forEach((object, methods) -> {
            methods.forEach(method -> {
                if (method.getName().equals("raiseEvent") && object == this) return;
                try {
                    method.invoke(object, event);
                } catch (ReflectiveOperationException e) {
                    LOGGER.error("Error while invoking event on " + object.getClass().getCanonicalName() + "#" + method.getName(), e);
                }
            });
        });
    }

}
