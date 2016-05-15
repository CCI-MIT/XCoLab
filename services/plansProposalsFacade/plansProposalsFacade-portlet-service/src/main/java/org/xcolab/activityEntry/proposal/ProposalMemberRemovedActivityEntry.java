package org.xcolab.activityEntry.proposal;


public class ProposalMemberRemovedActivityEntry extends ProposalBaseActivityEntry{


    @Override
    public Integer getSecondaryType() {
        return 7;
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
