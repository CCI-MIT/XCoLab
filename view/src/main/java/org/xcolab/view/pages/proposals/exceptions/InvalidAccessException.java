package org.xcolab.view.pages.proposals.exceptions;

public class InvalidAccessException extends RuntimeException {

    public InvalidAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidAccessException(String msg) {
        super(msg);
    }
}
