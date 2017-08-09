package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalVoteSwitchActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalVoteSwitchActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_VOTE_SWITCH.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.voteswitch.message";
    }

    @Override
    public String getTitleTemplate() {
        return "Vote switched";
    }
}
