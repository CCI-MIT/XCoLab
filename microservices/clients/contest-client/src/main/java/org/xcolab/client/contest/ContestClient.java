package org.xcolab.client.contest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;

public class ContestClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:"+RequestUtils.getServicesPort()+"/contest-service";

    public static Contest getContest(String contestUrlName, Long contestYear)
            throws ContestNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contests")
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear);
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<Contest>>() {
                    });
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException("Contest " + contestUrlName
                    + " not found in year " + contestYear);
        }
    }
}
