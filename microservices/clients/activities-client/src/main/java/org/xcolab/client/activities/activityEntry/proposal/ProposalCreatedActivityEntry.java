package org.xcolab.client.activities.activityEntry.proposal;

public class ProposalCreatedActivityEntry extends ProposalBaseActivityEntry{


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_CREATED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return " created <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "Created <proposal/>";
    }

}
