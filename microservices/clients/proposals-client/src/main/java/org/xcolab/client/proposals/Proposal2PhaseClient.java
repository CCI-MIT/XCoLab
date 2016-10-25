package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.Proposal2PhaseDto;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Proposal2PhaseClient {

    private static final Map<RestService, Proposal2PhaseClient> instances = new HashMap<>();

    private final RestService proposalService;
    private final RestResource<Proposal2PhaseDto, Long> proposal2PhaseResource;

    private Proposal2PhaseClient(RestService proposalService) {
        proposal2PhaseResource = new RestResource1<>(
                proposalService, "proposal2Phases", Proposal2PhaseDto.TYPES);
        this.proposalService = proposalService;
    }

    public static Proposal2PhaseClient fromService(RestService proposalService) {
        Proposal2PhaseClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new Proposal2PhaseClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
    }

    public Proposal2Phase getProposal2PhaseByProposalIdContestPhaseId(Long proposalId,
            Long contestPhaseId) throws Proposal2PhaseNotFoundException {
        try {
            return proposal2PhaseResource
                    .service("getByContestPhaseIdProposalId", Proposal2Phase.class)
                    .queryParam("proposalId", proposalId)
                    .queryParam("contestPhaseId", contestPhaseId)

                    .getChecked();
        } catch (EntityNotFoundException ignored) {
            throw new Proposal2PhaseNotFoundException(proposalId);
        }
    }

    public List<Proposal2Phase> getProposal2PhaseByProposalId(Long proposalId) {
        return DtoUtil.toPojos(proposal2PhaseResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute(), proposalService);
    }

    public void createProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.create(new Proposal2PhaseDto(proposal2Phase))
                .execute();
    }

    public void updateProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.service("updateProposal2Phase", Boolean.class).post(proposal2Phase);
    }

    public void deleteProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.service("deleteProposal2Phase", Boolean.class)
                .post(proposal2Phase);
    }

    public Integer getProposalCountForActiveContestPhase(Long contestPhasePK) {

        try {
            return proposal2PhaseResource.service(contestPhasePK, "getProposalCount", Integer.class)
                    .getChecked();
        } catch (EntityNotFoundException ignored) {
            return 0;
        }
    }

    public void promoteProposal(Long proposalId, Long activePhaseForContest,
            Long currentProposalContestPhase) {
        proposal2PhaseResource.service("promoteProposal", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("activePhaseForContest", activePhaseForContest)
                .queryParam("currentProposalContestPhase", currentProposalContestPhase)
                .get();
    }
}
