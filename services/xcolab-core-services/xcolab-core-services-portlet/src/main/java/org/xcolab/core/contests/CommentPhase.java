package org.xcolab.core.contests;

import org.xcolab.core.proposals.Proposal;

import java.util.Calendar;

/**
 * User: jintrone
 * Date: 11/19/12
 * Time: 2:09 PM
 */
public interface CommentPhase {

     void addProposal(Proposal proposal, Calendar version);
}
