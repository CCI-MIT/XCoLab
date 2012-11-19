package org.xcolab.core.contests;

import java.util.Calendar;
import java.util.List;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.impl.BaseDocumentEntityWrapper;
import org.xcolab.core.proposals.Proposal;
import org.xcolab.core.proposals.ProposalImpl;
import org.xcolab.core.proposals.template.ProposalTemplate;

public class ContestPhaseImpl extends BaseDocumentEntityWrapper implements ContestPhase {
	final static String PROPOSALS_PROPERTY = "proposals";

	public ContestPhaseImpl(DocumentEntity entity) {
		super(entity);
	}

	public Proposal[] getProposals() throws DocumentEntityException {
	    return getChildren(PROPOSALS_PROPERTY, Proposal.class, ProposalImpl.class);
	}



    public Calendar[] getActiveDates() {
        return new Calendar[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setActiveDates(Calendar[] dates) throws ContestPhaseTimingException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public ContestPhaseType getPhaseType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Contest getContest() {
        return null;
    }

    public String toString() {
		return "ContestPhaseImpl[" + getWrapped() + "]";
	}


	public void removeProposal(Proposal proposal) throws DocumentEntityException {
	    Proposal[] currentProposals = getChildren(PROPOSALS_PROPERTY, Proposal.class, ProposalImpl.class);
	    boolean found = false;
	    Proposal[] newProposals = new Proposal[currentProposals.length-1];
	    for (int i = 0, j = 0; j < newProposals.length; i++) {
	        if (currentProposals[i].getId().equals(proposal.getId())) {
	            found = true;
	        }
	        else {
	            newProposals[j++] = currentProposals[i];
	        }
	    }
	    found = found || currentProposals[currentProposals.length-1].getId().equals(proposal.getId());
	    if (found) {
	        setReferences(PROPOSALS_PROPERTY, newProposals);
	    }
	}



}
