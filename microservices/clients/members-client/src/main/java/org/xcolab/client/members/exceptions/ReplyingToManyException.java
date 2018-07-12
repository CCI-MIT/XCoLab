package org.xcolab.client.members.exceptions;

import java.util.List;

public class ReplyingToManyException extends Exception {
    public ReplyingToManyException (List<Long> recipientIds, String threadId) {
        super("You are trying to use threadId "+threadId+ " with "+recipientIds.size()+ " different recipients");
    }
}
