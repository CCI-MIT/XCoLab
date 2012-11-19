package org.xcolab.core.contests;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.proposals.Proposal;

/**
 * User: jintrone
 * Date: 11/19/12
 * Time: 2:30 PM
 */
public class CreationContestPhaseImpl extends ContestPhaseImpl implements CreationPhase {


    public CreationContestPhaseImpl(DocumentEntity entity) {
        super(entity);
    }

    public Proposal createProposal() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
