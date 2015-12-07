package org.climatecollaboratorium.events;

import java.io.Serializable;

public interface EventBus extends Serializable {
    HandlerRegistration registerHandler(Class<? extends Event> eventType, EventHandler handler);
    void fireEvent(Event event);
    void unregister(HandlerRegistration handlerRegistration);

}
