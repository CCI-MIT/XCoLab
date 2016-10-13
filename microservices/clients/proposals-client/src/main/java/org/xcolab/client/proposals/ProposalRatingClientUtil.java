package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValue;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalRatingClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");

    private static final ProposalRatingClient client =
            ProposalRatingClient.fromService(proposalService);

    public static ProposalRatingClient getClient() {
        return client;
    }

    public static List<ProposalRating> getProposalRatingsByProposalUserContestPhase(
            Long proposalId, Long contestPhaseId, Long userId) {
        return client
                .getProposalRatingsByProposalUserContestPhase(proposalId, contestPhaseId, userId);
    }

    public static List<ProposalRating> getFellowRatingsForProposal(
            long proposalId, long contestPhaseId) {
        return client.getFellowRatingsForProposal(proposalId, contestPhaseId);
    }

    public static List<ProposalRating> getRatingsForProposal(
            long proposalId, long contestPhaseId, int judgeType) {
        return client.getRatingsForProposal(proposalId, contestPhaseId, judgeType);
    }

    public static List<ProposalRating> getJudgeRatingsForProposal(
            long proposalId, long contestPhaseId) {
        return client.getJudgeRatingsForProposal(proposalId, contestPhaseId);
    }

    public static List<ProposalRating> getJudgeRatingsForProposalAndUser(
            long userId, long proposalId, long contestPhaseId) {
        return client.getJudgeRatingsForProposalAndUser(userId, proposalId, contestPhaseId);
    }

    public static List<ProposalRating> getRatingsForProposalAndUser(
            long proposalId, int judgeType, long userId, long contestPhaseId) {
        return client.getRatingsForProposalAndUser(proposalId, judgeType, userId, contestPhaseId);
    }

    public static List<ProposalRating> getFellowRatingForProposalAndUser(
            long userId, long proposalId, long contestPhaseId) {
        return client.getFellowRatingForProposalAndUser(userId, proposalId, contestPhaseId);
    }

    public static ProposalRating createProposalRating(
            ProposalRating proposalRating) {
        return client.createProposalRating(proposalRating);
    }

    public static boolean updateProposalRating(ProposalRating proposalRating) {
        return client.updateProposalRating(proposalRating);
    }

    public static ProposalRatingValue getProposalRatingValue(long id) {
        return client.getProposalRatingValue(id);
    }

    public static ProposalRatingType getProposalRatingType(long id) {
        return client.getProposalRatingType(id);
    }
}
