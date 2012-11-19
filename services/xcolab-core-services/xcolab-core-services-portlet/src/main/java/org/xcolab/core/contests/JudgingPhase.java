package org.xcolab.core.contests;

import org.xcolab.core.proposals.Proposal;

import java.util.Calendar;

/**
 * User: jintrone
 * Date: 11/19/12
 * Time: 2:05 PM
 */
public interface JudgingPhase {

    void addProposal(Proposal proposal, Calendar version);
}
