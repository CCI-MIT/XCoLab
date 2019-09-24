package org.xcolab.util.http.exceptions;

public class RuntimeEntityNotFoundException extends RuntimeException {

    private static final String JSON_FORMAT = "{ \"exception\": \"%s\", \"message\": \"%s\" }";

    public RuntimeEntityNotFoundException(String message) {
        this(message, RuntimeEntityNotFoundException.class);
    }

    public RuntimeEntityNotFoundException(String message,
            Class<? extends RuntimeEntityNotFoundException> exceptionType) {
        super(String.format(JSON_FORMAT, exceptionType.getCanonicalName(), message));
    }
}
