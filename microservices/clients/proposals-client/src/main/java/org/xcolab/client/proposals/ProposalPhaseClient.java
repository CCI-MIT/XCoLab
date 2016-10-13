package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.Proposal2PhaseDto;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttributeDto;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalPhaseClient {

    private static final Map<RestService, ProposalPhaseClient> instances = new HashMap<>();

    private final RestService proposalService;
    private final RestResource<Proposal2PhaseDto, Long> proposal2PhaseResource;
    private final RestResource<ProposalContestPhaseAttributeDto, Long>
            proposalContestPhaseAttributeResource;


    private ProposalPhaseClient(RestService proposalService) {
        proposal2PhaseResource = new RestResource1<>(
                proposalService, "proposal2Phases", Proposal2PhaseDto.TYPES);
        proposalContestPhaseAttributeResource = new RestResource1<>(proposalService,
                "proposalContestPhaseAttributes", ProposalContestPhaseAttributeDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalPhaseClient fromService(RestService proposalService) {
        ProposalPhaseClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalPhaseClient(proposalService);
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

    public List<ProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(
            Long contestPhaseId, Long proposalId) {
        return DtoUtil.toPojos(proposalContestPhaseAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalContestPhaseAttributeDto.class)
                                .withParameter("proposalId", proposalId)
                                .withParameter("contestPhaseId", contestPhaseId).asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .execute(), proposalService);
    }

    public Boolean isProposalContestPhaseAttributeSetAndTrue(Long proposalId, long contestPhaseId,
            String name) {
        ProposalContestPhaseAttribute proposalAttribute =
                getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        return proposalAttribute != null && proposalAttribute.getStringValue().equals("true");

    }

    public ProposalContestPhaseAttribute getProposalContestPhaseAttribute(Long proposalId,
            Long contestPhaseId, String name) {
        try {
            return proposalContestPhaseAttributeResource
                    .service("getByContestPhaseProposalIdName", ProposalContestPhaseAttribute.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .optionalQueryParam("proposalId", proposalId)
                    .optionalQueryParam("name", name)
                    .getChecked();
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }

    public boolean persistSelectedJudgesAttribute(Long proposalId, Long contestPhaseId,
            List<Long> selectedJudges) {
        ProposalContestPhaseAttribute judges =
                getProposalContestPhaseAttribute(proposalId, contestPhaseId,
                        ProposalContestPhaseAttributeKeys.SELECTED_JUDGES);

        StringBuilder attributeValue = new StringBuilder("");
        if (selectedJudges != null) {
            for (Long userId : selectedJudges) {
                attributeValue.append(userId).append(";");
            }
        }
        judges.setStringValue(attributeValue.toString().replaceAll(";$", ""));

        updateProposalContestPhaseAttribute(judges);
        return true;
    }

    public boolean updateProposalContestPhaseAttribute(
            ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource
                .update(new ProposalContestPhaseAttributeDto(proposalContestPhaseAttribute),
                        proposalContestPhaseAttribute.getId_())
                .execute();
    }

    public ProposalContestPhaseAttribute setProposalContestPhaseAttribute(Long proposalId,
            Long contestPhaseId, String name, Long aditionalId, Long numericValue,
            String stringValue) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute =
                getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        if (proposalContestPhaseAttribute != null) {
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            updateProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        } else {
            proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setName(name);
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
            proposalContestPhaseAttribute =
                    createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        }
    }

    public ProposalContestPhaseAttribute createProposalContestPhaseAttribute(
            ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource
                .create(new ProposalContestPhaseAttributeDto(proposalContestPhaseAttribute))
                .execute().toPojo(proposalService);
    }

    public Boolean deleteProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId,
            String name) {
        ProposalContestPhaseAttribute pcpa =
                getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        return proposalContestPhaseAttributeResource.delete(pcpa.getId_()).execute();
    }
}
