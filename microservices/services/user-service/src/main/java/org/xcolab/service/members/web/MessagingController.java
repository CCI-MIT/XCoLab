package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.IMessagingClient;
import org.xcolab.client.user.exceptions.MessageNotFoundException;
import org.xcolab.client.user.messaging.MessageLimitExceededException;
import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.client.user.pojo.wrapper.MessageWrapper;
import org.xcolab.client.user.pojo.wrapper.SendMessageWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.commons.spring.web.annotation.ListMapping;
import org.xcolab.service.members.domain.messaging.MessageDao;
import org.xcolab.service.members.domain.messaginguserpreferences.MessagingUserPreferenceDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.messaging.MessageLimitManager;
import org.xcolab.service.members.service.messaging.MessagingService;
import org.xcolab.service.members.service.messaging.MessagingUserPreferenceService;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class MessagingController implements IMessagingClient {

    private final MessagingService messagingService;

    private final MessageLimitManager messageLimitManager;

    private final MessageDao messageDao;

    private final MessagingUserPreferenceDao messagingUserPreferencesDao;

    private final MessagingUserPreferenceService messagingUserPreferencesService;

    @Autowired
    public MessagingController(MessageDao messageDao,
            MessagingUserPreferenceDao messagingUserPreferencesDao,
            MessagingUserPreferenceService messagingUserPreferencesService,
            MessagingService messagingService, MessageLimitManager messageLimitManager) {
        this.messageDao = messageDao;
        this.messagingUserPreferencesDao = messagingUserPreferencesDao;
        this.messagingUserPreferencesService = messagingUserPreferencesService;
        this.messagingService = messagingService;
        this.messageLimitManager = messageLimitManager;
    }

    @Override
    @ListMapping("/messages")
    public List<MessageWrapper> getUserMessages(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long recipientId,
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened,
            @RequestParam(required = false) Timestamp sinceDate,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long messageId,
            @RequestParam(required = false) String threadId) throws MessageNotFoundException {

        if (messageId != null && threadId != null) {
            try {
                return messageDao.getFullConversation(messageId, threadId);
            } catch (NotFoundException nfe) {
                throw new MessageNotFoundException(messageId);
            }
        } else {
            final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                    sort);


            return messageDao
                    .findByGiven(paginationHelper, senderId, recipientId, isArchived, isOpened,
                            sinceDate);
        }
    }

    @RequestMapping("/messages/count")
    public Integer countUserMessages(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long recipientId,
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened,
            @RequestParam(required = false) Timestamp sinceDate,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long messageId,
            @RequestParam(required = false) String threadId) {


        return messageDao.countByGiven(senderId, recipientId, isArchived, isOpened,
                sinceDate);

    }

    @Override
    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public MessageWrapper getMessage(@PathVariable long messageId) throws MessageNotFoundException {
        try {
            return messageDao.getMessage(messageId);
        } catch (NotFoundException e) {
            throw new MessageNotFoundException(messageId);
        }
    }

    @Override
    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.GET)
    public List<UserWrapper> getMessageRecipients(@PathVariable long messageId) {
        return messageDao.getRecipients(messageId);
    }

    @Override
    @RequestMapping(value = "/messages/{messageId}/threads", method = RequestMethod.GET)
    public List<String> getMessageThreads(@PathVariable long messageId) {
        return messageDao.getThreads(messageId);
    }

    @Override
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public MessageWrapper createMessage(@RequestBody SendMessageWrapper sendMessageBean,
            @RequestParam(required = false, defaultValue = "true") boolean checkLimit,
            @RequestParam(required = false) String threadId)
            throws MessageLimitExceededException {
        return messagingService
                .sendMessage(sendMessageBean, sendMessageBean.getRecipientIds(), checkLimit,
                        threadId);
    }

    @Override
    @RequestMapping(value = "/messages/{messageId}/recipients/{userId}", method = RequestMethod.PUT)
    public boolean updateRecipientStatus(@PathVariable long messageId, @PathVariable long userId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened) {
        boolean success = true;
        if (isOpened != null) {
            success = messageDao.setOpened(messageId, userId, isOpened);
        }
        if (isArchived != null) {
            success = success && messageDao.setArchived(messageId, userId, isArchived);
        }
        return success;
    }

    @Override
    @GetMapping("/members/{userId}/messagingPreferences")
    public MessagingUserPreferenceWrapper getMessagingPreferences(@PathVariable long userId) {
        return messagingUserPreferencesService.getByuserId(userId);
    }

    @Override
    @PutMapping("/members/{userId}/messagingPreferences/{messagingPreferencesId}")
    public boolean updateMessagingPreferences(@PathVariable long userId,
            @PathVariable long messagingPreferencesId,
            @RequestBody MessagingUserPreferenceWrapper messagingUserPreferences) {
        return messagingUserPreferencesDao.update(messagingUserPreferences);
    }

    @Override
    @PostMapping("/members/{userId}/messagingPreferences")
    public MessagingUserPreferenceWrapper createMessagingPreferences(@PathVariable long userId,
            @RequestBody MessagingUserPreferenceWrapper messagingUserPreferences) {
        return messagingUserPreferencesDao.create(messagingUserPreferences)
                .orElseThrow(() -> new InternalException(
                        "Could not retrieve id of created messagingPreferences: "
                                + messagingUserPreferences));
    }

    @Override
    @RequestMapping(value = "/members/{userId}/canSendMessage", method = RequestMethod.GET)
    public boolean canUserSendMessage(@PathVariable long userId,
            @RequestParam(required = false, defaultValue = "1") int messagesToSend) {
        return messageLimitManager.canSendMessages(messagesToSend, userId);
    }

    @Override
    @RequestMapping(value = "/members/{userId}/numberOfMessagesLeft", method = RequestMethod.GET)
    public int getNumberOfMessagesLeft(@PathVariable long userId) {
        return messageLimitManager.getNumberOfMessagesLeft(userId);
    }
}
