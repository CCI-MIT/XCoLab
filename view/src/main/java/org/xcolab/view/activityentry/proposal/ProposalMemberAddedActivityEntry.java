package org.xcolab.view.activityentry.proposal;

import org.springframework.stereotype.Component;

@Component
public class ProposalMemberAddedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_MEMBER_ADDED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.memberadded.message";
    }

    @Override
    public String getTitleTemplate() {
        return "New team member";
    }

}
