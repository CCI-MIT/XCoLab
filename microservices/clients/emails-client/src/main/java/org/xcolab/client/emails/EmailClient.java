package org.xcolab.client.emails;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.emails.pojo.Email;

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

        Email email = new Email();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setEmailBody(emailBody);
        email.setHtml(isHtml);
        email.setReplyTo(((replyTo == null ? ("") : (replyTo))));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Email> entity = new HttpEntity(email, headers);

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.POST, entity,
                String.class);
        response.getBody();
    }

}
