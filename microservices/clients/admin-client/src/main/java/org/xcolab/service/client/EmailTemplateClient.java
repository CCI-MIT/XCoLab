package org.xcolab.service.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.pojo.ContestEmailTemplate;

import java.util.List;

public class EmailTemplateClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/admin-service";

    static RestTemplate restTemplate = new RestTemplate();

    public static List<ContestEmailTemplate> listAllContestEmailTemplates() {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/emailTemplates");


        ResponseEntity<List<ContestEmailTemplate>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ContestEmailTemplate>>() {
                });

        return response.getBody();
    }

        public static ContestEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/emailTemplates/" + emailTemplateType + "");
        return restTemplate.getForObject(uriBuilder.build().toString(), ContestEmailTemplate.class);
    }
}
