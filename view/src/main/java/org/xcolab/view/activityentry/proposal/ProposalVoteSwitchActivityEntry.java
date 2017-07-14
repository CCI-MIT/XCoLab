package org.xcolab.view.activityentry.proposal;

import org.springframework.stereotype.Component;

@Component
public class ProposalVoteSwitchActivityEntry extends ProposalBaseActivityEntry {


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
