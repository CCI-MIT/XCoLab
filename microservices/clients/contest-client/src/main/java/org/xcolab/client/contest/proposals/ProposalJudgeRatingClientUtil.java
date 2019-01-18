package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;

import java.util.List;

public final class ProposalJudgeRatingClientUtil {

    private static final ProposalJudgeRatingClient client = new ProposalJudgeRatingClient();

    public static ProposalJudgeRatingClient getClient() {
        return client;
    }

    public static List<ProposalRatingWrapper> getProposalRatingsByProposalUserContestPhase(
            Long proposalId, Long contestPhaseId, Long userId) {
        return client
                .getProposalRatingsByProposalUserContestPhase(proposalId, contestPhaseId, userId);
    }

    public static List<ProposalRatingWrapper> getFellowRatingsForProposal(
            long proposalId, long contestPhaseId) {
        return client.getFellowRatingsForProposal(proposalId, contestPhaseId);
    }

    public static List<ProposalRatingWrapper> getRatingsForProposal(
            long proposalId, long contestPhaseId, int judgeType) {
        return client.getRatingsForProposal(proposalId, contestPhaseId, judgeType);
    }

    public static List<ProposalRatingWrapper> getJudgeRatingsForProposal(
            long proposalId, long contestPhaseId) {
        return client.getJudgeRatingsForProposal(proposalId, contestPhaseId);
    }

    public static List<ProposalRatingWrapper> getJudgeRatingsForProposalAndUser(
            long userId, long proposalId, long contestPhaseId) {
        return client.getJudgeRatingsForProposalAndUser(userId, proposalId, contestPhaseId);
    }

    public static List<ProposalRatingWrapper> getRatingsForProposalAndUser(
            long proposalId, int judgeType, long userId, long contestPhaseId) {
        return client.getRatingsForProposalAndUser(proposalId, judgeType, userId, contestPhaseId);
    }

    public static List<ProposalRatingWrapper> getFellowRatingForProposalAndUser(
            long userId, long proposalId, long contestPhaseId) {
        return client.getFellowRatingForProposalAndUser(userId, proposalId, contestPhaseId);
    }

    public static ProposalRatingWrapper createProposalRating(
            ProposalRatingWrapper proposalRating) {
        return client.createProposalRating(proposalRating);
    }

    public static boolean updateProposalRating(ProposalRatingWrapper proposalRating) {
        return client.updateProposalRating(proposalRating);
    }

    public static IProposalRatingValue getProposalRatingValue(long id) {
        return client.getProposalRatingValue(id);
    }
    public static List<IProposalRatingValue> getProposalRatingValuesByProposalRatingTypeId(Long ratingTypeId) {
        return client.getProposalRatingValuesByProposalRatingTypeId(ratingTypeId);
    }

    public static IProposalRatingType getProposalRatingType(long id) {
        return client.getProposalRatingType(id);
    }

    public static List<IProposalRatingType> getRatingTypesForJudges() {
        return client.getRatingTypesForJudges();
    }
    public static List<IProposalRatingType> getRatingTypesForFellows() {
        return client.getRatingTypesForFellows();
    }
}
