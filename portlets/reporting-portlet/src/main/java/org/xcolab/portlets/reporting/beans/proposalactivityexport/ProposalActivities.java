package org.xcolab.portlets.reporting.beans.proposalactivityexport;

import com.ext.portlet.model.Proposal;

/**
 * @author pdeboer
 *         First created on 18/07/14 at 13:21
 */
public class ProposalActivities {
    Proposal proposal;
    int numDifferentDaysProposalUpdates;
    int numUpdates;
    int numDaysCreationIsBeforeFinalistSelection;

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public int getNumDifferentDaysProposalUpdates() {
        return numDifferentDaysProposalUpdates;
    }

    public void setNumDifferentDaysProposalUpdates(int numDifferentDaysProposalUpdates) {
        this.numDifferentDaysProposalUpdates = numDifferentDaysProposalUpdates;
    }

    public int getNumUpdates() {
        return numUpdates;
    }

    public void setNumUpdates(int numUpdates) {
        this.numUpdates = numUpdates;
    }

    public int getNumDaysCreationIsBeforeFinalistSelection() {
        return numDaysCreationIsBeforeFinalistSelection;
    }

    public void setNumDaysCreationIsBeforeFinalistSelection(int numDaysCreationIsBeforeFinalistSelection) {
        this.numDaysCreationIsBeforeFinalistSelection = numDaysCreationIsBeforeFinalistSelection;
    }
}
