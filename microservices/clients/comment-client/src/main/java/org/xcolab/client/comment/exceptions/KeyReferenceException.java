package org.xcolab.client.comment.exceptions;

public class KeyReferenceException extends RuntimeException {
    public KeyReferenceException(Exception notFoundException) {
        super(notFoundException);
    }
}
