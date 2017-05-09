package org.xcolab.service.activities.activityEntry.proposal;

public class ProposalAttributeRemoveActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_ATTRIBUTE_REMOVED.getSecondaryTypeId();
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
