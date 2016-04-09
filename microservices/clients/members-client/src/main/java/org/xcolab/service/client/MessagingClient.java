package org.xcolab.service.client;

import org.omg.CORBA.SystemException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.pojo.Message;
import org.xcolab.pojo.User_;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MessagingClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/members-service";

    private static final RestTemplate restTemplate = new RestTemplate();

    private MessagingClient() { }

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

    public static boolean createMessage(Message message) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages");

        final ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(uriBuilder.build().toString(), message, String.class);
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

    public static boolean createRecipient(long messageId, long recipientStatusId, long recipientId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients");

        Map<String, Long> uriVars = new HashMap<>();
        uriVars.put("recipientStatusId", recipientStatusId);
        uriVars.put("recipientId", recipientId);

        final ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(uriBuilder.build().toString(), null, String.class, uriVars);
        return responseEntity.getStatusCode().is2xxSuccessful();
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
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/messages/" + messageId + "/recipients");
        Map<String, Object> uriVars = new HashMap<>();
        uriVars.put("memberId", memberId);
        uriVars.put("isArchived", isArchived);

        restTemplate.exchange(uriBuilder.build().toString(), HttpMethod.PATCH, null, String.class, uriVars);
    }

}
