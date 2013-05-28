package org.climatecollaboratorium.events;

import java.io.Serializable;

public class HandlerRegistration implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<? extends Event> eventType;
    private EventHandler handler;
    private EventBus eventBus;
    
    public HandlerRegistration(Class<? extends Event> eventType, EventHandler handler, EventBus eventBus) {
        this.eventType = eventType;
        this.handler = handler;
        this.eventBus = eventBus;
    }
    
    public Class<? extends Event> getEventType() {
        return eventType;
    }
    public void setEventType(Class<? extends Event> eventType) {
        this.eventType = eventType;
    }
    public EventHandler getHandler() {
        return handler;
    }
    public void setHandler(EventHandler handler) {
        this.handler = handler;
    }
    
    public void unregister() {
        eventBus.unregister(this);
    }
}
