package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

public class ProposalMemberAddedEvent extends BaseProposalUserActivityEvent {

    public ProposalMemberAddedEvent(Proposal proposal, User user) {
        super(proposal, user);
    }

}
