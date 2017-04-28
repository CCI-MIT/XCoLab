package org.xcolab.service.activities.activityentry.proposal;

public class ProposalSupporterAddedActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_SUPPORTER_ADDED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "is supporting <proposal/>";
    }

    @Override
    public String getTitleTemplate() {
        return "New <proposal/> supporter";
    }
}

