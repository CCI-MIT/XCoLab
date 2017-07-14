package org.xcolab.view.activityentry.proposal;


import org.springframework.stereotype.Component;

@Component
public class ProposalMemberRemovedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_MEMBER_REMOVED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.memberremoved.message";
    }

    @Override
    public String getTitleTemplate() {
        return "Team member removed";
    }

}
