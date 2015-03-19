package org.xcolab.exceptions;

/**
 * Created by Thomas on 2/9/2015.
 */
public class TabAuthorizationException extends Exception {

    public TabAuthorizationException() {
    }

    public TabAuthorizationException(String message) {
        super(message);
    }

    public TabAuthorizationException(Throwable cause) {
        super(cause);
    }

    public TabAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TabAuthorizationException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}