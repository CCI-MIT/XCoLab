package org.xcolab.core.contests;

import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.proposals.Proposal;

public interface ContestPhase extends DocumentEntityWrapper {

    void addProposal(Proposal proposal);

    void removeProposal(Proposal proposal) throws DocumentEntityException;
	
	Proposal[] getProposals() throws DocumentEntityException;

}
