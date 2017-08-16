package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalCreatedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalCreatedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_CREATED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.created.message";
    }

    @Override
    public String getTitleTemplate() {
        return "Created <proposal/>";
    }

}
