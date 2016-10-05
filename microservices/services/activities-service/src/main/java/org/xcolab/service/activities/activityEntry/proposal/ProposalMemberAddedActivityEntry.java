package org.xcolab.service.activities.activityEntry.proposal;

public class ProposalMemberAddedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_MEMBER_ADDED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "became a team member of <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "New team member";
    }

}
