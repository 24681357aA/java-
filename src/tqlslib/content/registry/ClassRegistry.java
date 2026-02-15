package tqlslib.content.registry;

import arc.struct.ObjectMap;
import arc.util.Log;

public class ClassRegistry {
    private static final String DEFAULT_PREFIX = "tqls-";
    private static final ObjectMap<String, Class<?>> registry = new ObjectMap<>();
    private static String prefix = DEFAULT_PREFIX;

    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void register(String name, Class<?> clazz) {
        String fullName = prefix + name;
        registry.put(fullName, clazz);
        Log.info("Registered class: " + fullName);
    }

    public static void registerWithoutPrefix(String name, Class<?> clazz) {
        registry.put(name, clazz);
        Log.info("Registered class (no prefix): " + name);
    }

    public static Class<?> get(String name) {
        String fullName = prefix + name;
        Class<?> clazz = registry.get(fullName);
        if (clazz == null) {
            clazz = registry.get(name);
        }
        return clazz;
    }

    public static boolean has(String name) {
        String fullName = prefix + name;
        return registry.containsKey(fullName) || registry.containsKey(name);
    }

    public static void unregister(String name) {
        String fullName = prefix + name;
        registry.remove(fullName);
        registry.remove(name);
        Log.info("Unregistered class: " + name);
    }

    public static void clear() {
        registry.clear();
        Log.info("Cleared class registry");
    }

    public static int size() {
        return registry.size;
    }

    public static ObjectMap<String, Class<?>> getAll() {
        return registry;
    }
}
