package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.proposals.enums.ProposalJudgeType;
import org.xcolab.util.http.client.RestResource1;

import java.util.List;

public final class ProposalJudgeRatingClient {

    private final RestResource1<ProposalRatingWrapper, Long> proposalRatingResource = null; // proposalRatings
    private final RestResource1<IProposalRatingValue, Long> proposalRatingValueResource = null; // proposalRatingValues
    private final RestResource1<IProposalRatingType, Long> proposalRatingTypeResource = null; // proposalRatingTypes

    public List<ProposalRatingWrapper> getProposalRatingsByProposalUserContestPhase(Long proposalId,
            Long contestPhaseId, Long userId) {
        return proposalRatingResource.list()
                //.withCache(CacheKeys.withClass(ProposalRating.class)
                //                .withParameter("proposalId", proposalId)
                //                .withParameter("contestPhaseId", contestPhaseId)
                //                .withParameter("userId", userId).asList(),
                //        CacheRetention.MISC_MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public List<ProposalRatingWrapper> getFellowRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.FELLOW.getId());
    }


    public  List<IProposalRatingType> getRatingTypesForJudges() {
        return this.getRatingTypesForJudgeType(ProposalJudgeType.JUDGE.getId());
    }

    public List<IProposalRatingType> getRatingTypesForFellows() {
            return this.getRatingTypesForJudgeType(ProposalJudgeType.FELLOW.getId());
    }

    private List<IProposalRatingType> getRatingTypesForJudgeType(int judgeType) {
        return proposalRatingTypeResource.list()
                .queryParam("judgeType", judgeType)
                .queryParam("active", true)
                .execute();
    }

    protected List<ProposalRatingWrapper> getRatingsForProposal(long proposalId, long contestPhaseId,
            int judgeType) {

        return proposalRatingResource
                .collectionService("findByProposalIdJudgeTypeJudgeIdContestPhaseId",
                        ProposalRatingWrapper.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("contestPhaseId", contestPhaseId)
                .getList();
    }

    public List<ProposalRatingWrapper> getJudgeRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.JUDGE.getId());
    }

    public List<ProposalRatingWrapper> getJudgeRatingsForProposalAndUser(long userId, long proposalId,
            long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.JUDGE.getId(), userId,
                contestPhaseId);
    }

    protected List<ProposalRatingWrapper> getRatingsForProposalAndUser(long proposalId, int judgeType,
            long userId, long contestPhaseId) {
        return proposalRatingResource
                .collectionService("findByProposalIdJudgeTypeJudgeIdContestPhaseId",
                        ProposalRatingWrapper.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .getList();

    }

    public List<ProposalRatingWrapper> getFellowRatingForProposalAndUser(long userId, long proposalId,
            long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.FELLOW.getId(), userId,
                contestPhaseId);
    }

    public ProposalRatingWrapper createProposalRating(ProposalRatingWrapper proposalRating) {
        return proposalRatingResource
                .create(new ProposalRatingWrapper(proposalRating))
                .execute();
    }

    public boolean updateProposalRating(ProposalRatingWrapper proposalRating) {
        return proposalRatingResource
                .update(new ProposalRatingWrapper(proposalRating), proposalRating.getId())
                .execute();
    }

    public IProposalRatingValue getProposalRatingValue(long id) {
        return proposalRatingValueResource.get(id)
                .execute();
    }

    public List<IProposalRatingValue> getProposalRatingValuesByProposalRatingTypeId(Long ratingTypeId) {
        return proposalRatingValueResource.list()
                .optionalQueryParam("ratingTypeId", ratingTypeId)
                .execute();
    }

    public IProposalRatingType getProposalRatingType(long id) {
        return proposalRatingTypeResource.get(id)
                .execute();
    }
}
