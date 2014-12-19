package org.xcolab.proposals.events;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

public class ProposalAssociatedWithContestPhaseEvent extends BaseProposalUserActivityEvent {
    private final ContestPhase contestPhase;

    public ProposalAssociatedWithContestPhaseEvent(Proposal proposal, ContestPhase contestPhase, User user) {
        super(proposal, user);
        this.contestPhase = contestPhase;
    }

    public ContestPhase getContestPhase() {
        return contestPhase;
    }

}
