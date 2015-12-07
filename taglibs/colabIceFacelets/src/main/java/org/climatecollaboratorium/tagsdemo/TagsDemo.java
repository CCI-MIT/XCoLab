package org.climatecollaboratorium.tagsdemo;

import java.util.ArrayList;
import java.util.List;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;

public class TagsDemo {
    private EventBus eventBus;
    private String tag = "empty";
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    
    public EventBus getEventBus() {
        return eventBus;
    }
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bindEvents();
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    private void bindEvents() {
        // unregister old handlers
        for (HandlerRegistration registration: handlerRegistrations) {
            registration.unregister();
        }
        handlerRegistrations.clear();

        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                // check if event
                if (event.hasSource("tagsdemo")) {
                    tag = event.getParameters("tagsdemo").get("tag");
                }
            }
        }));
    }
    
    

}
