package org.xcolab.service.activities.activityentry.proposal;


public class ProposalMemberRemovedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_MEMBER_REMOVED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "is no longer a team member of <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "Team member removed";
    }

}
