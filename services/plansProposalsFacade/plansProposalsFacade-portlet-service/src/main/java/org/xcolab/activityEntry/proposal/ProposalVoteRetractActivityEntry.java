package org.xcolab.activityEntry.proposal;

public class ProposalVoteRetractActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Integer getSecondaryType() {
        return 4;
    }

    @Override
    public String getBodyTemplate() {
        return "retracted vote for <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "Vote retracted";
    }
}
