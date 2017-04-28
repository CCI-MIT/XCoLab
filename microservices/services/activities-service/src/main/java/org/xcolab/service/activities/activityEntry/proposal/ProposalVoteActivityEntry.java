package org.xcolab.service.activities.activityentry.proposal;

public class ProposalVoteActivityEntry  extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_VOTE.getSecondaryTypeId();
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
