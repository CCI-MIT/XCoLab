package org.xcolab.client.members;

import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class MessagingClient {

    private static final RestService membersService = new RestService("members-service");
    private static final RestResource<Message> messageResource = new RestResource<>(membersService,
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
        messageResource.getTypelessSubResource(messageId, "recipients")
                .create(null)
                .queryParam("recipientId", recipientId)
                .execute();
    }

    public static List<Member> getMessageRecipients(long messageId) {
        return messageResource.getSubResource(messageId, "recipients",
                Member.TYPES)
                .list()
                .execute();
    }

    public static void setArchived(long messageId, long memberId, boolean isArchived) {
        //TODO: change to proper put
        UriBuilder uriBuilder = messageResource.getResourceUrl(messageId)
                .queryParam("memberId", memberId)
                .queryParam("isArchived", isArchived);

        RequestUtils.put(uriBuilder, null);
    }

    public static void setOpened(long messageId, long memberId, boolean isOpened) {
        //TODO: change to proper put
        UriBuilder uriBuilder = messageResource.getResourceUrl(messageId)
                .queryParam("memberId", memberId)
                .queryParam("isOpened", isOpened);

        RequestUtils.put(uriBuilder, null);
    }
}
