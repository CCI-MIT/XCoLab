package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.commons.activities.enums.ActivityType;
import org.xcolab.commons.activities.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalMemberAddedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalMemberAddedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.MEMBER_ADDED;
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.memberadded.message";
    }

    @Override
    public String getTitleTemplate() {
        return "New team member";
    }

}
