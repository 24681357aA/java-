package tqlslib.content.registry;

import arc.struct.ObjectMap;
import arc.util.Log;

/**
 * Unified class registry for managing class registrations in the mod
 * Supports both prefixed and non-prefixed registration
 */
public class ClassRegistry {
    private static final String DEFAULT_PREFIX = "tqls-";
    private static final ObjectMap<String, Class<?>> registry = new ObjectMap<>();
    private static String prefix = DEFAULT_PREFIX;

    /**
     * Set the registration prefix
     * @param newPrefix New prefix to use
     */
    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    /**
     * Get the current registration prefix
     * @return Current prefix
     */
    public static String getPrefix() {
        return prefix;
    }

    /**
     * Register a class with prefix
     * @param name Class name
     * @param clazz Class object
     */
    public static void register(String name, Class<?> clazz) {
        String fullName = prefix + name;
        registry.put(fullName, clazz);
        Log.info("Registered class: " + fullName);
    }

    /**
     * Register a class without prefix
     * @param name Class name
     * @param clazz Class object
     */
    public static void registerWithoutPrefix(String name, Class<?> clazz) {
        registry.put(name, clazz);
        Log.info("Registered class (no prefix): " + name);
    }

    /**
     * Get a registered class
     * First tries with prefix, then without
     * @param name Class name
     * @return Registered class object, or null if not found
     */
    public static Class<?> get(String name) {
        String fullName = prefix + name;
        Class<?> clazz = registry.get(fullName);
        if (clazz == null) {
            clazz = registry.get(name);
        }
        return clazz;
    }

    /**
     * Check if a class is registered
     * @param name Class name
     * @return true if registered
     */
    public static boolean has(String name) {
        String fullName = prefix + name;
        return registry.containsKey(fullName) || registry.containsKey(name);
    }

    /**
     * Unregister a class
     * @param name Class name
     */
    public static void unregister(String name) {
        String fullName = prefix + name;
        registry.remove(fullName);
        registry.remove(name);
        Log.info("Unregistered class: " + name);
    }

    /**
     * Clear the registry
     */
    public static void clear() {
        registry.clear();
        Log.info("Cleared class registry");
    }

    /**
     * Get the number of registered classes
     * @return Number of classes
     */
    public static int size() {
        return registry.size;
    }

    /**
     * Get all registered classes
     * @return ObjectMap containing all registered classes
     */
    public static ObjectMap<String, Class<?>> getAll() {
        return registry;
    }
}
