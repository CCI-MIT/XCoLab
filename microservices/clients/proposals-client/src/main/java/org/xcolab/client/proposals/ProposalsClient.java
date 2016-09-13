package org.xcolab.client.proposals;

import org.w3c.dom.Entity;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.proposals.exceptions.PlanTemplateNotFoundException;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalRatingValueNotFoundException;
import org.xcolab.client.proposals.pojo.PlanSectionDefinition;
import org.xcolab.client.proposals.pojo.PlanTemplate;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.client.proposals.pojo.ProposalAttribute;
import org.xcolab.client.proposals.pojo.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.ProposalRating;
import org.xcolab.client.proposals.pojo.ProposalRatingType;
import org.xcolab.client.proposals.pojo.ProposalRatingValue;
import org.xcolab.client.proposals.pojo.ProposalSupporter;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.ProposalVote;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ProposalsClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource<Proposal> proposalResource = new RestResource<>(
            proposalService, "proposals", Proposal.TYPES);

    private static final RestResource<Proposal2Phase> proposal2PhaseResource = new RestResource<>(
            proposalService, "proposal2Phases", Proposal2Phase.TYPES);

    private static final RestResource<ProposalVote> proposalVoteResource = new RestResource<>(proposalService,
            "proposalVotes", ProposalVote.TYPES);

    private static final RestResource<ProposalContestPhaseAttribute> proposalContestPhaseAttributeResource = new RestResource<>(proposalService,
            "proposalContestPhaseAttributes", ProposalContestPhaseAttribute.TYPES);

    private static final RestResource<ProposalAttribute> proposalAttributeResource = new RestResource<>(proposalService,
            "proposalAttributes", ProposalAttribute.TYPES);

    private static final RestResource<ProposalSupporter> proposalSupporterResource = new RestResource<>(proposalService,
            "proposalSupporters", ProposalSupporter.TYPES);

    private static final RestResource<ProposalVersion> proposalVersionResource = new RestResource<>(proposalService,
            "proposalVersions", ProposalVersion.TYPES);

    private static final RestResource<PlanTemplate> planTemplateResource = new RestResource<>(proposalService,
            "planTemplates", PlanTemplate.TYPES);

    private static final RestResource<PlanSectionDefinition> planSectionDefinitionResource = new RestResource<>(proposalService,
            "planSectionDefinitions", PlanSectionDefinition.TYPES);

    private static final RestResource<ProposalRating> proposalRatingResource = new RestResource<>(proposalService,
            "proposalRatings", ProposalRating.TYPES);

    private static final RestResource<ProposalRatingValue> proposalRatingValueResource = new RestResource<>(proposalService,
            "proposalRatingValues", ProposalRatingValue.TYPES);

    private static final RestResource<ProposalRatingType> proposalRatingTypeResource = new RestResource<>(proposalService,
            "proposalRatingTypes", ProposalRatingType.TYPES);


    public static Proposal createProposal(Proposal proposal) {
        return proposalResource.create(proposal).execute();
    }

    public static List<Proposal> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    public static List<Proposal> getProposalsInContestPhase(Long contestPhaseId) {
        return listProposals(0, Integer.MAX_VALUE, null, true, contestPhaseId, null);
    }


    public static List<Proposal> listProposals(int start, int limit, Long contestId,
                                               Boolean visible, Long contestPhaseId, Integer ribbon) {
        return proposalResource.list()
                .addRange(start, limit)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("visible", visible)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("ribbon", ribbon)
                .execute();
    }

    public static Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
    }

    public static Proposal getProposal(long proposalId, boolean includeDeleted)
            throws ProposalNotFoundException {
        try {
            return proposalResource.get(proposalId)
                    .queryParam("includeDeleted", includeDeleted)
                    .withCache(CacheKeys.withClass(Proposal.class)
                                    .withParameter("proposalId", proposalId)
                                    .withParameter("includeDeleted", includeDeleted).build(),
                            CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ProposalNotFoundException(proposalId);
        }
    }

    public static List<Proposal> getContestIntegrationRelevantSubproposals(Long proposalId) {
        return proposalResource.getSubRestResource(proposalId, "contestIntegrationRelevantSubproposal", Proposal.TYPES).list().execute();
    }

    public static Integer getNumberOfProposalsForJudge(Long userId, Long contestPhaseId) {
        return proposalResource.service("numberOfProposalsForJudge", Integer.class)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();

    }

    public static boolean updateProposal(Proposal proposal) {
        return proposalResource.update(proposal, proposal.getProposalId()).execute();
    }

    public static boolean deleteProposal(long proposalId) {
        return proposalResource.delete(proposalId).execute();
    }

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

    public static List<ProposalSupporter> getProposalSupporters(Long proposalId) {
        return proposalSupporterResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static Integer getProposalSupportersCount(Long proposalId) {

        return proposalSupporterResource.service("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .get();

    }

    public static ProposalVersion getProposalVersionByProposalIdVersion(Long proposalId, Integer version) {
        return proposalVersionResource.service("getByProposalIdVersion", ProposalVersion.class)
                .queryParam("proposalId", proposalId)
                .queryParam("version", version)
                .get();
    }


    public static Integer getProposalCountForActiveContestPhase(Long contestPhasePK) {
        try {
            return proposal2PhaseResource.service(contestPhasePK, "getProposalCount", Integer.class).getChecked();
        } catch (EntityNotFoundException ignored) {
            return 0;
        }
    }

    public static List<ProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(Long contestPhaseId, Long proposalId) {
        return proposalContestPhaseAttributeResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static ProposalContestPhaseAttribute getAllProposalContestPhaseProposalAttributes(Long contestPhaseId, Long proposalId, String name) {
        return proposalContestPhaseAttributeResource.service("getByContestPhaseProposalIdName", ProposalContestPhaseAttribute.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("name", name)
                .get();

    }

    public static ProposalContestPhaseAttribute createProposalContestPhaseAttribute(ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return proposalContestPhaseAttributeResource.create(proposalContestPhaseAttribute).execute();
    }

    public static Integer countProposalVotesInContestPhase(Long contestPhaseId) {
        try {
            return proposalVoteResource.<Proposal, Integer>service("count", Integer.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .withCache(CacheKeys.withClass(Proposal.class)
                            .withParameter("contestPhaseId", contestPhaseId)
                            .asCount(), CacheRetention.REQUEST)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static Integer countProposalVotesInContestPhaseProposalId(Long proposalId, Long contestPhaseId) {
        return proposalVoteResource.service("count", Integer.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public static List<ProposalVote> getProposalVotes(Long contestPhaseId, Long proposalId, Long userId) {
        return proposalVoteResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public static ProposalAttribute createProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource.create(proposalAttribute).execute();
    }


    public static ProposalAttribute getProposalAttribute(Long proposalId, String name, Long additionalId) {
        return proposalAttributeResource.service("getByProposalIdVersionAditionalId", ProposalAttribute.class)
                .queryParam("proposalId", proposalId)
                .queryParam("name", name)
                .queryParam("additionalId", additionalId)
                .get();

    }

    public static List<ProposalVersion> getAllProposalVersions(Long proposalId) {
        return proposalVersionResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static ProposalAttribute getProposalAttribute(long id_) throws ProposalAttributeNotFoundException {
        return proposalAttributeResource.get(id_)
                .execute();

    }

    public static boolean updateProposalAttribute(ProposalAttribute proposalAttribute) {
        return proposalAttributeResource.update(proposalAttribute, proposalAttribute.getId_())
                .execute();
    }

    public static Contest getCurrentContestForProposal(Long proposalId) throws ContestNotFoundException {

        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        ContestPhase contestPhase = ContestClient.getContestPhase(contestPhaseId);
        return ContestClient.getContest(contestPhase.getContestPhasePK());

    }

    public static ProposalAttribute setProposalAttribute(ProposalAttribute proposalAttribute, Long authorId) {
        return proposalAttributeResource.create(proposalAttribute).queryParam("authorId", authorId)
                .execute();
    }

    public static Long getLatestContestPhaseIdInProposal(Long proposalId) {
        List<Proposal2Phase> allP2p = getProposal2PhaseByProposalId(proposalId);
        long newestVersionContestPhaseId = 0;
        int newestVersion = 0;
        for (Proposal2Phase p2p : allP2p) {
            long contestPhaseId = p2p.getContestPhaseId();
            if (p2p.getVersionTo() == -1) {
                return contestPhaseId;
            } else if (p2p.getVersionTo() > newestVersion) {
                newestVersion = p2p.getVersionTo();
                newestVersionContestPhaseId = contestPhaseId;
            }
        }

        if (newestVersion != 0 && newestVersionContestPhaseId != 0) {
            return newestVersionContestPhaseId;
        }
        return null;
    }

    public static List<ProposalAttribute> getAllProposalAttributes(Long proposalId, Integer version) {
        return proposalAttributeResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("version", version)
                .execute();
    }

    public static PlanTemplate getPlanTemplate(long Id_) throws PlanTemplateNotFoundException {
        return planTemplateResource.get(Id_)
                .execute();

    }

    public static List<PlanSectionDefinition> getPlanSectionDefinitionByPlanTemplateId(Long planTemplateId, Boolean weight) {

        return planSectionDefinitionResource.list()
                .optionalQueryParam("planTemplateId", planTemplateId)
                .optionalQueryParam("weight", ((weight == null) ? (false) : weight))
                .execute();
    }

    public static List<ProposalRating> getProposalRatingsByProposalUserContestPhase(Long proposalId, Long contestPhaseId, Long userId) {
        return proposalRatingResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public static ProposalRatingValue getProposalRatingValue(long id) {
        return proposalRatingValueResource.get(id)
                .execute();
    }

    public static ProposalRatingType getProposalRatingType(long id) {
            return proposalRatingTypeResource.get(id)
                    .execute();

    }
}
