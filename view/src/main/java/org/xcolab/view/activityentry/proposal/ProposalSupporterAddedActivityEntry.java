package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalSupporterAddedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalSupporterAddedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_SUPPORTER_ADDED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.supporteradded.message";
    }

    @Override
    public String getTitleTemplate() {
        return "New <proposal/> supporter";
    }
}

