package org.xcolab.view.activityentry.proposal;


import org.springframework.stereotype.Component;

@Component
public class ProposalAttributeRemoveActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_ATTRIBUTE_REMOVED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.attributeremove.message";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> updated";
    }
}
