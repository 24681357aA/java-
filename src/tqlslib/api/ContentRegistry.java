package tqlslib.api;

public interface ContentRegistry {
    void registerClass(String name, Class<?> clazz);
    Class<?> getClass(String name);
    boolean hasClass(String name);
    String getPrefix();
}
