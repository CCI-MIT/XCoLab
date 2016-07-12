package org.xcolab.client.contest;

import org.springframework.core.ParameterizedTypeReference;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class ContestPhaseClient {

    private static final RestService contestPhaseService = new RestService("contest-service");
    private static final RestResource<Contest> contestPhaseResource = new RestResource<>(contestPhaseService,
            "contestPhases", Contest.class, new ParameterizedTypeReference<List<Contest>>() {
    });
}
