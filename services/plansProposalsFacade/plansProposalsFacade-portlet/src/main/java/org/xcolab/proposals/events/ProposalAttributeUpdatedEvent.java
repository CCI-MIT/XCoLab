package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.liferay.portal.model.User;

public class ProposalAttributeUpdatedEvent  extends BaseProposalUserActivityEvent {
    private final String attributeName;
    private final ProposalAttribute oldAttribute;
    private final ProposalAttribute newAttribute;
    
    public ProposalAttributeUpdatedEvent(Proposal proposal, User user, String attributeName, ProposalAttribute oldAttribute,
            ProposalAttribute newAttribute) {
        super(proposal, user);
        this.attributeName = attributeName;
        this.oldAttribute = oldAttribute;
        this.newAttribute = newAttribute;
        setUpdatedDate(proposal.getUpdatedDate());
    }
    
    public String getAttributeName() {
        return attributeName;
    }
    public ProposalAttribute getOldAttribute() {
        return oldAttribute;
    }
    public ProposalAttribute getNewAttribute() {
        return newAttribute;
    }
    
    

}
