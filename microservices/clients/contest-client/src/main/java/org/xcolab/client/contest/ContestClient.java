package org.xcolab.client.contest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

public class ContestClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/contest-service";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static Contest getContest(String contestUrlName, Long contestYear)
            throws ContestNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contests")
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear);
        ResponseEntity<List<Contest>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Contest>>() {
                });

        final List<Contest> contests = response.getBody();
        if (contests.isEmpty()) {
            throw new ContestNotFoundException("Contest " + contestUrlName
                    + " not found in year " + contestYear);
        }
        return contests.get(0);
    }
}
