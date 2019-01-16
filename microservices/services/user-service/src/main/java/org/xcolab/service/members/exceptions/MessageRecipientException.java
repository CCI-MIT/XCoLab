package org.xcolab.service.members.exceptions;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid message recipients")
public class MessageRecipientException extends RuntimeException {
    private MessageRecipientException(long messageId, String message) {
        super("Message " + messageId + ":" + message);
    }

    public static MessageRecipientException empty(long messageId) {
        return new MessageRecipientException(messageId,
                "Message must have at least one recipient");
    }

    public static MessageRecipientException notFound(long messageId,
            Collection<Long> foundRecipientIds, Collection<Long> allRecipientIds) {
        final int numRecipientsNotFound = allRecipientIds.size() - foundRecipientIds.size();
        Collection<Long> unresolvedRecipients =
                CollectionUtils.subtract(allRecipientIds, foundRecipientIds);

        return new MessageRecipientException(messageId, String.format(
                "Could not find %d/%d recipients: %s",
                numRecipientsNotFound, allRecipientIds.size(),
                StringUtils.join(unresolvedRecipients, ", ")));
    }
}
