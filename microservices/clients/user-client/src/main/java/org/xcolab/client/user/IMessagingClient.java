package org.xcolab.client.user;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.MessageNotFoundException;
import org.xcolab.client.user.exceptions.MessageOrThreadNotFoundException;
import org.xcolab.client.user.exceptions.ReplyingToManyException;
import org.xcolab.client.user.legacy.enums.MessageType;
import org.xcolab.client.user.messaging.MessageLimitExceededException;
import org.xcolab.client.user.pojo.wrapper.MessageWrapper;
import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.client.user.pojo.wrapper.SendMessageWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.spring.web.annotation.ListMapping;
import org.xcolab.util.http.exceptions.Http429TooManyRequestsException;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@FeignClient("xcolab-user-service")
public interface IMessagingClient {

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    List<MessageWrapper> getUserMessages(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long recipientId,
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened,
            @RequestParam(required = false) Timestamp sinceDate,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long messageId,
            @RequestParam(required = false) String threadId) throws MessageNotFoundException;

    @RequestMapping("/messages/count")
    Integer countUserMessages(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long recipientId,
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened,
            @RequestParam(required = false) Timestamp sinceDate,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long messageId,
            @RequestParam(required = false) String threadId);

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    MessageWrapper getMessage(@PathVariable long messageId) throws MessageNotFoundException;

    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.GET)
    List<UserWrapper> getMessageRecipients(@PathVariable long messageId);

    @RequestMapping(value = "/messages/{messageId}/threads", method = RequestMethod.GET)
    List<String> getMessageThreads(@PathVariable long messageId);

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    MessageWrapper createMessage(@RequestBody SendMessageWrapper sendMessageBean,
            @RequestParam(required = false, defaultValue = "true") boolean checkLimit,
            @RequestParam(required = false) String threadId)
            throws MessageLimitExceededException;

    @RequestMapping(value = "/messages/{messageId}/recipients/{userId}", method = RequestMethod.PUT)
    boolean updateRecipientStatus(@PathVariable long messageId, @PathVariable long userId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened);

    @GetMapping("/members/{userId}/messagingPreferences")
    MessagingUserPreferenceWrapper getMessagingPreferences(@PathVariable long userId);

    @PutMapping("/members/{userId}/messagingPreferences/{messagingPreferencesId}")
    boolean updateMessagingPreferences(@PathVariable long userId,
            @PathVariable long messagingPreferencesId,
            @RequestBody MessagingUserPreferenceWrapper messagingUserPreferences);

    @PostMapping("/members/{userId}/messagingPreferences")
    MessagingUserPreferenceWrapper createMessagingPreferences(@PathVariable long userId,
            @RequestBody MessagingUserPreferenceWrapper messagingUserPreferences);

    @RequestMapping(value = "/members/{userId}/canSendMessage", method = RequestMethod.GET)
    boolean canUserSendMessage(@PathVariable long userId,
            @RequestParam(required = false, defaultValue = "1") int messagesToSend);

    @RequestMapping(value = "/members/{userId}/numberOfMessagesLeft", method = RequestMethod.GET)
    int getNumberOfMessagesLeft(@PathVariable long userId);


    default void checkLimitAndSendMessage(String subject, String content,
            long fromId, List<Long> recipientIds) throws MessageLimitExceededException {
        try {
            sendMessage(subject, content, fromId, null, recipientIds, true);
        } catch (Http429TooManyRequestsException e) {
            throw new MessageLimitExceededException(fromId);
        }
    }

    //Overload this method to accept optionally the threadId
    default void checkLimitAndSendMessage(String subject, String content,
            long fromId, String threadId, List<Long> recipientsIds)
            throws MessageLimitExceededException {
        try {
            sendMessage(subject, content, fromId, threadId, recipientsIds, true);
        } catch (Http429TooManyRequestsException e) {
            throw new MessageLimitExceededException(fromId);
        }
    }

    default void sendMessage(String subject, String content, Long fromId,
            String threadId, List<Long> recipientIds) {
        sendMessage(subject, content, fromId, threadId, recipientIds, false);
    }

    default void sendMessage(String subject, String content, long fromId, String threadId,
            List<Long> recipientIds, boolean checkLimit) {
        SendMessageWrapper sendMessageBean = new SendMessageWrapper();
        sendMessageBean.setSubject(StringEscapeUtils.unescapeXml(subject));
        sendMessageBean.setContent(content.replaceAll("\n", ""));
        sendMessageBean.setFromId(fromId);
        sendMessageBean.setRecipientIds(recipientIds);

        if (StringUtils.isNotEmpty(threadId)) {
            if (sendMessageBean.getRecipientIds().size() == 1) {
                try {
                    createMessage(sendMessageBean, checkLimit, threadId);
                } catch (MessageLimitExceededException mlee) {
                    throw new ReplyingToManyException(recipientIds, threadId);
                }

            } else {
                //You are trying to reply to many, which is not permitted. Throw an exception
                throw new ReplyingToManyException(recipientIds, threadId);
            }
        } else {
            try {
                createMessage(sendMessageBean, checkLimit, null);
            } catch (MessageLimitExceededException mlee) {
                throw new ReplyingToManyException(recipientIds, threadId);
            }

        }
    }

    default int countUnreadMessagesForUser(long userId) {
        return countUserMessages(0, Integer.MAX_VALUE,
                userId, null, false, false,
                null, null, null, null);
    }

    default void setOpened(long messageId, long userId, boolean isOpened) {
        updateRecipientStatus(messageId, userId, null, isOpened);

    }

    default int countMessages(long userId, MessageType type) {

        switch (type) {
            case INBOX:
                return countMessagesForUser(userId, false);
            case ARCHIVED:
                return countMessagesForUser(userId, true);
            case SENT:
                return countSentMessagesForUser(userId);
            default:
                return 0;
        }
    }

    default int countMessagesForUser(long userId, boolean isArchived) {
        return countUserMessages(0, Integer.MAX_VALUE,
                userId, null, isArchived, false,
                null, null, null, null);

    }

    default int countSentMessagesForUser(long userId) {
        return countUserMessages(0, Integer.MAX_VALUE,
                null, userId, null, false,
                null, null, null, null);
    }

    default List<MessageWrapper> getMessages(long userId, int pagerStart, int pagerNext,
            MessageType type) {
        switch (type) {
            case INBOX:
                return getMessagesForUser(pagerStart, pagerNext, userId, false);
            case ARCHIVED:
                return getMessagesForUser(pagerStart, pagerNext, userId, true);
            case SENT:
                return getSentMessagesForUser(pagerStart, pagerNext, userId);
            default:
                return Collections.emptyList();
        }
    }

    default List<MessageWrapper> getMessagesForUser(int firstMessage, int lastMessage, long userId,
            boolean isArchived) {
        try {
            return getUserMessages(firstMessage,
                    lastMessage, userId, null, isArchived, null,
                    null, null, null, null);
        } catch (MessageNotFoundException e) {
            return Collections.emptyList();
        }
    }

    default List<MessageWrapper> getSentMessagesForUser(int firstMessage, int lastMessage,
            long userId) {
        try {
            return getUserMessages(firstMessage,
                    lastMessage, null, userId, null, null,
                    null, null, null, null);
        } catch (MessageNotFoundException e) {
            return Collections.emptyList();
        }
    }

    default List<MessageWrapper> getFullConversation(long messageId, String threadId) throws MessageOrThreadNotFoundException {
        try {
            return getUserMessages(0,
                    Integer.MAX_VALUE, null, null, null, null,
                    null, null, messageId, threadId);
        } catch (MessageNotFoundException e) {
            return Collections.emptyList();
        }
    }

    default void setArchived(long messageId, long userId, boolean isArchived) {
        updateRecipientStatus(messageId,userId,isArchived,null);

    }




}
