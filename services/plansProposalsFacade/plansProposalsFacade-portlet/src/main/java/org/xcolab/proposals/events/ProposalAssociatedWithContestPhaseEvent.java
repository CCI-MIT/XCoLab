package org.xcolab.proposals.events;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;

public class ProposalAssociatedWithContestPhaseEvent {
    private final Proposal proposal;
    private final ContestPhase contestPhase;

    public ProposalAssociatedWithContestPhaseEvent(Proposal proposal, ContestPhase contestPhase) {
        this.proposal = proposal;
        this.contestPhase = contestPhase;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public ContestPhase getContestPhase() {
        return contestPhase;
    }

}
