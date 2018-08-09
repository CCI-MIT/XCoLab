package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageAllAuthorsMassAction extends MessageMassAction {

    public MessageAllAuthorsMassAction() {
        super("Message all proposal authors");
    }

    @Override
    protected List<Proposal> getProposalsToBeMessaged(Contest contest) {
        Long contestId = contest.getId();
        List<ContestPhase> allPhases = ContestClientUtil.getAllContestPhases(contestId);
        Map<Long, Proposal> proposalsMap = new HashMap<>();
        for (ContestPhase cp : allPhases) {
            ContestPhaseType cpt = ContestClientUtil.getContestPhaseType(cp.getContestPhaseTypeId());
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
