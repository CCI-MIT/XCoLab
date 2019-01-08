package org.xcolab.client.comment.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ThreadNotFoundException extends EntityNotFoundException {

    public ThreadNotFoundException(long threadId) {
        super("Thread with id " + threadId + " not found.", ThreadNotFoundException.class);
    }
}
