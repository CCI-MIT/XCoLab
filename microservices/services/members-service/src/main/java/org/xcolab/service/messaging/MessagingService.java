package org.xcolab.service.messaging;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.domain.messaging.MessageDao;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.model.tables.pojos.User_;

import java.util.List;

@Service
public class MessagingService {

    @Autowired
    private MessageDao messageDao;

    public int countArchivedMessagesForUser(long userId) throws SystemException {
        return messageDao.countByReceivingUserArchived(userId, true);
    }

    public int countUnreadMessagesForUser(long userId) {
        return messageDao.countByReceivingUserOpened(userId, false);
    }

    public int countMessagesForUser(long userId) {
        return messageDao.countByReceivingUserArchived(userId, false);
    }

    public int countSentMessagesForUser(long userId) {
        return messageDao.countBySendingUser(userId);
    }

    public List<Message> findArchivedMessagesForUser(int startRecord, int limitRecord, long userId) {
        return messageDao.findByReceivingUserArchived(startRecord, limitRecord, userId, true);
    }

    public List<Message> findSentMessagesForUser(int startRecord, int limitRecord, long userId) {
        return messageDao.findBySendingUser(startRecord, limitRecord, userId);
    }

    public List<Message> findMessagesForUser(int startRecord, int limitRecord, long userId) {
        return messageDao.findByReceivingUserArchived(startRecord, limitRecord, userId, false);
    }

    //TODO: combine into sendMessage method
    public void createMessage(long messageId, long senderId, long repliesToId, String subject, String content) {
        messageDao.createMessage(messageId, senderId, repliesToId, subject, content);
    }
    public void createRecipient(long messageRecipientStatusId, long messageId, long recipientId) {
        messageDao.createMessageRecipient(messageRecipientStatusId, messageId, recipientId);
    }

    public void setArchived(long messageId, long memberId, boolean isArchived) {
        messageDao.setArchived(messageId, memberId, isArchived);
    }

    public void setOpened(long messageId, long memberId, boolean isOpened) {
        messageDao.setOpened(messageId, memberId, isOpened);
    }

    public Message getMessage(long messageId) {
        return messageDao.getMessage(messageId);
    }

    public List<User_> getRecipients(long messageId) {
        return messageDao.getRecipients(messageId);
    }
}
