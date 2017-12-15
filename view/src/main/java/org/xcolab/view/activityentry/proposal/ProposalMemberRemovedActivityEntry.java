package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.activities.enums.enums.ActivityType;
import org.xcolab.util.activities.enums.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalMemberRemovedActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalMemberRemovedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.MEMBER_REMOVED;
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
