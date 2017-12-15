package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.activities.enums.enums.ActivityType;
import org.xcolab.util.activities.enums.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalVoteRetractActivityEntry extends ProposalBaseActivityEntry {

    @Autowired
    public ProposalVoteRetractActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.VOTE_RETRACTED;
    }

    @Override
    public String getBodyTemplate() {
        return "activities.proposal.voteretract.message";
    }

    @Override
    public String getTitleTemplate() {
        return "Vote retracted";
    }
}
