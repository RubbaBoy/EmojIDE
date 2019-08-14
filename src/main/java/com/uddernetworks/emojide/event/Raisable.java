package com.uddernetworks.emojide.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

public abstract class Raisable<T extends Event> {

    private static Logger LOGGER = LoggerFactory.getLogger(Raisable.class);

    final String name;
    final Class<T> type;
    Map<Object, List<Method>> eventClasses = new HashMap<>();
    List<Method> suspended = new ArrayList<>();

    public Raisable(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the identifying name of the {@link Raisable}.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the instance of {@link Event} class that will be listened to.
     *
     * @return The event type
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * Adds an object to listen to events from the current {@link Raisable}.
     *
     * @param listener The class to listen
     * @return The current {@link Raisable}
     */
    public Raisable addListener(Object listener) {
        LOGGER.info("Added listener {} ({})", listener, listener.getClass().getCanonicalName());
        Class<?> current = listener.getClass();
        registerListener(listener, current);
        while (current.getSuperclass() != null) {
            if (Object.class.equals(current = current.getSuperclass())) break;
            LOGGER.info("Adding superclass {} ({})", listener, current.getCanonicalName());
            registerListener(listener, current);
        }
        return this;
    }

    private void registerListener(Object listener, Class clazz) {
        LOGGER.info("Registering {} ({})", listener, clazz.getCanonicalName());
        Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
            if (method.getParameterCount() != 1) return;
            var type = method.getParameters()[0].getType();
            if (method.getParameterCount() == 1 && type.equals(this.type)) {
                method.setAccessible(true);

                var handler = method.getAnnotation(Handler.class);
                if (handler == null || !handler.event().equalsIgnoreCase(name)) return;

                eventClasses.computeIfAbsent(listener, i -> new ArrayList<>()).add(method);
            }
        });
    }

    /**
     * Removes the given listening object instance from listening to events.
     *
     * @param listener The class to remove
     * @return The current {@link Raisable}
     */
    public Raisable removeListener(Object listener) {
        eventClasses.remove(listener);
        return this;
    }

    /**
     * Raises the given {@link Event}
     *
     * @param event The {@link Event} to raise
     */
    public void raise(T event) {
        var cancellable = event instanceof Cancellable ? (Cancellable) event : null;
        var allMethods = new ArrayList<Map.Entry<Object, Method>>();
        new HashMap<>(this.eventClasses).forEach((object, methods) -> methods.forEach(method -> allMethods.add(new AbstractMap.SimpleEntry<>(object, method))));
        allMethods.sort(Comparator.comparingInt(entry -> entry.getValue().getAnnotation(Handler.class).priority().getPriority()));
        Collections.reverse(allMethods);

        LOGGER.info("====================================");
        for (Map.Entry<Object, Method> handler : allMethods) {
            var object = handler.getKey();
            var method = handler.getValue();
            LOGGER.info("Handler {}#{}", object.getClass().getSimpleName(), method.getName());
            try {
                method.invoke(object, event);
            } catch (ReflectiveOperationException | IllegalArgumentException e) {
                LOGGER.error("Error while invoking event on " + object.getClass().getCanonicalName() + "#" + method.getName(), e);
            }
            if (cancellable != null && cancellable.isCancelled()) {
                LOGGER.info("Event cancelled!!!!!!!");
                break;
            }
        }

        new HashMap<>(this.eventClasses).keySet()
                .stream()
                .filter(object -> object instanceof Class)
                .forEach(eventClasses::remove);
    }

    /**
     * Saves and then disabled all listeners. They may be resumed via {@link #resumeListeners()}.
     */
    public void suspendListeners() {
        if (!suspended.isEmpty()) return;
        eventClasses.forEach((object, methods) -> methods.stream().filter(method -> !method.getAnnotation(Handler.class).overrideSuspend()).forEach(suspended::add));

        eventClasses.values().forEach(methods -> methods.removeAll(suspended));
        LOGGER.info("All keyboard listeners have been suspended");
    }

    /**
     * Enabled all listeners disabled with {@link #suspendListeners()}.
     */
    public void resumeListeners() {
        suspended.forEach(method -> eventClasses.computeIfAbsent(method.getDeclaringClass(), i -> new ArrayList<>()).add(method));
        suspended.clear();
        LOGGER.info("All keyboard listeners have been reset back to the last suspend");
    }
}
