package org.climatecollaboratorium.events;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventBusImpl implements EventBus {
    @SuppressWarnings("unused")
    private Map<Class<? extends Event>, Set<HandlerRegistration>> eventHandlers = Collections.synchronizedMap(new HashMap<Class<? extends Event>, Set<HandlerRegistration>>());

    @SuppressWarnings("unchecked")
    @Override
    public void fireEvent(Event event) {
        if (eventHandlers.containsKey(event.getClass())) {
            Set<HandlerRegistration> handlers = eventHandlers.get(event.getClass());
            for (HandlerRegistration handler: handlers) {
                handler.getHandler().onEvent(event);
            }
        }
        
    }

    @Override
    public HandlerRegistration registerHandler(Class<? extends Event> eventType, EventHandler handler) {
        if (! eventHandlers.containsKey(eventType)) {
            eventHandlers.put(eventType, new HashSet<HandlerRegistration>());
        }
        
        HandlerRegistration handlerRegistration = new HandlerRegistration(eventType, handler, this);
        
        eventHandlers.get(eventType).add(handlerRegistration);
        
        return handlerRegistration;
    }

    @Override
    public void unregister(HandlerRegistration handlerRegistration) {
        if (eventHandlers.containsKey(handlerRegistration.getEventType())) {
            eventHandlers.get(handlerRegistration.getEventType()).remove(handlerRegistration);
        }
    }
    

}
