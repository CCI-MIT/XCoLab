package org.xcolab.core.events;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EventBusImpl implements EventBus {
    private Map<Class<?>, Set<ListenerMethod>> listeners = new ConcurrentHashMap<Class<?>, Set<ListenerMethod>>();
    private Map<Object, Set<ListenerMethod>> registrationsPerListener = new ConcurrentHashMap<Object, Set<ListenerMethod>>();

    public ListenerRegistration addLinstener(Object listener) throws EventBusException {
        Class<?> listenerClass = listener.getClass();
        
        Set<ListenerMethod> listenerRegistrations = new HashSet<ListenerMethod>(); 
        
        for (Method method: listenerClass.getMethods()) {
            if (method.isAnnotationPresent(EventListener.class)) {
                Class<?>[] methodParameterTypes = method.getParameterTypes();
                
                if (methodParameterTypes.length != 1) {
                    throw new EventBusException("Event listener method should accept exactly one parameter, unknown method: " + method);
                }
                
                
                
                Set<ListenerMethod> eventListeners = listeners.get(methodParameterTypes[0]);
                if (eventListeners == null) {
                    synchronized(listeners) {
                        eventListeners = listeners.get(methodParameterTypes[0]);
                        if (eventListeners == null) {
                            eventListeners = Collections.synchronizedSet(new HashSet<ListenerMethod>());
                            listeners.put(methodParameterTypes[0], eventListeners);
                        }
                        listeners.put(methodParameterTypes[0], eventListeners);
                    }
                }
                
                ListenerMethod m = new ListenerMethod(listener, method);
                
                eventListeners.add(m);
                listenerRegistrations.add(m);
            }
        }
        registrationsPerListener.put(listener, listenerRegistrations);
        return new ListenerRegistrationImpl(listener, this);
        
    }

    public void removeListener(Object listener) {
        if (registrationsPerListener.containsKey(listener)) {
            for (ListenerMethod lm: registrationsPerListener.get(listener)) {
                Class<?> eventClass = lm.getMethod().getParameterTypes()[0];
                listeners.get(eventClass).remove(lm);
            }
        }
        
    }

    public void publish(Object event) throws EventBusException {
        Set<ListenerMethod> listenerMethods = listeners.get(event.getClass());
        if (listenerMethods != null) {
            for (ListenerMethod lm: listenerMethods) {
                lm.publish(event);
            }
        }
    }
    
    

}
