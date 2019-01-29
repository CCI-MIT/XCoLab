package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.util.List;

public class MessageActivePhaseAuthorsMassAction extends MessageMassAction {

    public MessageActivePhaseAuthorsMassAction() {
        super("Message contributors in active phase");
    }

    @Override
    protected List<ProposalWrapper> getProposalsToBeMessaged(ContestWrapper contest) {
        ContestPhaseWrapper activeContestPhase = StaticContestContext.getContestClient().getActivePhase(contest.getId());
        return StaticProposalContext.getProposalClient()
                .getActiveProposalsInContestPhase(activeContestPhase.getId());
    }
}
