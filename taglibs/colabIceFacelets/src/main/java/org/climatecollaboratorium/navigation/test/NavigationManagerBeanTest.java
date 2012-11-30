package org.climatecollaboratorium.navigation.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.navigation.NavigationEvent;


public class NavigationManagerBeanTest {
    private EventBus eventBus;
    private List<String> navigationLog = new ArrayList<String>(); 

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bind();
    }
    
    private void bind() {
        eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                for (String source: event.getSources()) {
                    navigationLog.add(0, new Date() + "Navigation event: " + source + "\tparameters: " + event.getParameters(source));
                }
            }
        });
    }

    public List<String> getNavigationLog() {
        return navigationLog;
    }
    

}