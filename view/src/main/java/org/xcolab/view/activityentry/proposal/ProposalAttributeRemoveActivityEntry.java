package org.xcolab.view.activityentry.proposal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalAttributeRemoveActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalAttributeRemoveActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

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
