package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

public class ProposalSupporterAddedEvent extends BaseProposalUserActivityEvent {
    
    public ProposalSupporterAddedEvent(Proposal proposal, User user) {
        super(proposal, user);
    }

}
