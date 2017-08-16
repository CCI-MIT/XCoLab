package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalSupporterRemovedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalSupporterRemovedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_SUPPORTER_REMOVED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.supporterremoved.message";
    }

    @Override
    public String getTitleTemplate() {
        return "New <proposal/> supporter";
    }
}
