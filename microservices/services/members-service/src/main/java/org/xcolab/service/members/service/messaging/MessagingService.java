package org.xcolab.service.members.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Message;
import org.xcolab.service.members.domain.messaging.MessageDao;

import java.util.Optional;

@Service
public class MessagingService {

    @Autowired
    private MessageDao messageDao;

    //TODO: combine into sendMessage method
    public Optional<Message> createMessage(Message message) {
        return messageDao.createMessage(message);
    }

    public void createRecipient(long messageId, long recipientId) {
        messageDao.createMessageRecipient(messageId, recipientId);
    }
}
