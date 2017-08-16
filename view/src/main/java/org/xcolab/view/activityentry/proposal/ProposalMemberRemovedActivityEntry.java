package org.xcolab.view.activityentry.proposal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalMemberRemovedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalMemberRemovedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return ProposalActivitySubType.PROPOSAL_MEMBER_REMOVED.getSecondaryTypeId();
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.memberremoved.message";
    }

    @Override
    public String getTitleTemplate() {
        return "Team member removed";
    }

}
