package org.xcolab.util.exceptions;

public class DatabaseAccessException extends RuntimeException {
    public DatabaseAccessException(String msg) {
        super(msg);
    }

    public DatabaseAccessException(Throwable cause) {
        super(cause);
    }

    public DatabaseAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
