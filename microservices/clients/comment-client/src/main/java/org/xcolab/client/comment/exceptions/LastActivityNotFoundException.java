package org.xcolab.client.comment.exceptions;

public class LastActivityNotFoundException extends RuntimeException {
    public LastActivityNotFoundException(long threadId) {
        super("Could not find last activity for thread " + threadId);
    }
}
