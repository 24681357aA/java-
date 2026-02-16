package tqlslib.event;

import arc.struct.ObjectMap;
import arc.struct.Seq;
import tqlslib.api.EventBus;
import java.util.function.Consumer;

public class EventBusImpl implements EventBus {
    private ObjectMap<Class<?>, Seq<Consumer<?>>> handlers = new ObjectMap<>();
    
    @Override
    public <T extends TQLEvent> void subscribe(Class<T> eventType, Consumer<T> handler) {
        handlers.get(eventType, Seq::new).add(handler);
    }
    
    @Override
    public <T extends TQLEvent> void unsubscribe(Class<T> eventType, Consumer<T> handler) {
        Seq<Consumer<?>> eventHandlers = handlers.get(eventType);
        if (eventHandlers != null) {
            eventHandlers.remove(handler);
        }
    }
    
    @Override
    public <T extends TQLEvent> void post(T event) {
        Seq<Consumer<?>> eventHandlers = handlers.get(event.getClass());
        if (eventHandlers != null) {
            for (Consumer<?> handler : eventHandlers) {
                ((Consumer<T>) handler).accept(event);
            }
        }
    }
}
