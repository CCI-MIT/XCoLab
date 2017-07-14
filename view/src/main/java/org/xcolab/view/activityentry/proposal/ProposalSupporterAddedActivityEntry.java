package org.xcolab.view.activityentry.proposal;

import org.springframework.stereotype.Component;

@Component
public class ProposalSupporterAddedActivityEntry extends ProposalBaseActivityEntry {


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

