package org.xcolab.services;

import com.google.common.eventbus.EventBus;

public class EventBusServiceImpl implements EventBusService {
    private EventBus eventBus = new EventBus();

    @Override
    public void register(Object eventHandler) {
        eventBus.register(eventHandler);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }

    @Override
    public void unregister(Object eventHandler) {
        eventBus.unregister(eventHandler);
        
    }
    
    

}
