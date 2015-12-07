package org.climatecollaboratorium.events;

public interface EventBus {
    HandlerRegistration registerHandler(Class<? extends Event> eventType, EventHandler handler);
    void fireEvent(Event event);
    void unregister(HandlerRegistration handlerRegistration);

}
