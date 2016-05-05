package org.xcolab.client.members;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;

public final class MessagingClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/members-service";

    private MessagingClient() { }

    public static Message getMessage(long messageId) throws MessageNotFoundException {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId);
        try {
            return RequestUtils.get(uriBuilder, Message.class);
        } catch (EntityNotFoundException e) {
            throw new MessageNotFoundException(messageId);
        }
    }

    public static List<Message> getMessagesForUser(int firstMessage, int lastMessage, long userId, boolean isArchived) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("recipientId", userId)
                        .queryParam("firstRecord", firstMessage)
                        .queryParam("lastRecord", lastMessage);

        uriBuilder.queryParam("isArchived", isArchived);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Message>>() {
        });
    }

    public static List<Message> getSentMessagesForUser(int firstMessage, int lastMessage, long userId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("senderId", userId)
                        .queryParam("firstRecord", firstMessage)
                        .queryParam("lastRecord", lastMessage);

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Message>>() {
        });
    }

    public static int getMessageCountForUser(long userId, boolean isArchived) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("recipientId", userId)
                        .queryParam("isArchived", isArchived);
        return RequestUtils.getCount(uriBuilder);
    }

    public static int getUnreadMessageCountForUser(long userId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("recipientId", userId)
                        .queryParam("isOpened", false)
                        .queryParam("isArchived", false);
        return RequestUtils.getCount(uriBuilder);
    }

    public static int getSentMessageCountForUser(long userId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("senderId", userId);
        return RequestUtils.getCount(uriBuilder);
    }

    public static void createMessage(Message message) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages");

        RequestUtils.post(uriBuilder, message, String.class);
    }

    public static void createRecipient(long messageId, long recipientStatusId, long recipientId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients")
                .queryParam("recipientStatusId", recipientStatusId)
                .queryParam("recipientId", recipientId);

        RequestUtils.post(uriBuilder, null, String.class);
    }

    public static List<Member> getMessageRecipients(long messageId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients");
        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<Member>>() {
        });
    }

    public static void setArchived(long messageId, long memberId, boolean isArchived) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId)
                .queryParam("memberId", memberId)
                .queryParam("isArchived", isArchived);

        RequestUtils.put(uriBuilder, null);
    }

    public static void setOpened(long messageId, long memberId, boolean isOpened) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId)
                .queryParam("memberId", memberId)
                .queryParam("isOpened", isOpened);

        RequestUtils.put(uriBuilder, null);
    }

}
