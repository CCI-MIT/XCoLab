package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalVoteSwitchActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalVoteSwitchActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.VOTE_SWITCHED;
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.voteswitch.message";
    }

    @Override
    public String getTitleTemplate() {
        return "Vote switched";
    }
}
