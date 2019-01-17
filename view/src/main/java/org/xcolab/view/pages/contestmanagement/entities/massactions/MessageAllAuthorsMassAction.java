package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.client.contest.pojo.Proposal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageAllAuthorsMassAction extends MessageMassAction {

    public MessageAllAuthorsMassAction() {
        super("Message all proposal authors");
    }

    @Override
    protected List<Proposal> getProposalsToBeMessaged(ContestWrapper contest) {
        Long contestId = contest.getId();
        List<ContestPhaseWrapper> allPhases = ContestClientUtil.getAllContestPhases(contestId);
        Map<Long, Proposal> proposalsMap = new HashMap<>();
        for (ContestPhaseWrapper cp : allPhases) {
            IContestPhaseType cpt = ContestClientUtil.getContestPhaseType(cp.getContestPhaseTypeId());
            if (cpt.getStatus().equals(ContestStatus.OPEN_FOR_SUBMISSION.name())) {
                List<Proposal> proposals =
                        ProposalClientUtil.getActiveProposalsInContestPhase(cp.getId());
                for (Proposal p : proposals) {
                    proposalsMap.putIfAbsent(p.getId(), p);
                }
            }
        }

        List<Proposal> ret = new ArrayList<>();
        ret.addAll(proposalsMap.values());
        return ret;
    }
}
