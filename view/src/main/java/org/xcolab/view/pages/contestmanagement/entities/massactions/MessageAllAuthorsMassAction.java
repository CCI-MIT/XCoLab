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
        Long contestPK = contest.getContestPK();
        List<ContestPhase> allPhases = ContestClientUtil.getAllContestPhases(contestPK);
        Map<Long, Proposal> proposalsMap = new HashMap<>();
        for (ContestPhase cp : allPhases) {
            ContestPhaseType cpt = ContestClientUtil.getContestPhaseType(cp.getContestPhaseType());
            if (cpt.getStatus().equals(ContestStatus.OPEN_FOR_SUBMISSION.name())) {
                List<Proposal> proposals =
                        ProposalClientUtil.getActiveProposalsInContestPhase(cp.getContestPhasePK());
                for (Proposal p : proposals) {
                    proposalsMap.putIfAbsent(p.getProposalId(), p);
                }
            }
        }

        List<Proposal> ret = new ArrayList<>();
        ret.addAll(proposalsMap.values());
        return ret;
    }
}
