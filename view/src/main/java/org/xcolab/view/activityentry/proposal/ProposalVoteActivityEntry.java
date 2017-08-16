package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalVoteActivityEntry  extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalVoteActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_VOTE.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.vote.message";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> vote";
    }
}
