package org.xcolab.core.events;

public class ListenerRegistrationImpl implements ListenerRegistration {
    private final Object listener;
    private final EventBus eventBus;
    
    public ListenerRegistrationImpl(Object listener, EventBus eventBus) {
        this.listener = listener;
        this.eventBus = eventBus;
    }
    

    public Object getListener() {
        return listener;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void unregister() throws EventBusException {
        eventBus.removeListener(listener);
    }

}
