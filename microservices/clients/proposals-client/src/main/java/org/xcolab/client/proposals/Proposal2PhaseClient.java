package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class Proposal2PhaseClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource<Proposal2Phase, Long> proposal2PhaseResource = new RestResource1<>(
            proposalService, "proposal2Phases", Proposal2Phase.TYPES);

    public static Proposal2Phase getProposal2PhaseByProposalIdContestPhaseId(Long proposalId, Long contestPhaseId) throws Proposal2PhaseNotFoundException {
        try {
            return proposal2PhaseResource.service("getByContestPhaseIdProposalId", Proposal2Phase.class)
                    .queryParam("proposalId", proposalId)
                    .queryParam("contestPhaseId", contestPhaseId)
                    .getChecked();
        } catch (EntityNotFoundException ignored) {
            throw new Proposal2PhaseNotFoundException(proposalId);
        }
    }

    public static List<Proposal2Phase> getProposal2PhaseByProposalId(Long proposalId) {
        return proposal2PhaseResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static void createProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.create(proposal2Phase).execute();
    }

    public static void updateProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.service("updateProposal2Phase", Boolean.class).post(proposal2Phase);
    }

    public static void deleteProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.service("deleteProposal2Phase", Boolean.class)
                .post(proposal2Phase);
    }

    public static Integer getProposalCountForActiveContestPhase(Long contestPhasePK) {

        try {
            return proposal2PhaseResource.service(contestPhasePK, "getProposalCount", Integer.class).getChecked();
        } catch (EntityNotFoundException ignored) {
            return 0;
        }
    }

    public static void promoteProposal(Long proposalId, Long activePhaseForContest, Long currentProposalContestPhase) {
        proposal2PhaseResource.service("promoteProposal", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("activePhaseForContest", activePhaseForContest)
                .queryParam("currentProposalContestPhase", currentProposalContestPhase)
                .get();
    }
}
