package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

public class ProposalVotedOnEvent extends BaseProposalUserActivityEvent {
    private final boolean switched;

    public ProposalVotedOnEvent(Proposal proposal, User user, boolean switched) {
        super(proposal, user);
        this.switched = switched;
    }

    public boolean isSwitched() {
        return switched;
    }

}
