package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

public abstract class BaseProposalUserActivityEvent {
    private final Proposal proposal;
    private final User user;
    
    
    public BaseProposalUserActivityEvent(Proposal proposal, User user) {
        super();
        this.proposal = proposal;
        this.user = user;
    }
    
    public Proposal getProposal() {
        return proposal;
    }
    public User getUser() {
        return user;
    }
    
    
    

}
