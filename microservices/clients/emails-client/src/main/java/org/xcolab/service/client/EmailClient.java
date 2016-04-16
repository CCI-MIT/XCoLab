package org.xcolab.service.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public final class EmailClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/emails-service";

    private static RestTemplate restTemplate = new RestTemplate();


    private EmailClient() {
    }

    public static void sendEmail(String from, String to, String subject, String emailBody, Boolean isHtml, String replyTo) {

        List<String> toAdd = new ArrayList<>();
        toAdd.add(to);
        sendEmail(from, toAdd, subject, emailBody, isHtml, replyTo);
    }

    public static void sendEmail(String from, List<String> to, String subject, String emailBody, Boolean isHtml, String replyTo) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/sendEmail");
        uriBuilder.queryParam("from", from);
        for (String emailTo : to) {
            uriBuilder.queryParam("to[]", emailTo);
        }
        uriBuilder.queryParam("subject", subject);
        uriBuilder.queryParam("emailBody", emailBody);
        uriBuilder.queryParam("isHtml", isHtml);
        uriBuilder.queryParam("replyTo", ((replyTo == null ? ("") : (replyTo))));


        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, null,
                String.class);


        response.getBody();
    }

}
