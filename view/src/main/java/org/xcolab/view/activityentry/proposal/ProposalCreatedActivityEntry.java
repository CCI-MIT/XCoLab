package org.xcolab.view.activityentry.proposal;

import org.springframework.stereotype.Component;

@Component
public class ProposalCreatedActivityEntry extends ProposalBaseActivityEntry {


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
