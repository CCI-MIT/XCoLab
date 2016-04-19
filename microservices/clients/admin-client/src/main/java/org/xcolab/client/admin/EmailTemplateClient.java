package org.xcolab.client.admin;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;

import java.util.List;

public class EmailTemplateClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/admin-service";

    private static final RestTemplate restTemplate = new RestTemplate();

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

    public static void updateContestEmailTemplate(ContestEmailTemplate contestEmailTemplate) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/emailTemplates/" + contestEmailTemplate.getType_() + "");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(contestEmailTemplate, headers);


        ResponseEntity<String> out = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.POST, entity,
                String.class);
    }

}
