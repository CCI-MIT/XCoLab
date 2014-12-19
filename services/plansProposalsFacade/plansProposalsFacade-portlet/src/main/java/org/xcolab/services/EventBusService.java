package org.xcolab.services;

import com.google.common.eventbus.EventBus;

/**
 * <p>Event bus to allow services to communicate without knowing each other</p>
 * @author janusz
 *
 */
public interface EventBusService {
    /**
     * <p>Registers an event handler @see {@link EventBus#register(Object)}</p>
     * @param eventHandler event handler 
     */
    void register(Object eventHandler);
    
    /**
     * <p>Unregister an  event handler @see {@link EventBus#unregister(Object)}</p>
     * @param eventHandler event handler 
     */
    void unregister(Object eventHandler);

    /**
     * <p>Sends an event @see {@link EventBus#post(Object)}</p>
     * @param eventHandler event handler 
     */
    void post(Object event);
}
