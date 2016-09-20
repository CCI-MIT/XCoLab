package org.xcolab.client.activities.activityEntry.proposal;

public class ProposalSupporterRemovedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_SUPPORTER_REMOVED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "retracted support for <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "New <proposal/> supporter";
    }
}
