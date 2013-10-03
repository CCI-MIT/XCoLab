package org.xcolab.portlets.proposals.requests;

import javax.validation.constraints.NotNull;

public class SupportProposalActionBean {
    
    @NotNull
    private long proposalId;

    public SupportProposalActionBean(long proposalId) {
        this.proposalId = proposalId;
    }
    
    public SupportProposalActionBean() {
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }
    
    

}
