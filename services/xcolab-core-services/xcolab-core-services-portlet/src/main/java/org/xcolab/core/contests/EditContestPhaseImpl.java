package org.xcolab.core.contests;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.proposals.Proposal;

/**
 * User: jintrone
 * Date: 11/19/12
 * Time: 2:26 PM
 */
public class EditContestPhaseImpl extends ContestPhaseImpl implements EditPhase {


    public EditContestPhaseImpl(DocumentEntity entity) {
        super(entity);
    }

    public void addProposal(Proposal proposal) {
	    addReference(PROPOSALS_PROPERTY, proposal);
	}
}
