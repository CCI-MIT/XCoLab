package org.xcolab.client.comment.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class LastActivityNotFoundException extends RuntimeEntityNotFoundException {

    public LastActivityNotFoundException(long threadId) {
        super("Could not find last activity for thread " + threadId,
                LastActivityNotFoundException.class);
    }
}
