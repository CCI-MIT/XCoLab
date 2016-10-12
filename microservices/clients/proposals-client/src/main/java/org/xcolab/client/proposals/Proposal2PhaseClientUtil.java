package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class Proposal2PhaseClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");

    private static final Proposal2PhaseClient client =
            Proposal2PhaseClient.fromService(proposalService);

    public static Proposal2PhaseClient getClient() {
        return client;
    }

    public static Proposal2Phase getProposal2PhaseByProposalIdContestPhaseId(
            Long proposalId, Long contestPhaseId) throws Proposal2PhaseNotFoundException {
        return client.getProposal2PhaseByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }

    public static List<Proposal2Phase> getProposal2PhaseByProposalId(
            Long proposalId) {
        return client.getProposal2PhaseByProposalId(proposalId);
    }

    public static void createProposal2Phase(Proposal2Phase proposal2Phase) {
        client.createProposal2Phase(proposal2Phase);
    }

    public static void updateProposal2Phase(Proposal2Phase proposal2Phase) {
        client.updateProposal2Phase(proposal2Phase);
    }

    public static void deleteProposal2Phase(Proposal2Phase proposal2Phase) {
        client.deleteProposal2Phase(proposal2Phase);
    }

    public static Integer getProposalCountForActiveContestPhase(Long contestPhasePK) {
        return client.getProposalCountForActiveContestPhase(contestPhasePK);
    }

    public static void promoteProposal(Long proposalId, Long activePhaseForContest,
            Long currentProposalContestPhase) {
        client.promoteProposal(proposalId, activePhaseForContest, currentProposalContestPhase);
    }
}
