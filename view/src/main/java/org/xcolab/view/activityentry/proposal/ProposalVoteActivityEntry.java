package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalVoteActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalVoteActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.VOTE_ADDED;
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.vote.message";
    }

    @Override
    public String getTitleTemplate() {
        return "<proposal/> vote";
    }
}
