package tqlslib.api;

import java.util.function.Consumer;

import tqlslib.event.TQLEvent;

public interface EventBus {
    <T extends TQLEvent> void subscribe(Class<T> eventType, Consumer<T> handler);
    <T extends TQLEvent> void unsubscribe(Class<T> eventType, Consumer<T> handler);
    <T extends TQLEvent> void post(T event);
}
