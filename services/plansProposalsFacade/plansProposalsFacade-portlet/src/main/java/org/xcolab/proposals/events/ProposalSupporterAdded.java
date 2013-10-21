package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

public class ProposalSupporterAdded extends BaseProposalUserActivityEvent {
    
    public ProposalSupporterAdded(Proposal proposal, User user) {
        super(proposal, user);
    }

}
