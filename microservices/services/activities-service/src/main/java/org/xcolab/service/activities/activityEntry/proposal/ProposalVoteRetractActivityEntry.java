package org.xcolab.service.activities.activityEntry.proposal;

public class ProposalVoteRetractActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_VOTE_RETRACT.getSecondaryTypeId();
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
