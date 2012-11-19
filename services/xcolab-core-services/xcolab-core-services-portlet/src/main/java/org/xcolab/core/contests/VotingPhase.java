package org.xcolab.core.contests;

import org.xcolab.core.proposals.Proposal;

import java.util.Calendar;

/**
 * User: jintrone
 * Date: 11/19/12
 * Time: 1:37 PM
 */
public interface VotingPhase {

    void addProposal(Proposal proposal, Calendar version);

    public int getVotesForProposal(Proposal p);

    public int getVoteCount();


}
