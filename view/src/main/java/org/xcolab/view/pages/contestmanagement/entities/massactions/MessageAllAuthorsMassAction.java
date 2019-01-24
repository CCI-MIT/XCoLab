package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageAllAuthorsMassAction extends MessageMassAction {

    public MessageAllAuthorsMassAction() {
        super("Message all proposal authors");
    }

    @Override
    protected List<ProposalWrapper> getProposalsToBeMessaged(ContestWrapper contest) {
        Long contestId = contest.getId();
        List<ContestPhaseWrapper> allPhases = StaticContestContext.getContestClient()
                .getAllContestPhases(contestId);
        Map<Long, ProposalWrapper> proposalsMap = new HashMap<>();
        for (ContestPhaseWrapper cp : allPhases) {
            IContestPhaseType cpt = StaticContestContext.getContestClient()
                    .getContestPhaseType(cp.getContestPhaseTypeId());
            if (cpt.getStatus().equals(ContestStatus.OPEN_FOR_SUBMISSION.name())) {
                List<ProposalWrapper> proposals =
                        StaticProposalContext.getProposalClient()
                                .getActiveProposalsInContestPhase(cp.getId());
                for (ProposalWrapper p : proposals) {
                    proposalsMap.putIfAbsent(p.getId(), p);
                }
            }
        }

        List<ProposalWrapper> ret = new ArrayList<>();
        ret.addAll(proposalsMap.values());
        return ret;
    }
}
