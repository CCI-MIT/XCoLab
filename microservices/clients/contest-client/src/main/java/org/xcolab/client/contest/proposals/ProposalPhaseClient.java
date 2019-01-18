package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalDto;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ProposalPhaseClient {

    private final RestResource<IProposal2Phase, Long> proposal2PhaseResource = null; // proposal2Phases
    private final RestResource<IProposalContestPhaseAttribute, Long>
            proposalContestPhaseAttributeResource = null; // proposalContestPhaseAttributes
    private final RestResource1<ProposalDto, Long> proposalResource = null; // proposal
    private final RestResource2L<ProposalDto, Long> proposalPhaseIdResource = null; // proposal / phaseIds

    public void invalidateProposal2PhaseCache(long proposalId, long contestPhaseId) {
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(IProposal2Phase.class)
                .withParameter("proposalId", proposalId)
                .withParameter("contestPhaseId", contestPhaseId)
                .asList(), CacheName.PROPOSAL_PHASE);
    }
    public IProposal2Phase getProposal2PhaseByProposalIdContestPhaseId(Long proposalId,
            Long contestPhaseId) throws Proposal2PhaseNotFoundException {
        final IProposal2Phase dto = proposal2PhaseResource.list()
                .queryParam("proposalId", proposalId)
                .queryParam("contestPhaseId", contestPhaseId)
                .withCache(CacheKeys.withClass(IProposal2Phase.class)
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

    public List<IProposal2Phase> getProposal2PhaseByProposalId(Long proposalId) {
        return proposal2PhaseResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public IProposal2Phase getProposal2PhaseByProposalIdVersion(long proposalId, int version) {
         return proposal2PhaseResource.list()
                 .queryParam("proposalId", proposalId)
                 .queryParam("version", version)
                 .withCache(CacheName.PROPOSAL_PHASE)
                 .executeWithResult()
                 .getFirst();
    }

    public List<IProposal2Phase> getProposal2PhaseByContestPhaseId(Long contestPhaseId) {
        return proposal2PhaseResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .execute();
    }

    public void createProposal2Phase(IProposal2Phase proposal2Phase) {
        proposal2PhaseResource.create(proposal2Phase)
                .execute();
    }

    public void updateProposal2Phase(IProposal2Phase proposal2Phase) {
        proposal2PhaseResource.collectionService("updateProposal2Phase", Boolean.class).post(proposal2Phase);
    }

    public void deleteProposal2Phase(IProposal2Phase proposal2Phase) {
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

    public List<IProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(
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
        IProposalContestPhaseAttribute proposalAttribute =
                getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        return proposalAttribute != null && proposalAttribute.getStringValue().equals("true");

    }

    public IProposalContestPhaseAttribute getProposalContestPhaseAttribute(Long proposalId,
            Long contestPhaseId, String name) {
        try {
            return proposalContestPhaseAttributeResource
                    .collectionService("getByContestPhaseProposalIdName", IProposalContestPhaseAttribute.class)
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
        IProposalContestPhaseAttribute
                judges = getOrCreateProposalContestPhaseAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, null,null,null);

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


    public IProposalContestPhaseAttribute persistProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name, Long aditionalId, Long numericValue, String stringValue) {
        IProposalContestPhaseAttribute
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

    public  Boolean hasProposalContestPhaseAttribute(Long proposalId, long contestPhaseId, String name) {
        IProposalContestPhaseAttribute proposalContestPhaseAttribute = getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);

        return proposalContestPhaseAttribute != null;

    }
    public IProposalContestPhaseAttribute getOrCreateProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name, Long aditionalId, Long numericValue, String stringValue) {
        IProposalContestPhaseAttribute proposalContestPhaseAttribute = getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
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
            IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource
                .update(proposalContestPhaseAttribute,
                        proposalContestPhaseAttribute.getId())
                .execute();
    }

    public IProposalContestPhaseAttribute setProposalContestPhaseAttribute(Long proposalId,
            Long contestPhaseId, String name, Long aditionalId, Long numericValue,
            String stringValue) {
        IProposalContestPhaseAttribute proposalContestPhaseAttribute =
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

    public IProposalContestPhaseAttribute createProposalContestPhaseAttribute(
            IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource
                .create(proposalContestPhaseAttribute)
                .execute();
    }

    public Boolean deleteProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId,
            String name) {
        IProposalContestPhaseAttribute pcpa =
                getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
        return proposalContestPhaseAttributeResource.delete(pcpa.getId()).execute();
    }

    public List<Long> getContestPhasesForProposal(long proposalId) {
        return proposalPhaseIdResource.resolveParentId(proposalResource.id(proposalId))
                .list()
                .execute();
    }
}
