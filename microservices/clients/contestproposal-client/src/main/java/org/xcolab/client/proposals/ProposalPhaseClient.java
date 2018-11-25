package org.xcolab.client.proposals;

import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalPhaseClient {

    private static final Map<ServiceNamespace, ProposalPhaseClient> instances = new HashMap<>();

    private final RestResource<Proposal2Phase, Long> proposal2PhaseResource;
    private final RestResource<ProposalContestPhaseAttribute, Long>
            proposalContestPhaseAttributeResource;
    private final RestResource1<Proposal, Long> proposalResource;
    private final RestResource2L<Proposal, Long> proposalPhaseIdResource;

    private ProposalPhaseClient() {
        proposal2PhaseResource = new RestResource1<>(ProposalResource.PROPOSAL_2_PHASE,
                Proposal2Phase.TYPES);
        proposalContestPhaseAttributeResource = new RestResource1<>(
                ProposalResource.PROPOSAL_CONTEST_PHASE_ATTRIBUTE,
                ProposalContestPhaseAttribute.TYPES);
        proposalResource = new RestResource1<>(ProposalResource.PROPOSAL, Proposal.TYPES);

        proposalPhaseIdResource = new RestResource2L<>(proposalResource, "phaseIds",
                TypeProvider.LONG);
    }

    public static ProposalPhaseClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace,
                serviceNamespace1 -> new ProposalPhaseClient());
    }

    public void invalidateProposal2PhaseCache(long proposalId, long contestPhaseId) {
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(Proposal2Phase.class)
                .withParameter("proposalId", proposalId)
                .withParameter("contestPhaseId", contestPhaseId)
                .asList(), CacheName.PROPOSAL_PHASE);
    }
    public Proposal2Phase getProposal2PhaseByProposalIdContestPhaseId(Long proposalId,
            Long contestPhaseId) throws Proposal2PhaseNotFoundException {
        final Proposal2Phase dto = proposal2PhaseResource.list()
                .queryParam("proposalId", proposalId)
                .queryParam("contestPhaseId", contestPhaseId)
                .withCache(CacheKeys.withClass(Proposal2Phase.class)
                        .withParameter("proposalId", proposalId)
                        .withParameter("contestPhaseId",contestPhaseId)
                        .asList(), CacheName.PROPOSAL_PHASE)
                .executeWithResult()
                .getOneIfExists();
        if (dto == null) {
            throw new Proposal2PhaseNotFoundException(proposalId, contestPhaseId);
        }
        return dto;
    }

    public List<Proposal2Phase> getProposal2PhaseByProposalId(Long proposalId) {
        return proposal2PhaseResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public Proposal2Phase getProposal2PhaseByProposalIdVersion(long proposalId, int version) {
         return proposal2PhaseResource.list()
                 .queryParam("proposalId", proposalId)
                 .queryParam("version", version)
                 .withCache(CacheName.PROPOSAL_PHASE)
                 .executeWithResult()
                 .getFirst();
    }

    public List<Proposal2Phase> getProposal2PhaseByContestPhaseId(Long contestPhaseId) {
        return proposal2PhaseResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .execute();
    }

    public void createProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.create(new Proposal2Phase(proposal2Phase))
                .execute();
    }

    public void updateProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.collectionService("updateProposal2Phase", Boolean.class).post(proposal2Phase);
    }

    public void deleteProposal2Phase(Proposal2Phase proposal2Phase) {
        proposal2PhaseResource.collectionService("deleteProposal2Phase", Boolean.class)
                .post(proposal2Phase);
    }

    public Integer getProposalCountForActiveContestPhase(Long contestPhaseId) {

        try {
            return proposal2PhaseResource.elementService(contestPhaseId, "getProposalCount", Integer.class)
                    .getChecked();
        } catch (EntityNotFoundException ignored) {
            return 0;
        }
    }

    public void promoteProposal(Long proposalId, Long activePhaseForContest,
            Long currentProposalContestPhase) {
        proposal2PhaseResource.collectionService("promoteProposal", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("activePhaseForContest", activePhaseForContest)
                .queryParam("currentProposalContestPhase", currentProposalContestPhase)
                .post();
    }

    public List<ProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(
            Long contestPhaseId, Long proposalId) {
        return proposalContestPhaseAttributeResource.list()
//                .withCache(CacheKeys.withClass(ProposalContestPhaseAttribute.class)
//                                .withParameter("proposalId", proposalId)
//                                .withParameter("contestPhaseId", contestPhaseId).asList(),
//                        CacheRetention.MISC_MEDIUM)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .execute();
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
                    .collectionService("getByContestPhaseProposalIdName", ProposalContestPhaseAttribute.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .optionalQueryParam("proposalId", proposalId)
                    .optionalQueryParam("name", name)
                    .getChecked()
                    ;
        } catch (EntityNotFoundException ignored) {
            return null;
        }
    }


    public  boolean persistSelectedJudgesAttribute(Long proposalId, Long contestPhaseId, List<Long> selectedJudges) {
        ProposalContestPhaseAttribute judges = getOrCreateProposalContestPhaseAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, null,null,null);

        StringBuilder attributeValue = new StringBuilder();
        if (selectedJudges != null) {
            for (Long userId : selectedJudges) {
                attributeValue.append(userId).append(";");
            }
        }
        judges.setStringValue(attributeValue.toString().replaceAll(";$", ""));

        updateProposalContestPhaseAttribute(judges);
        return true;
    }


    public  ProposalContestPhaseAttribute persistProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name, Long aditionalId, Long numericValue, String stringValue) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
        proposalContestPhaseAttribute.setProposalId(proposalId);
        proposalContestPhaseAttribute.setName(name);
        proposalContestPhaseAttribute.setAdditionalId(aditionalId);
        proposalContestPhaseAttribute.setNumericValue(numericValue);
        proposalContestPhaseAttribute.setStringValue(stringValue);
        proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
        proposalContestPhaseAttribute = createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
        return proposalContestPhaseAttribute;
    }

    public  Boolean hasProposalContestPhaseAttribute(Long proposalId, long contestPhaseId, String name) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);

        return proposalContestPhaseAttribute != null;

    }
    public  ProposalContestPhaseAttribute getOrCreateProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name, Long aditionalId, Long numericValue, String stringValue) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        } else {
            proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setName(name);
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
            proposalContestPhaseAttribute = createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        }
    }
    public boolean updateProposalContestPhaseAttribute(
            ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource
                .update(new ProposalContestPhaseAttribute(proposalContestPhaseAttribute),
                        proposalContestPhaseAttribute.getId())
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
                .create(new ProposalContestPhaseAttribute(proposalContestPhaseAttribute))
                .execute();
    }

    public Boolean deleteProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId,
            String name) {
        ProposalContestPhaseAttribute pcpa =
                getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        return proposalContestPhaseAttributeResource.delete(pcpa.getId()).execute();
    }

    public List<Long> getContestPhasesForProposal(long proposalId) {
        return proposalPhaseIdResource.resolveParentId(proposalResource.id(proposalId))
                .list()
                .execute();
    }
}
