package org.xcolab.service.members.domain.messaging;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface MessageDao {

    Message getMessage(long messageId) throws NotFoundException;

    int countByGiven(Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened);

    List<Message> findByGiven(PaginationHelper paginationHelper, Long senderId, Long recipientId,
            Boolean isArchived, Boolean isOpened);

    List<Member> getRecipients(long messageId);

    boolean setArchived(long messageId, long memberId, boolean isArchived);

    boolean setOpened(long messageId, long memberId, boolean isOpened);

    Optional<Message> createMessage(Message message);

    void createMessageRecipient(long messageId, long recipientId);
}
