package org.xcolab.client.members.exceptions;

public class MessageOrThreadNotFoundException extends Exception {

    public MessageOrThreadNotFoundException(long messageId, String threadId) {
        super("Message with id " + messageId + " does not exist in thread "+threadId);
    }

}
