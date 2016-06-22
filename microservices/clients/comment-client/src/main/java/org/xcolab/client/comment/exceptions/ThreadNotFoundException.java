package org.xcolab.client.comment.exceptions;

public class ThreadNotFoundException extends Exception {
    public ThreadNotFoundException(long threadId) {
        super("Thread with id " + threadId + " not found.");
    }
}
