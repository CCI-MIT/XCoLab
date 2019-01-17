package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.client.contest.pojo.Proposal;

import java.util.List;

public class MessageActivePhaseAuthorsMassAction extends MessageMassAction {

    public MessageActivePhaseAuthorsMassAction() {
        super("Message contributors in active phase");
    }

    @Override
    protected List<Proposal> getProposalsToBeMessaged(ContestWrapper contest) {
        ContestPhaseWrapper activeContestPhase = ContestClientUtil.getActivePhase(contest.getId());
        return ProposalClientUtil
                .getActiveProposalsInContestPhase(activeContestPhase.getId());
    }
}
