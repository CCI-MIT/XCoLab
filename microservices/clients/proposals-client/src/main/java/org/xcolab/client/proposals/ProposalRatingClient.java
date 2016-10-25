package org.xcolab.client.proposals;

import org.xcolab.client.proposals.enums.ProposalJudgeType;
import org.xcolab.client.proposals.pojo.ProposalRating;
import org.xcolab.client.proposals.pojo.ProposalRatingType;
import org.xcolab.client.proposals.pojo.ProposalRatingValue;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalRatingClient {

    private static final RestService proposalService = new RestService("proposals-service");

    private static final RestResource1<ProposalRating, Long> proposalRatingResource = new RestResource1<>(proposalService,
            "proposalRatings", ProposalRating.TYPES);

    private static final RestResource1<ProposalRatingValue, Long> proposalRatingValueResource = new RestResource1<>(proposalService,
            "proposalRatingValues", ProposalRatingValue.TYPES);

    private static final RestResource1<ProposalRatingType, Long> proposalRatingTypeResource = new RestResource1<>(proposalService,
            "proposalRatingTypes", ProposalRatingType.TYPES);


    public static List<ProposalRating> getProposalRatingsByProposalUserContestPhase(Long proposalId, Long contestPhaseId, Long userId) {
        return proposalRatingResource.list()
                //.withCache(CacheKeys.withClass(ProposalRating.class)
                 //               .withParameter("proposalId", proposalId)
                 //               .withParameter("contestPhaseId", contestPhaseId)
                 //               .withParameter("userId", userId).asList(),
                 //       CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public static List<ProposalRating> getFellowRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.FELLOW.getId());
    }

    public static List<ProposalRating> getJudgeRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.JUDGE.getId());
    }

    protected static List<ProposalRating> getRatingsForProposal(long proposalId, long contestPhaseId, int judgeType) {

        return proposalRatingResource.service("findByProposalIdJudgeTypeJudgeIdContestPhaseId", ProposalRating.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("contestPhaseId", contestPhaseId).getList();

    }

    public static List<ProposalRating> getJudgeRatingsForProposalAndUser(long userId, long proposalId, long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.JUDGE.getId(), userId, contestPhaseId);
    }

    public static List<ProposalRating> getFellowRatingForProposalAndUser(long userId, long proposalId, long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.FELLOW.getId(), userId, contestPhaseId);
    }

    protected static List<ProposalRating> getRatingsForProposalAndUser(long proposalId, int judgeType, long userId, long contestPhaseId) {
        return proposalRatingResource.service("findByProposalIdJudgeTypeJudgeIdContestPhaseId", ProposalRating.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId).getList();

    }

    public static ProposalRating createProposalRating(ProposalRating proposalRating) {
        return proposalRatingResource.create(proposalRating).execute();
    }

    public static boolean updateProposalRating(ProposalRating proposalRating) {
        return proposalRatingResource.update(proposalRating, proposalRating.getId_())
                .execute();
    }

    public static ProposalRatingValue getProposalRatingValue(long id) {
        return proposalRatingValueResource.get(id)
                .execute();
    }

    public static ProposalRatingType getProposalRatingType(long id) {
        return proposalRatingTypeResource.get(id)
                .execute();

    }
}
