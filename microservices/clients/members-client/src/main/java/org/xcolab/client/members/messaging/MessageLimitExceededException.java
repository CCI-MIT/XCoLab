package org.xcolab.client.members.messaging;

public class MessageLimitExceededException extends Exception  {
    public MessageLimitExceededException(long userId) {
        super("Member " + userId + " exceeded their daily message limit");
    }
}
