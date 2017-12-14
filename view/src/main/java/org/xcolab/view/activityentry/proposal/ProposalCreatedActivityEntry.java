package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.ContestActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalCreatedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalCreatedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ContestActivityType.PROPOSAL_CREATED;
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
