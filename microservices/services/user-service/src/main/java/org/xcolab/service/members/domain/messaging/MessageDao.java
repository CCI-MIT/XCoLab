package org.xcolab.service.members.domain.messaging;

import org.xcolab.client.user.pojo.IMessage;
import org.xcolab.client.user.pojo.IUser;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface MessageDao {

    IMessage getMessage(long messageId) throws NotFoundException;

    List<IMessage> getFullConversation(long messageId, String threadId) throws NotFoundException;

    int countByGiven(Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened, Timestamp sinceDate);

    List<IMessage> findByGiven(PaginationHelper paginationHelper, Long senderId, Long recipientId,
            Boolean isArchived, Boolean isOpened, Timestamp sinceDate);

    List<IUser> getRecipients(long messageId);

    List<String> getThreads(long messageId);

    boolean setArchived(long messageId, long userId, boolean isArchived);

    boolean setOpened(long messageId, long userId, boolean isOpened);

    Optional<IMessage> createMessage(IMessage message);
    
    void createMessageRecipient(long messageId, long recipientId, String threadId);

    boolean delete(long messageId);
}
