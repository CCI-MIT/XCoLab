package org.xcolab.client.members;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.exceptions.MessageOrThreadNotFoundException;
import org.xcolab.client.members.exceptions.ReplyingToManyException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.client.members.pojo.MessagingUserPreference;
import org.xcolab.client.members.pojo.SendMessageBean;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.Http429TooManyRequestsException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.util.Collections;
import java.util.List;

public final class MessagingClient {

    private static final RestResource1<Member, Long> memberResource =
            new RestResource1<>(UserResource.USER, Member.TYPES);
    private static final RestResource1<Message, Long> messageResource =
            new RestResource1<>(UserResource.MESSAGES, Message.TYPES);


    private static final RestResource2L<Member, MessagingUserPreference> messagePreferencesResource
            = new RestResource2L<>(memberResource, "messagingPreferences", MessagingUserPreference.TYPES);

    private static final RestResource2L<Message, Member> messageRecipientResource =
            new RestResource2L<>(messageResource, "recipients", Member.TYPES);

    private static final RestResource2L<Message, String> messageThreadResource =
            new RestResource2L<>(messageResource, "threads",
                    new TypeProvider<>(String.class, new ParameterizedTypeReference<List<String>>() {}));



    private MessagingClient() { }

    public static Message getMessage(long messageId) throws MessageNotFoundException {
        try {
            return messageResource.get(messageId).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new MessageNotFoundException(messageId);
        }
    }

    public static List<Message> getMessages(long userId, int pagerStart, int pagerNext, MessageType type) {
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

    private static List<Message> getMessagesForUser(int firstMessage, int lastMessage, long userId,
            boolean isArchived) {
        return messageResource.list()
                .addRange(firstMessage, lastMessage)
                .queryParam("recipientId", userId)
                .queryParam("isArchived", isArchived)
                .execute();
    }

    private static List<Message> getSentMessagesForUser(int firstMessage, int lastMessage,
            long userId) {
        return messageResource.list()
                .addRange(firstMessage, lastMessage)
                .queryParam("senderId", userId).execute();
    }

    public static int countMessages(long userId, MessageType type) {

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

    public static List<Message> getFullConversation(long messageId, String threadId) throws MessageOrThreadNotFoundException {
        try {
            return messageResource.list()
                    .queryParam("messageId",messageId)
                    .queryParam("threadId", threadId)
                    .execute();
        } catch (UncheckedEntityNotFoundException e) {
            throw new MessageOrThreadNotFoundException(messageId, threadId);
        }
    }

    private static int countMessagesForUser(long userId, boolean isArchived) {
        return messageResource.count()
                .queryParam("recipientId", userId)
                .queryParam("isArchived", isArchived)
                .execute();
    }

    private static int countSentMessagesForUser(long userId) {
        return messageResource.count()
                .queryParam("senderId", userId)
                .execute();
    }

    public static int countUnreadMessagesForUser(long userId) {
        return messageResource.count()
                .queryParam("recipientId", userId)
                .queryParam("isOpened", false)
                .queryParam("isArchived", false)
                .execute();
    }

    public static List<Member> getMessageRecipients(long messageId) {
        return messageRecipientResource.resolveParentId(messageResource.id(messageId))
                .list()
                .withCache(CacheKeys.withClass(Member.class)
                        .withParameter("messageId", messageId)
                        .withParameter("type", "recipients").asList(), CacheName.MISC_MEDIUM)
                .execute();
    }

    public static List<String> getMessageThreads(long messageId) {
        return messageThreadResource.resolveParentId(messageResource.id(messageId))
                .list().execute();
    }


    public static void checkLimitAndSendMessage(String subject, String content,
            long fromId, List<Long> recipientIds) throws MessageLimitExceededException {
        try {
            sendMessage(subject, content, fromId, null, recipientIds, true);
        } catch (Http429TooManyRequestsException e) {
            throw new MessageLimitExceededException(fromId);
        }
    }
    //Overload this method to accept optionally the threadId
    public static void checkLimitAndSendMessage(String subject, String content,
            long fromId, String threadId, List<Long> recipientsIds) throws MessageLimitExceededException {
        try {
            sendMessage(subject, content, fromId, threadId, recipientsIds, true);
        } catch (Http429TooManyRequestsException e) {
            throw new MessageLimitExceededException(fromId);
        }
    }

    public static void sendMessage(String subject, String content, Long fromId,
            String threadId, List<Long> recipientIds) {
        sendMessage(subject, content, fromId, threadId, recipientIds, false);
    }

    private static void sendMessage(String subject, String content, long fromId, String threadId,
            List<Long> recipientIds, boolean checkLimit) {
        SendMessageBean sendMessageBean = new SendMessageBean();
        sendMessageBean.setSubject(StringEscapeUtils.unescapeXml(subject));
        sendMessageBean.setContent(content.replaceAll("\n", ""));
        sendMessageBean.setFromId(fromId);
        sendMessageBean.setRecipientIds(recipientIds);

        if (StringUtils.isNotEmpty(threadId)) {
            if (sendMessageBean.getRecipientIds().size() == 1) {
                messageResource.create(sendMessageBean)
                        .queryParam("checkLimit", checkLimit)
                        .queryParam("threadId", threadId)
                        .execute();
            } else {
                //You are trying to reply to many, which is not permitted. Throw an exception
                throw new ReplyingToManyException(recipientIds, threadId);
            }
        } else {
            messageResource.create(sendMessageBean)
                    .queryParam("checkLimit", checkLimit)
                    .execute();
        }
    }

    public static void setArchived(long messageId, long userId, boolean isArchived) {
        messageRecipientResource.resolveParentId(messageResource.id(messageId))
                .elementQuery(userId, Void.class)
                .queryParam("userId", userId)
                .queryParam("isArchived", isArchived)
                .put();
    }

    public static void setOpened(long messageId, long userId, boolean isOpened) {
        messageRecipientResource.resolveParentId(messageResource.id(messageId))
                .elementQuery(userId, Void.class)
                .queryParam("userId", userId)
                .queryParam("isOpened", isOpened)
                .put();
    }

    public static MessagingUserPreference getMessagingPreferencesForMember(long userId) {
        return memberResource.elementService(userId, "messagingPreferences", MessagingUserPreference.class)
                .get();
    }

    public static MessagingUserPreference createMessagingPreferences(
            MessagingUserPreference messagingUserPreferences) {
        return messagePreferencesResource.resolveParentId(memberResource.id(messagingUserPreferences.getUserId()))
                .create(messagingUserPreferences)
                .execute();
    }

    public static boolean updateMessagingPreferences(
            MessagingUserPreference messagingUserPreferences) {
        if (messagingUserPreferences.getId() == null) {
            createMessagingPreferences(messagingUserPreferences);
            return true;
        }
        return messagePreferencesResource.resolveParentId(memberResource.id(messagingUserPreferences.getUserId()))
                .update(messagingUserPreferences, messagingUserPreferences.getId())
                .execute();
    }


    public static boolean canMemberSendMessage(long userId, int messagesToSend) {
        return memberResource.elementService(userId, "canSendMessage", Boolean.class)
                .queryParam("messagesToSend", messagesToSend)
                .get();
    }

    public static int getNumberOfMessagesLeft(long userId) {
        return memberResource.elementService(userId, "numberOfMessagesLeft", Integer.class)
                .get();
    }
}
