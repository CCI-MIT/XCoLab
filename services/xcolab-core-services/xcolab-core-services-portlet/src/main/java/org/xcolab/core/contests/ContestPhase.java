package org.xcolab.core.contests;

import org.xcolab.core.documententity.DocumentEntityException;
import org.xcolab.core.documententity.DocumentEntityWrapper;
import org.xcolab.core.proposals.Proposal;
import org.xcolab.core.proposals.template.ProposalTemplate;

import java.lang.reflect.Type;
import java.util.Calendar;

public interface ContestPhase extends DocumentEntityWrapper {

    void removeProposal(Proposal proposal) throws DocumentEntityException;

  	Proposal[] getProposals() throws DocumentEntityException;

    public Calendar[] getActiveDates();

    public void setActiveDates(Calendar[] dates) throws ContestPhaseTimingException;

    public ContestPhaseType getPhaseType();

    public Contest getContest();



}
