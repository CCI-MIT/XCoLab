package org.xcolab.client.members.messaging;

public class MessageLimitExceededException extends Exception  {
    public MessageLimitExceededException(long memberId) {
        super("Member " + memberId + " exceeded their daily message limit");
    }
}
