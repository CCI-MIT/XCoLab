package org.xcolab.core.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ListenerMethod {
    private final Object listener;
    private final Method method;
    
    
    public ListenerMethod(Object listener, Method method) {
        super();
        this.listener = listener;
        this.method = method;
    }
    public Object getListener() {
        return listener;
    }
    public Method getMethod() {
        return method;
    }
    
    public void publish(Object event) throws EventBusException {
        try {
            method.invoke(listener, event);
        } catch (IllegalAccessException e) {
            throw new EventBusException("Can't execute method listener", e);
        } catch (IllegalArgumentException e) {
            throw new EventBusException("Can't execute method listener", e);
        } catch (InvocationTargetException e) {
            throw new EventBusException("Can't execute method listener", e);
        }
    }
    

}
