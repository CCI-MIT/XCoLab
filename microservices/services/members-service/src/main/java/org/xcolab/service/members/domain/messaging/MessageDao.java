package org.xcolab.service.members.domain.messaging;

import net.sf.ehcache.search.expression.Not;

import org.xcolab.model.tables.pojos.Member;
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

    List<Member> getRecipients(long messageId);

    List<String> getThreads(long messageId);

    long getLastMessageId(String threadId) throws NotFoundException;

    boolean setArchived(long messageId, long memberId, boolean isArchived);

    boolean setOpened(long messageId, long memberId, boolean isOpened);

    Optional<Message> createMessage(Message message);
    
    void createMessageRecipient(long messageId, long recipientId, String threadId);

    boolean delete(long messageId);
}
