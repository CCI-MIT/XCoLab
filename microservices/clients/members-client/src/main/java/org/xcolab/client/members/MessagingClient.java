package org.xcolab.client.members;

import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Date;
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
            return messageResource.get(messageId).execute();
        } catch (EntityNotFoundException e) {
            throw new MessageNotFoundException(messageId);
        }
    }

    public static List<Message> getMessagesForUser(int firstMessage, int lastMessage, long userId, boolean isArchived) {
        return messageResource.list()
                .addRange(firstMessage, lastMessage)
                .queryParam("recipientId", userId)
                .queryParam("isArchived", isArchived)
                .execute();
    }

    public static List<Message> getSentMessagesForUser(int firstMessage, int lastMessage, long userId) {
        return messageResource.list()
                .addRange(firstMessage, lastMessage)
                .queryParam("senderId", userId).execute();
    }

    public static int getMessageCountForUser(long userId, boolean isArchived) {
        return messageResource.count()
                .queryParam("recipientId", userId)
                .queryParam("isArchived", isArchived)
                .execute();
    }

    public static int getMessageCountForMemberSinceDate(long memberId, Date sinceDate) {
        return messageResource.count()
                .queryParam("recipientId", memberId)
                .queryParam("sinceDate", sinceDate)
                .execute();
    }

    public static int getUnreadMessageCountForUser(long userId) {
        return messageResource.count()
                .queryParam("recipientId", userId)
                .queryParam("isOpened", false)
                .queryParam("isArchived", false)
                .execute();
    }

    public static int getSentMessageCountForUser(long userId) {
        return messageResource.count()
                .queryParam("senderId", userId)
                .execute();
    }

    public static Message createMessage(Message message) {
        return messageResource.create(message).execute();
    }

    public static void createRecipient(long messageId, long recipientId) {
        messageResource.service(messageId, "recipients", String.class)
                .queryParam("recipientId", recipientId)
                .post();
    }

    public static List<Member> getMessageRecipients(long messageId) {
        return messageResource.getSubRestResource(messageId, "recipients",
                Member.TYPES)
                .list()
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
                .getUnchecked();
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
}
