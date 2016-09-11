package org.xcolab.client.members;

import org.apache.commons.lang3.StringEscapeUtils;

import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.client.members.pojo.SendMessageBean;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.Http429TooManyRequestsException;

import java.util.Collections;
import java.util.List;

public final class MessagingClient {

    private static final RestService memberService = new RestService("members-service");
    private static final RestResource<Member> memberResource = new RestResource<>(memberService,
            "members", Member.TYPES);
    private static final RestResource<Message> messageResource = new RestResource<>(memberService,
            "messages", Message.TYPES);

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
        return messageResource.getSubRestResource(messageId, "recipients", Member.TYPES)
                .list()
                .execute();
    }

    public static void checkLimitAndSendMessage(String subject, String content,
            long fromId, List<Long> recipientIds) throws MessageLimitExceededException {
        try {
            sendMessage(subject, content, fromId, fromId, recipientIds, true);
        } catch (Http429TooManyRequestsException e) {
            throw new MessageLimitExceededException(fromId);
        }
    }

    public static void sendMessage(String subject, String content, Long fromId,
            long replyToId, List<Long> recipientIds) {
        sendMessage(subject, content, fromId, replyToId, recipientIds, false);
    }

    private static void sendMessage(String subject, String content, long fromId, long replyToId,
            List<Long> recipientIds, boolean checkLimit) {
        SendMessageBean sendMessageBean = new SendMessageBean();
        sendMessageBean.setSubject(StringEscapeUtils.unescapeXml(subject));
        sendMessageBean.setContent(content.replaceAll("\n", ""));
        sendMessageBean.setFromId(fromId);
        sendMessageBean.setRepliesTo(replyToId);
        sendMessageBean.setRecipientIds(recipientIds);

        messageResource.create(sendMessageBean)
                .queryParam("checkLimit", checkLimit)
                .execute();
    }

    public static void setArchived(long messageId, long memberId, boolean isArchived) {
        messageResource.getSubServiceResource(messageId, "recipients")
                .query(memberId, Void.class)
                .queryParam("memberId", memberId)
                .queryParam("isArchived", isArchived)
                .put();
    }

    public static void setOpened(long messageId, long memberId, boolean isOpened) {
        messageResource.getSubServiceResource(messageId, "recipients")
                .query(memberId, Void.class)
                .queryParam("memberId", memberId)
                .queryParam("isOpened", isOpened)
                .put();
    }

    public static MessagingUserPreferences getMessagingPreferencesForMember(long memberId) {
        return memberResource.service(memberId, "messagingPreferences", MessagingUserPreferences.class)
                .get();
    }

    public static MessagingUserPreferences createMessagingPreferences(MessagingUserPreferences messagingUserPreferences) {
        return memberResource
                .getSubRestResource(messagingUserPreferences.getUserId(), "messagingPreferences", MessagingUserPreferences.TYPES)
                .create(messagingUserPreferences)
                .execute();
    }

    public static boolean updateMessagingPreferences(MessagingUserPreferences messagingUserPreferences) {
        if (messagingUserPreferences.getMessagingPreferencesId() == null) {
            createMessagingPreferences(messagingUserPreferences);
            return true;
        }
        return memberResource
                .getSubRestResource(messagingUserPreferences.getUserId(), "messagingPreferences", MessagingUserPreferences.TYPES)
                .update(messagingUserPreferences, messagingUserPreferences.getMessagingPreferencesId())
                .execute();
    }

    public static boolean canMemberSendMessage(long memberId) {
        return memberResource.service(memberId, "canSendMessage", Boolean.class)
                .get();
    }
}
