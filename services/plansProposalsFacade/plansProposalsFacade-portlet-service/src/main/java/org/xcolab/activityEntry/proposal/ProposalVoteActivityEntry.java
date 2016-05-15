package org.xcolab.activityEntry.proposal;

public class ProposalVoteActivityEntry  extends ProposalBaseActivityEntry {


    @Override
    public Integer getSecondaryType() {
        return 3;
    }

    @Override
    public String getBodyTemplate() {
        return "voted for <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> vote";
    }
}
