package com.uddernetworks.emojide.overengineering;

import com.uddernetworks.emojide.discord.EmojiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectManager {

    private static Logger LOGGER = LoggerFactory.getLogger(ObjectManager.class);

    private static ObjectManager instance;
    private static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (sun.misc.Unsafe) field.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private List<ObjectEntry<?>> objectEntries = new CopyOnWriteArrayList<>();

    // TODO: Add constructor finders based on stored objects for more intelligent object creation.

    /**
     * Creates a class, with the option to skip the invocation of any construction via {@link Unsafe}.
     *
     * @param clazz The class to create an instance of
     * @param skipConstructor If all constructors should be skipped. If false, the default constructor must be available.
     * @param <T> The type of the class
     * @return The class instance
     */
    public static <T> T create(Class<T> clazz, boolean skipConstructor) {
        try {
            if (skipConstructor) {
                return (T) unsafe.allocateInstance(clazz);
            } else {
                return clazz.getConstructor().newInstance();
            }
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Error creating class " + clazz.getCanonicalName(), e);
            throw new RuntimeException("Error creating class " + clazz.getCanonicalName());
        }
    }


    /**
     * Creates a cached class that has the option to expire after X milliseconds. The same as
     * {@link #createExpirable(Class, boolean, long)} but with override being true.
     *
     * @param clazz The class to create an instance of
     * @param expiresAfter The amount of milliseconds until the class will be removed from the cache.
     * @param <T> The type of the class
     * @return The class instance
     */
    public static <T> T createExpirable(Class<T> clazz, long expiresAfter) {
        return createExpirable(clazz, true, expiresAfter);
    }


    /**
     * Creates a cached class that has the option to expire after X milliseconds. A new class be created if override is
     * true.
     *
     * @param clazz The class to create an instance of
     * @param override If any previous created classes should be overridden
     * @param expiresAfter The amount of milliseconds until the class will be removed from the cache.
     * @param <T> The type of the class
     * @return The class instance
     */
    public static <T> T createExpirable(Class<T> clazz, boolean override, long expiresAfter) {
        try {
            if (override) {
                instance.objectEntries.removeIf(entry -> entry.getObject().getClass().equals(clazz));
            } else {
                var cached = instance.getCached(clazz);
                if (cached.isPresent()) return cached.get();
            }

            var created = clazz.getConstructor().newInstance();
            instance.objectEntries.add(new ObjectEntry<>(created, false, true, System.currentTimeMillis() + expiresAfter));
            return created;
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Error creating class " + clazz.getCanonicalName(), e);
            throw new RuntimeException("Error creating class " + clazz.getCanonicalName());
        }
    }

    /**
     * Creates or gets a singleton of the given class, meaning there will only be one instance of the class ever created
     * with the manager.
     *
     * @param clazz The class to create an instance of
     * @param <T> The type of the class
     * @return The class instance
     */
    public static <T> T createSingleton(Class<T> clazz) {
        try {
            var cached = instance.getCached(clazz);
            if (cached.isPresent()) return cached.get();
            var created = clazz.getConstructor().newInstance();
            instance.objectEntries.add(new ObjectEntry<>(created, true, false, 0));
            return created;
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Error creating singleton class " + clazz.getCanonicalName(), e);
            throw new RuntimeException("Error creating singleton class " + clazz.getCanonicalName());
        }
    }

    private <T> Optional<T> getCached(Class<T> clazz) {
        return this.objectEntries.parallelStream().filter(entry -> entry.getObject().getClass().equals(clazz)).findFirst().map(entry -> (T) entry.getObject());
    }

    private void cleanUp() {
        final long accessedAt = System.currentTimeMillis();
        this.objectEntries.removeIf(entry -> entry.isExpires() && entry.getExpiresAt() < accessedAt);
    }

    public static ObjectManager getInstance() {
        if (instance == null) instance = new ObjectManager();
        return instance;
    }
}
