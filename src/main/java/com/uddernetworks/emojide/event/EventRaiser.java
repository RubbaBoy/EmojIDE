package com.uddernetworks.emojide.event;

import java.util.ArrayList;
import java.util.List;

public class EventRaiser {

    private static List<Raisable> raisables = new ArrayList<>();

    /**
     * Adds the given {@link Raisable} instance to the internal list to be used in the future.
     *
     * @param raisable The {@link Raisable} to add
     */
    public static void createEvent(Raisable raisable) {
        raisables.add(raisable);
    }

    /**
     * Raises the given {@link Event} to invoke all listeners.
     *
     * @param clazz The class of the {@link Raisable} added
     * @param event The {@link Event} to raise
     * @param <T> The {@link Event} type being risen
     */
    public static <T extends Event> void raiseEvent(Class clazz, T event) {
        getRaisable(clazz).raise(event);
    }

    /**
     * Raises the given {@link Event} to invoke all listeners.
     *
     * @param name The name of the {@link Raisable} added
     * @param event The {@link Event} to raise
     * @param <T> The {@link Event} type being risen
     */
    public static <T extends Event> void raiseEvent(String name, T event) {
        getRaisable(name).raise(event);
    }

    /**
     * Gets a {@link Raisable} by the given name, throwing if not found.
     *
     * @param clazz The class ofd the {@link Raisable}
     * @return The found {@link Raisable}
     */
    public static Raisable getRaisable(Class clazz) {
        return raisables.stream().filter(raisable -> raisable.getClass().equals(clazz)).findFirst().orElseThrow();
    }

    /**
     * Gets a {@link Raisable} by the given name, throwing if not found.
     *
     * @param name The {@link Raisable} name
     * @return The found {@link Raisable}
     */
    public static Raisable getRaisable(String name) {
        return raisables.stream().filter(raisable -> raisable.getName().equalsIgnoreCase(name)).findFirst().orElseThrow();
    }

}
