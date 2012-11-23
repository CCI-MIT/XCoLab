package org.xcolab.core.events;

import org.xcolab.core.base.XColabException;

/**
 * Exception thrown by event bus methods.
 * 
 * @author janusz
 *
 */
public class EventBusException extends XColabException {

    private static final long serialVersionUID = 7412879352278216306L;

    public EventBusException() {
        super();
    }

    public EventBusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EventBusException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventBusException(String message) {
        super(message);
    }

    public EventBusException(Throwable cause) {
        super(cause);
    }
    

}
