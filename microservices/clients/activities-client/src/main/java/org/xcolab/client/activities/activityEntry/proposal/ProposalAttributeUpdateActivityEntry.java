package org.xcolab.client.activities.activityEntry.proposal;

public class ProposalAttributeUpdateActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_ATTRIBUTE_UPDATE.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "updated <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> updated";
    }
}
