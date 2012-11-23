package org.xcolab.core.events;

/**
 * Event bus that allows components to communicate without knowing each other. 
 * 
 * <p>
 * To use event bus one has to implement an event listener - a class in which there 
 * will be a method annotated with {@link EventListener}. This method has to accept
 * one parameter. Class of this parameter is used as an event type. After registration
 * whenever an event is published with that type, listener will be notified.
 * </p>
 * <p>
 * If listener is no longer needed it should be unregistered either by a call to 
 * {@link #removeListener(Object)} or by {@link ListenerRegistration#unregister()}.  
 * </p>
 * 
 * 
 * @see ListenerRegistration 
 * @see EventListener
 * 
 * @author janusz
 *
 */
public interface EventBus {
    
    /**
     * Registers a listener. 
     * 
     * <p>
     * Listener class is examined for methods that are annotated with {@link EventListener} class and accept exactly one 
     * parameter. Each such method is registered as a event handler for events of type the same as method's parameter.
     * </p> 
     * 
     * @param listener object with methods annotated with {@link EventListener} 
     * @return ListenerRegistration that allows one to unregister listener
     * @throws EventBusException in case of any problem
     */
    ListenerRegistration addLinstener(Object listener) throws EventBusException;
    
    /**
     * Removes a listener.
     * 
     * <p>
     * Reverses actions taken in {@link #addLinstener(Object)}
     * </p>
     * 
     * @param listener a listener object, the same that was used on {@link #addLinstener(Object)} 
     * @throws EventBusException in case of any error
     */
    void removeListener(Object listener) throws EventBusException;
    
    /**
     * Publishes an event.
     * 
     * <p>
     * Event type is taken from event class, each listener listening for such events is notified.
     * </p>
     * 
     * @param event event to be sent
     * @throws EventBusException in case of any error
     */
    void publish(Object event) throws EventBusException;

}
