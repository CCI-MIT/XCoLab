package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

import java.util.Date;

public abstract class BaseProposalUserActivityEvent {
    private final Proposal proposal;
    private final User user;
    private Date updatedDate;
    
    
    public BaseProposalUserActivityEvent(Proposal proposal, User user) {
        super();
        this.proposal = proposal;
        this.user = user;
        this.updatedDate = new Date();
    }
    
    public Proposal getProposal() {
        return proposal;
    }
    public User getUser() {
        return user;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    

}
