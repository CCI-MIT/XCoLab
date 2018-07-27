package org.xcolab.client.members.exceptions;

public class ThreadNotFoundException extends Exception {
    public ThreadNotFoundException(String threadId) {
        super("Thread with id " + threadId + " does not exist");
    }
}
