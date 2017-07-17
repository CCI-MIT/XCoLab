package org.xcolab.view.activityentry.proposal;

import org.springframework.stereotype.Component;

@Component
public class ProposalAttributeUpdateActivityEntry extends ProposalBaseActivityEntry {


    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_ATTRIBUTE_UPDATE.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.attributeupdated.message";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> updated";
    }
}
