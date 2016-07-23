package org.xcolab.client.contest;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class ContestClient {

    private static final RestService contestService = new RestService("contest-service");
    private static final RestResource<Contest> contestResource = new RestResource<>(contestService,
            "contests", Contest.TYPES);

    public static Contest getContest(long contestId) throws ContestNotFoundException {
        try {
            return contestResource.get(contestId)
                    .cacheIdentifier("contestId_" + contestId)
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new ContestNotFoundException(contestId);
        }
    }

    public static Contest getContest(String contestUrlName, long contestYear)
            throws ContestNotFoundException {
        //TODO: port to new methods
        final UriBuilder uriBuilder = contestResource.getResourceUrl()
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
    public static List<Contest> findByActiveFeatured(Boolean active, Boolean featured) {
        return contestResource.list()
                .optionalQueryParam("active", active)
                .optionalQueryParam("featured",featured)
                .execute();
    }
}
