package org.xcolab.client.contest;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

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
        final Contest contest = contestResource.list()
                .queryParam("contestUrlName", contestUrlName)
                .queryParam("contestYear", contestYear)
                .executeWithResult().getFirstIfExists();
        if (contest == null) {
            throw new ContestNotFoundException(contestUrlName, contestYear);
        }
        return contest;
    }
}
