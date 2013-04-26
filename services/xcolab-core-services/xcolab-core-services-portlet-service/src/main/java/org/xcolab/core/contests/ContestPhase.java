package org.xcolab.core.contests;

import java.util.Calendar;

import org.xcolab.core.proposals.Proposal;
import org.xcolab.core.xcolabententity.XColabEntityException;
import org.xcolab.core.xcolabententity.XColabEntityWrapper;

public interface ContestPhase extends XColabEntityWrapper {

    void removeProposal(Proposal proposal) throws XColabEntityException;

  	Proposal[] getProposals() throws XColabEntityException;

    public Calendar[] getActiveDates();

    public void setActiveDates(Calendar[] dates) throws ContestPhaseTimingException;

    public ContestPhaseType getPhaseType();

    public Contest getContest();



}
