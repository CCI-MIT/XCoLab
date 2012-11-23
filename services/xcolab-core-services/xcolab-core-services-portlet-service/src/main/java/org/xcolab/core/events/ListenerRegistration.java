package org.xcolab.core.events;

/**
 * Represents a listener registration and provides convinient method of unregistering a listener/handler.
 * 
 * @author janusz
 *
 */
public interface ListenerRegistration {
    /**
     * Returns listener.
     * 
     * @return listener
     */
    Object getListener();
    
    /**
     * Returns event bus.
     * @return event bus
     */
    EventBus getEventBus();
    
    /**
     * Unregisters a listener/handler.
     * 
     * @throws EventBusException
     */
    void unregister() throws EventBusException;
}
