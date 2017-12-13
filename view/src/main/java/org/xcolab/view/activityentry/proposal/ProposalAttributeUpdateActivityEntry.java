package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalAttributeUpdateActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalAttributeUpdateActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.UPDATED;
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
