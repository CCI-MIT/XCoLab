package org.xcolab.client.contest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class ContestClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:"+RequestUtils.getServicesPort()+"/contest-service";

    public static Contest getContest(long contestId) throws ContestNotFoundException {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://"
                    + EUREKA_APPLICATION_ID + "/contests/" + contestId);
            return RequestUtils.get(uriBuilder, Contest.class, "contestId_" + contestId);
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public static Contest getContest(String contestUrlName, long contestYear)
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
            throw new ContestNotFoundException(contestUrlName, contestYear);
        }
    }
}
