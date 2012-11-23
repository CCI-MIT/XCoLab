package org.xcolab.core.base;

/** 
 * Base exception for exceptions thrown by XColab. 
 * 
 * @author janusz
 *
 */
public class XColabException extends Exception {

    public XColabException() {
    }

    public XColabException(String message) {
        super(message);
    }

    public XColabException(Throwable cause) {
        super(cause);
    }

    public XColabException(String message, Throwable cause) {
        super(message, cause);
    }

    public XColabException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
