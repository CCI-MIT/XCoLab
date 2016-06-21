package org.xcolab.client.members;

import org.springframework.core.ParameterizedTypeReference;

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
    private static final RestResource messageResource = new RestResource(membersService,
            "messages");

    private MessagingClient() { }

    public static Message getMessage(long messageId) throws MessageNotFoundException {
        UriBuilder uriBuilder = messageResource.getResourceUrl(messageId);
        try {
            return RequestUtils.get(uriBuilder, Message.class);
        } catch (EntityNotFoundException e) {
            throw new MessageNotFoundException(messageId);
        }
    }

    public static List<Message> getMessagesForUser(int firstMessage, int lastMessage, long userId, boolean isArchived) {
        UriBuilder uriBuilder = messageResource.getResourceUrl()
                .addRange(firstMessage, lastMessage)
                .queryParam("recipientId", userId)
                .queryParam("isArchived", isArchived);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Message>>() {
        });
    }

    public static List<Message> getSentMessagesForUser(int firstMessage, int lastMessage, long userId) {
        UriBuilder uriBuilder = messageResource.getResourceUrl()
                .addRange(firstMessage, lastMessage)
                .queryParam("senderId", userId);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Message>>() {
        });
    }

    public static int getMessageCountForUser(long userId, boolean isArchived) {
        UriBuilder uriBuilder = messageResource.getResourceUrl()
                        .queryParam("recipientId", userId)
                        .queryParam("isArchived", isArchived);
        return RequestUtils.getCount(uriBuilder);
    }

    public static int getUnreadMessageCountForUser(long userId) {
        UriBuilder uriBuilder = messageResource.getResourceUrl()
                        .queryParam("recipientId", userId)
                        .queryParam("isOpened", false)
                        .queryParam("isArchived", false);
        return RequestUtils.getCount(uriBuilder);
    }

    public static int getSentMessageCountForUser(long userId) {
        UriBuilder uriBuilder = messageResource.getResourceUrl()
                        .queryParam("senderId", userId);
        return RequestUtils.getCount(uriBuilder);
    }

    public static void createMessage(Message message) {
        UriBuilder uriBuilder = messageResource.getResourceUrl();

        RequestUtils.post(uriBuilder, message, String.class);
    }

    public static void createRecipient(long messageId, long recipientStatusId, long recipientId) {
        UriBuilder uriBuilder = messageResource.getResourceUrl(messageId)
                .queryParam("recipientStatusId", recipientStatusId)
                .queryParam("recipientId", recipientId);

        RequestUtils.post(uriBuilder, null, String.class);
    }

    public static List<Member> getMessageRecipients(long messageId) {
        UriBuilder uriBuilder = messageResource.getSubResource(messageId, "recipients")
                .getResourceUrl();
        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Member>>() {
        });
    }

    public static void setArchived(long messageId, long memberId, boolean isArchived) {
        UriBuilder uriBuilder = messageResource.getResourceUrl(messageId)
                .queryParam("memberId", memberId)
                .queryParam("isArchived", isArchived);

        RequestUtils.put(uriBuilder, null);
    }

    public static void setOpened(long messageId, long memberId, boolean isOpened) {
        UriBuilder uriBuilder = messageResource.getResourceUrl(messageId)
                .queryParam("memberId", memberId)
                .queryParam("isOpened", isOpened);

        RequestUtils.put(uriBuilder, null);
    }
}
