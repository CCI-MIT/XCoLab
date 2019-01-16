package org.xcolab.client.user.exceptions;

public class ThreadNotFoundException extends Exception {
    public ThreadNotFoundException(String threadId) {
        super("Thread with id " + threadId + " does not exist");
    }
}
