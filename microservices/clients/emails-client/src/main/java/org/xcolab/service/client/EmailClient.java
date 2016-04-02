package org.xcolab.service.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class EmailClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/email-service";

    static RestTemplate restTemplate = new RestTemplate();

    public static void sendEmail(String senderEmail, String subject, String emailBody, List<String>
            recipients) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/sendEmail");
        uriBuilder.queryParam("senderEmail", senderEmail);
        uriBuilder.queryParam("subject", subject);
        uriBuilder.queryParam("emailBody", emailBody);
        uriBuilder.queryParam("recipients", recipients);


        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, null, new ParameterizedTypeReference<String>() {
                });

        response.getBody();
    }

}
