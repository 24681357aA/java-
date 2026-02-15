package tqlslib.api;

import java.util.function.Consumer;

public interface EventBus {
    <T> void subscribe(Class<T> eventType, Consumer<T> handler);
    <T> void unsubscribe(Class<T> eventType, Consumer<T> handler);
    <T> void post(T event);
}
