package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.activities.enums.enums.ActivityType;
import org.xcolab.util.activities.enums.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalSupporterRemovedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalSupporterRemovedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.SUPPORT_REMOVED;
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
