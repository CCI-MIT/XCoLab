package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;

import java.util.List;

public class MessageActivePhaseAuthorsMassAction extends MessageMassAction {

    public MessageActivePhaseAuthorsMassAction() {
        super("Message contributors in active phase");
    }

    @Override
    protected List<Proposal> getProposalsToBeMessaged(Contest contest) {
        ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contest.getId());
        return ProposalClientUtil
                .getActiveProposalsInContestPhase(activeContestPhase.getId());
    }
}
