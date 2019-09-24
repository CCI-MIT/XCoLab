package org.xcolab.util.http.exceptions;

public class EntityNotFoundException extends Exception {

    private static final String JSON_FORMAT = "{ \"exception\": \"%s\", \"message\": \"%s\" }";

    public EntityNotFoundException(String message) {
        this(message, EntityNotFoundException.class);
    }

    public EntityNotFoundException(String message,
            Class<? extends EntityNotFoundException> exceptionType) {
        super(String.format(JSON_FORMAT, exceptionType.getCanonicalName(), message));
    }
}
