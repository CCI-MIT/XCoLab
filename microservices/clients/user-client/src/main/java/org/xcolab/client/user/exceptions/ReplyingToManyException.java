package org.xcolab.client.user.exceptions;

import java.util.List;

public class ReplyingToManyException extends IllegalStateException {
    public ReplyingToManyException (List<Long> recipientIds, String threadId) {
        super("You are trying to use threadId "+threadId+ " with "+recipientIds.size()+ " different recipients");
    }
}
