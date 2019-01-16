package org.xcolab.service.members.domain.messaging;

import org.xcolab.model.tables.pojos.User;
import org.xcolab.model.tables.pojos.Message;

import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface MessageDao {

    Message getMessage(long messageId) throws NotFoundException;

    List<Message> getFullConversation(long messageId, String threadId) throws NotFoundException;

    int countByGiven(Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened, Timestamp sinceDate);

    List<Message> findByGiven(PaginationHelper paginationHelper, Long senderId, Long recipientId,
            Boolean isArchived, Boolean isOpened, Timestamp sinceDate);

    List<User> getRecipients(long messageId);

    List<String> getThreads(long messageId);

    boolean setArchived(long messageId, long userId, boolean isArchived);

    boolean setOpened(long messageId, long userId, boolean isOpened);

    Optional<Message> createMessage(Message message);
    
    void createMessageRecipient(long messageId, long recipientId, String threadId);

    boolean delete(long messageId);
}
