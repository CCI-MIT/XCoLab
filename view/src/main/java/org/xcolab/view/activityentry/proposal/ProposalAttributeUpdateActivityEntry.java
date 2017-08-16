package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalAttributeUpdateActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalAttributeUpdateActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

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
