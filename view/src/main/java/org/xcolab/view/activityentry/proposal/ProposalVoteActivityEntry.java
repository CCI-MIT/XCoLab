package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.i18n.ResourceMessageResolver;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProposalVoteActivityEntry extends ProposalBaseActivityEntry {

    private IProposalVote proposalVote;

    @Autowired
    public ProposalVoteActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.VOTE_ADDED;
    }

    @Override
    public void initializeInternal() throws ActivityInitializationException {
        super.initializeInternal();
        long proposalId = getActivityEntry().getCategoryId();
        long userId = getActivityEntry().getUserId();
        final List<IProposalVote> proposalVotes =
                StaticProposalContext.getProposalMemberRatingClient()
                        .getProposalVotes(null, proposalId, userId);
        //TODO COLAB-2657: we should store the actual vote ID in additionalId
        proposalVote = proposalVotes.size() == 1 ? proposalVotes.get(0) : null;
    }

    @Override
    public List<Object> getBodyTemplateParams() {
        final ArrayList<Object> params = new ArrayList<>(super.getBodyTemplateParams());
        if (proposalVote != null) {
            params.add(proposalVote.getValue());
        }
        return params;
    }

    @Override
    public String getBodyTemplate() {
        boolean allowsVoteValue = ConfigurationAttributeKey
                .PROPOSALS_MAX_VOTES_PER_PROPOSAL.get() > 1;

        if (proposalVote != null && allowsVoteValue) {
            return "activities.proposal.vote.messageWithCount";
        }
        return "activities.proposal.vote.message";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> vote";
    }
}
