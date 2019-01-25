package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ProposalJudgeRatingClientMockImpl implements IProposalJudgeRatingClient {

    @Override
    public List<ProposalRatingWrapper> getProposalRatingsByProposalUserContestPhase(Long proposalId,
            Long contestPhaseId, Long userId) {
        return Collections.emptyList();
    }

    @Override
    public List<IProposalRatingType> getRatingTypesForJudgeType(Integer judgeType) {
        return Collections.emptyList();
    }

    @Override
    public List<ProposalRatingWrapper> getRatingsForProposalAndUser(Long proposalId,
            Integer judgeType, Long userId, Long contestPhaseId) {
        return Collections.emptyList();
    }

    @Override
    public ProposalRatingWrapper createProposalRating(ProposalRatingWrapper proposalRating) {
        return null;
    }

    @Override
    public boolean updateProposalRating(ProposalRatingWrapper proposalRating) {
        return false;
    }

    @Override
    public IProposalRatingValue getProposalRatingValue(Long proposalRatingValueId) {
        return null;
    }

    @Override
    public List<IProposalRatingValue> getProposalRatingValuesByProposalRatingTypeId(
            Long ratingTypeId) {
        return Collections.emptyList();
    }

    @Override
    public IProposalRatingType getProposalRatingType(Long proposalRatingTypeId) {
        return null;
    }
}
