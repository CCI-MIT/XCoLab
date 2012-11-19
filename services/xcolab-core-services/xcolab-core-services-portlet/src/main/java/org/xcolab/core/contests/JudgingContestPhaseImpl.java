package org.xcolab.core.contests;

import org.xcolab.core.documententity.DocumentEntity;
import org.xcolab.core.proposals.Proposal;

import java.util.Calendar;

/**
 * User: jintrone
 * Date: 11/19/12
 * Time: 2:20 PM
 */
public class JudgingContestPhaseImpl extends ContestPhaseImpl implements JudgingPhase {

    public JudgingContestPhaseImpl(DocumentEntity entity) {
        super(entity);
    }

    public void addProposal(Proposal proposal, Calendar version) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

     public ContestPhaseType getPhaseType() {
        return ContestPhaseType.JUDGE;
    }
}
