package org.xcolab.proposals.events;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;

public class ProposalAttributeUpdatedEvent {
    private final Proposal proposal;
    private final String attributeName;
    private final ProposalAttribute oldAttribute;
    private final ProposalAttribute newAttribute;
    
    public ProposalAttributeUpdatedEvent(Proposal proposal, String attributeName, ProposalAttribute oldAttribute,
            ProposalAttribute newAttribute) {
        super();
        this.proposal = proposal;
        this.attributeName = attributeName;
        this.oldAttribute = oldAttribute;
        this.newAttribute = newAttribute;
    }
    
    public Proposal getProposal() {
        return proposal;
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
