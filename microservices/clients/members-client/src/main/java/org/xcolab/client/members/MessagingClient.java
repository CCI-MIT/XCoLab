package org.xcolab.client.members;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;

import java.util.List;

public final class MessagingClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/members-service";

    private static final RestTemplate restTemplate = new RestTemplate();

    private MessagingClient() { }

    public static Message getMessage(long messageId) throws MessageNotFoundException {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId);
        try {
            return restTemplate.getForObject(uriBuilder.build().toString(), Message.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new MessageNotFoundException(messageId);
            }
            throw e;
        }
    }

    public static List<Message> getMessagesForUser(int firstMessage, int lastMessage, long userId, boolean isArchived) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("recipientId", userId)
                        .queryParam("firstRecord", firstMessage)
                        .queryParam("lastRecord", lastMessage);

        uriBuilder.queryParam("isArchived", isArchived);

        ResponseEntity<List<Message>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {
                });

        return response.getBody();
    }

    public static List<Message> getSentMessagesForUser(int firstMessage, int lastMessage, long userId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("senderId", userId)
                        .queryParam("firstRecord", firstMessage)
                        .queryParam("lastRecord", lastMessage);

        ResponseEntity<List<Message>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {
                });

        return response.getBody();
    }

    public static int getMessageCountForUser(long userId, boolean isArchived) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("recipientId", userId)
                        .queryParam("isArchived", isArchived);
        final HttpHeaders httpHeaders = restTemplate.headForHeaders(uriBuilder.build().toString());
        final List<String> countHeaders = httpHeaders.get("X-Total-Count");
        if (countHeaders.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(countHeaders.get(0));
    }

    public static int getUnreadMessageCountForUser(long userId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("recipientId", userId)
                        .queryParam("isOpened", false)
                        .queryParam("isArchived", false);
        final HttpHeaders httpHeaders = restTemplate.headForHeaders(uriBuilder.build().toString());
        final List<String> countHeaders = httpHeaders.get("X-Total-Count");
        if (countHeaders.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(countHeaders.get(0));
    }

    public static int getSentMessageCountForUser(long userId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages")
                        .queryParam("senderId", userId);
        final HttpHeaders httpHeaders = restTemplate.headForHeaders(uriBuilder.build().toString());
        final List<String> countHeaders = httpHeaders.get("X-Total-Count");
        if (countHeaders.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(countHeaders.get(0));
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

    public static List<Member> getMessageRecipients(long messageId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients");
        ResponseEntity<List<Member>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Member>>() {
                });
        return response.getBody();
    }

    public static void setArchived(long messageId, long memberId, boolean isArchived) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId)
                .queryParam("memberId", memberId)
                .queryParam("isArchived", isArchived);

        restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.PUT, null, String.class);
    }

    public static void setOpened(long messageId, long memberId, boolean isOpened) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId)
                .queryParam("memberId", memberId)
                .queryParam("isOpened", isOpened);

        restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.PUT, null, String.class);
    }

}
