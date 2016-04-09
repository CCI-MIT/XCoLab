package org.xcolab.service.client;

import org.omg.CORBA.SystemException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.pojo.Message;
import org.xcolab.pojo.User_;

import java.util.List;

public final class MessagingClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/members-service";

    private static final RestTemplate restTemplate = new RestTemplate();

    private MessagingClient() { }

    public static Message getMessage(long messageId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId);
        return restTemplate.getForObject(uriBuilder.build().toString(), Message.class);
    }

    public static List<Message> getMessagesForUser(int firstMessage, int lastMessage, long userId, boolean isArchived) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/" + userId + "/messages/")
                        .queryParam("firstRecord", firstMessage)
                        .queryParam("lastRecord", lastMessage);

        uriBuilder.queryParam("isArchived", isArchived);

        ResponseEntity<List<Message>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {
                });

        return response.getBody();
    }

    public static List<Message> getSentMessagesForUser(int firstMessage, int lastMessage, long userId) throws SystemException {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/" + userId + "/messagesSent/")
                        .queryParam("firstRecord", firstMessage)
                        .queryParam("lastRecord", lastMessage);

        ResponseEntity<List<Message>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {
                });

        return response.getBody();
    }

    public static int getMessageCountForUser(long userId, boolean isArchived) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/" + userId + "/messages/count");
        if (isArchived) {
            uriBuilder.queryParam("isArchived", true);
        }
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static int getUnreadMessageCountForUser(long userId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/" + userId + "/messages/countUnread");
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static int getSentMessageCountForUser(long userId) throws SystemException {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/" + userId + "/messages/countSent");
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static void createMessage(Message message) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages");

        restTemplate.postForEntity(uriBuilder.build().toString(), message, String.class);
    }

    public static void createRecipient(long messageId, long recipientStatusId, long recipientId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients")
                .queryParam("recipientStatusId", recipientStatusId)
                .queryParam("recipientId", recipientId);

        restTemplate.postForEntity(uriBuilder.build().toString(), null, String.class);
    }

    public static List<User_> getMessageRecipients(long messageId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients");
        ResponseEntity<List<User_>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User_>>() {
                });
        return response.getBody();
    }

    public static void setArchived(long messageId, long memberId, boolean isArchived) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients")
                .queryParam("memberId", memberId)
                .queryParam("isArchived", isArchived);

        restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.PATCH, null, String.class);
    }

    public static void setOpened(long messageId, long memberId, boolean isOpened) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients")
                .queryParam("memberId", memberId)
                .queryParam("isOpened", isOpened);

        restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.PATCH, null, String.class);
    }

}
