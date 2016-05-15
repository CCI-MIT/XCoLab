package org.xcolab.activityEntry.proposal;

public class ProposalMemberAddedActivityEntry extends ProposalBaseActivityEntry{


    @Override
    public Integer getSecondaryType() {
        return 6;
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
