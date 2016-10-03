package org.xcolab.service.activities.activityEntry.proposal;

public class ProposalVoteSwitchActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_VOTE_SWITCH.getSecondaryTypeId();
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
