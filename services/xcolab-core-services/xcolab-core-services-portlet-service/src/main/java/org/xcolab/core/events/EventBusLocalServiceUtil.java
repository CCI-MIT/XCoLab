package org.xcolab.core.events;

/**
 * A event bus service util that allows sharing of event bus between portlets. 
 * 
 * <p>
 * It provides static methods that access single event bus. In order to use this class it has to be created 
 * once with concrete implementation of event bus see {@link #EventBusLocalServiceUtil(EventBus)}.
 * </p>
 *  
 * @author janusz
 *
 */
public class EventBusLocalServiceUtil {
    
    /**
     * Event bus that will be used to broadcast events.
     */
    private static EventBus eventBus;
    
    /**
     * Creates new instance of EventBusLocalServiceUtil. 
     * 
     * Used to initialize class and allow for static access to event bus.
     * 
     * @param eventBus event bus that should be used by portlets
     */
    public EventBusLocalServiceUtil(EventBus eventBus) {
        EventBusLocalServiceUtil.eventBus = eventBus;
    }
    
    /**
     * Registers a listener,  {@link EventBus#addLinstener(Object)}
     * 
     * @param listener listener to be registered 
     * @return listener registration object
     * @throws EventBusException in case of any error
     */
    public static ListenerRegistration addLinstener(Object listener) throws EventBusException {
        return getEventBus().addLinstener(listener);
        
    }

    /**
     * Removes a listener, {@link EventBus#removeListener(Object)}
     * 
     * @param listener listener to be registered 
     * @return listener registration object
     * @throws EventBusException in case of any error
     */
    public static void removeListener(Object listener) throws EventBusException {
        getEventBus().removeListener(listener);
    }
    

    /**
     * Publishes an event, {@link EventBus#publish(Object)}
     * 
     * @param listener listener to be registered 
     * @return listener registration object
     * @throws EventBusException in case of any error
     */
    public static void publish(Object event) throws EventBusException {
        getEventBus().publish(event);
    }
    
    
    /**
     * Returns a event bus, it checks if object was properly initialized.
     * 
     * @return EventBus to be used 
     * @throws EventBusException if this class wasn't initialized 
     */
    private static EventBus getEventBus() throws EventBusException {
        if (eventBus == null) {
            throw new EventBusException(EventBusLocalServiceUtil.class.getSimpleName() + " wasn't properly initialized, setEventBus has to be called first");
        }
        return EventBusLocalServiceUtil.eventBus;
    }
}
