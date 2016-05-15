package org.xcolab.activityEntry.proposal;

public class ProposalVoteSwitchActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Integer getSecondaryType() {
        return 5;
    }

    @Override
    public String getBodyTemplate() {
        return "voted for <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "Vote switched";
    }
}
