package org.xcolab.client.proposals;

import org.xcolab.client.activity.ActivityClient;
import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalDto;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.tiers.ProposalReference;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public final class ProposalClient {

    private final RestResource<ProposalDto, Long> proposalResource;
    private final RestResource<Long, Long> proposalThreadIdResource;
    private final RestResource<Long, Long> proposalIdResource;
    private final RestResource1<ProposalVersion, Long> proposalVersionResource;
    private final RestResource1<ProposalReference, Long> proposalReferenceResource;

    //TODO COLAB-2600: methods that use this should be in the service!
    private final ContestClient contestClient;

    private final ActivityClient activityClient;

    public ProposalClient() {

        proposalResource = new RestResource1<>(ProposalResource.PROPOSAL, ProposalDto.TYPES);
        proposalIdResource = new RestResource1<>(ProposalResource.PROPOSAL_ID, TypeProvider.LONG);
        proposalThreadIdResource = new RestResource1<>(ProposalResource.PROPOSAL_THREAD_ID,
                TypeProvider.LONG);
        proposalVersionResource = new RestResource1<>(ProposalResource.PROPOSAL_VERSION,
                ProposalVersion.TYPES);
        proposalReferenceResource = new RestResource1<>(ProposalResource.PROPOSAL_REFERENCE,
                ProposalReference.TYPES);

        contestClient = ContestClientUtil.getClient();
        activityClient = StaticActivityContext.getActivityClient();
    }

    public Proposal createProposal(Proposal proposal) {
        return proposalResource
                .create(new ProposalDto(proposal))
                .execute().toProposal();
    }

    public List<Proposal> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    public List<Proposal> listProposals(int start, int limit, Long contestId, Boolean visible,
        Long contestPhaseId, Integer ribbon) {
        return listProposals(start, limit, null, contestId, null,
            null, visible, contestPhaseId, ribbon);
    }

    public List<Proposal> listProposalsInActiveContests() {
        return ProposalDto.toProposals(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("visible", true)
                .queryParam("contestPrivate", false)
                .queryParam("contestActive", true)
                .withCache(CacheName.PROPOSAL_LIST)
                .execute());
    }

    public List<Proposal> listProposalsInCompletedContests(List<Integer> ribbons) {
        return ProposalDto.toProposals(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("visible", true)
                .queryParam("contestPrivate", false)
                .queryParam("contestActive", false)
                .optionalQueryParam("ribbon", ribbons)
                .withCache(CacheName.PROPOSAL_LIST_CLOSED)
                .execute());
    }

    public List<Proposal> listProposals(int start, int limit, String filterText, Long contestId,
        List<Long> contestTypeIds, List<Long> contestTierIds, Boolean visible, Long contestPhaseId,
        Integer ribbon) {
        return ProposalDto.toProposals(proposalResource.list()
                .addRange(start, limit)
                .optionalQueryParam("filterText", filterText)
                .optionalQueryParam("contestIds", contestId)
                .optionalQueryParam("contestTypeIds", contestTypeIds)
                .optionalQueryParam("contestTierIds", contestTierIds)
                .optionalQueryParam("visible", visible)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("ribbon", ribbon)
                .execute());
    }

    public List<Proposal> getProposalsInPublicContests(List<Long> contestTypeIds,
            List<Long> contestTierIds, String filterText) {

        return ProposalDto.toProposals(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .optionalQueryParam("filterText", filterText)
                .optionalQueryParam("contestTypeIds", contestTypeIds)
                .optionalQueryParam("contestTierIds", contestTierIds)
                .queryParam("visible", true)
                .queryParam("contestPrivate", false)
                .withCache(CacheName.MISC_SHORT)
                .execute());
    }

    public List<Long> listProposalIds(int start, int limit, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        return proposalIdResource.list()
                .addRange(start, limit)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("visible", visible)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("ribbon", ribbon)
                .execute();
    }

    public List<Long> listThreadIds(List<Long> proposalIds, Long contestId, Long contestPhaseId,
            Integer ribbon) {
        return proposalThreadIdResource.list()
                .optionalQueryParam("proposalIds", proposalIds)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("ribbon", ribbon)
                .execute();
    }

    public List<Proposal> getProposalsInContestPhase(Long contestPhaseId) {
        return listProposals(0, Integer.MAX_VALUE, null, true, contestPhaseId, null);
    }

    public List<Proposal> getAllProposals() {
        return listProposals(0, Integer.MAX_VALUE, null, true, null, null);
    }

    public List<Proposal> getProposalsInContest(Long contestId) {
        ContestPhase cp = contestClient.getActivePhase(contestId);

        return listProposals(0, Integer.MAX_VALUE, null, true, cp.getId(), null);
    }

    //TODO: move to proposals/{proposalId}/teamMembers endpoint
    public List<Member> getProposalMembers(Long proposalId) {
        return proposalResource.elementService(proposalId, "allMembers", Member.TYPES.getTypeReference())
                .getList();
    }

    //TODO: move to proposals/{proposalId}/teamMembers endpoint
    public void removeMemberFromProposalTeam(Long proposalId, Long userId) {
        proposalResource.elementService(proposalId, "removeMemberFromProposalTeam", Boolean.class)
                .queryParam("userId", userId)
                .delete();

        activityClient.createActivityEntry(ProposalActivityType.MEMBER_REMOVED, userId, proposalId);
    }

    public void promoteMemberToProposalOwner(Long proposalId, Long userId) {
        proposalResource.elementService(proposalId, "promoteMemberToProposalOwner", Boolean.class)
                .queryParam("userId", userId)
                .post();
    }

    public Boolean isUserInProposalTeam(Long proposalId, Long memberUserId) {
        return proposalResource.elementService(proposalId, "isUserInProposalTeam", Boolean.class)
                .queryParam("memberUserId", memberUserId)
                .get();
    }

    public List<Proposal> getActiveProposalsInContestPhase(Long contestPhaseId, CacheName cacheName) {
        return ProposalDto.toProposals(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .optionalQueryParam("visible", true)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .withCache(CacheKeys.withClass(ProposalDto.class)
                        .withParameter("contestPhaseId", contestPhaseId).asList(), cacheName)
                .execute());
    }

    public List<Proposal> getActiveProposalsInContestPhase(Long contestPhaseId) {
        return getActiveProposalsInContestPhase(contestPhaseId, CacheName.PROPOSAL_LIST);
    }

    public Proposal createProposal(long authorUserId, long contestPhaseId, boolean publishActivity) {
        return proposalResource.collectionService("createProposal", Proposal.class)
                .queryParam("authorUserId", authorUserId)
                .queryParam("contestPhaseId", contestPhaseId)
                .queryParam("publishActivity", publishActivity)
                .post();
    }

    public List<Proposal> getContestIntegrationRelevantSubproposals(Long proposalId) {
        return ProposalDto.toProposals(proposalResource.elementService(proposalId, "contestIntegrationRelevantSubproposal",
                ProposalDto.TYPES.getTypeReference()).getList());
    }

    public List<Proposal> getLinkingProposalsForUser(long userId) {
        final List<Proposal> userProposals = getMemberProposals(userId);
        List<Proposal> linkingProposals = new ArrayList<>();
        for (Proposal proposal : userProposals) {
            linkingProposals.addAll(getLinkingProposals(proposal.getId()));
        }
        return linkingProposals;
    }

    public List<Proposal> getMemberProposals(Long userId) {
        return ProposalDto.toProposals(proposalResource.collectionService("getMemberProposals", ProposalDto.TYPES.getTypeReference())
                .queryParam("userId", userId)
                .getList());
    }

    public List<Proposal> getLinkingProposals(long proposalId) {
        List<ProposalReference> proposalReferences = getProposalReference(null, proposalId);
        List<Proposal> linkingProposals = new ArrayList<>();
        for (ProposalReference proposalReference : proposalReferences) {
            try {
                final Proposal proposal = getProposal(proposalReference.getProposalId());
                if (!linkingProposals.contains(proposal)) {
                    linkingProposals.add(proposal);
                }
            } catch (ProposalNotFoundException ignored) {

            }
        }
        return linkingProposals;
    }

    public Proposal getProposalByThreadId(long threadId) {
        final ProposalDto proposalDto = proposalResource.list()
                .queryParam("threadId", threadId)
                .executeWithResult()
                .getOneIfExists();
        if (proposalDto != null) {
            return proposalDto.toProposal();
        }
        throw new ProposalNotFoundException("No proposal with threadId = " + threadId);
    }

    public Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
    }

    public void invalidateProposalCache(long proposalId) {
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(Proposal.class)
                .withParameter("proposalId", proposalId)
                .withParameter("includeDeleted", false).build(), CacheName.MISC_REQUEST);
    }

    public Proposal getProposal(long proposalId, boolean includeDeleted)
            throws ProposalNotFoundException {
        try {
            return proposalResource.get(proposalId)
                    .queryParam("includeDeleted", includeDeleted)
                    .withCache(CacheKeys.withClass(ProposalDto.class)
                                    .withParameter("proposalId", proposalId)
                                    .withParameter("includeDeleted", includeDeleted).build(),
                            CacheName.MISC_REQUEST)
                    .executeChecked().toProposal();
        } catch (EntityNotFoundException e) {
            throw new ProposalNotFoundException(proposalId);
        }
    }

    public List<ProposalReference> getProposalReference(Long proposalId, Long subProposalId) {
        return proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute();
    }

    public List<Proposal> getSubproposals(Long proposalId, Boolean includeProposalsInSameContest) {
        return ProposalDto.toProposals(proposalResource
                .elementService(proposalId, "getSubproposals", ProposalDto.TYPES.getTypeReference())
                .queryParam("includeProposalsInSameContest", includeProposalsInSameContest)
                .getList());
    }


    public List<Proposal> getLinkingProposalsForProposalId(Long proposalId) {
        return ProposalDto.toProposals(proposalResource.elementService(proposalId, "listProposalLinks", ProposalDto.TYPES.getTypeReference())
                .getList());
    }

    public Integer getProposalMaterializedPoints(Long proposalId) {
        return proposalResource.elementService(proposalId, "materializedPoints", Integer.class).get();
    }


    public Integer getNumberOfProposalsAlreadyJudgedForJudge(Long userId, Long contestPhaseId) {
        return proposalResource.collectionService("numberOfProposalsAlreadyJudgedForJudge", Integer.class)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();

    }
    public Integer getNumberOfProposalsForJudge(Long userId, Long contestPhaseId) {
        return proposalResource.collectionService("numberOfProposalsForJudge", Integer.class)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();

    }

    public ProposalReference getProposalReferenceByProposalIdSubProposalId(Long proposalId,
            Long subProposalId) {
        List<ProposalReference> list = proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public boolean updateProposal(Proposal proposal) {
        return proposalResource
                .update(new ProposalDto(proposal), proposal.getId())
                .cacheKey(CacheKeys.withClass(ProposalDto.class)
                                .withParameter("proposalId", proposal.getId())
                                .withParameter("includeDeleted", false).build())
                .execute();
    }

    public boolean deleteProposal(long proposalId) {
        return proposalResource.delete(proposalId).execute();
    }

    public ProposalVersion getProposalVersionByProposalIdVersion(Long proposalId, Integer version) {
        return proposalVersionResource.collectionService("getByProposalIdVersion", ProposalVersion.class)
                .queryParam("proposalId", proposalId)
                .queryParam("version", version)
                .get()
                ;
    }
    public List<ProposalVersion> getProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId, int start, int end) {
        return proposalVersionResource.collectionService("getGroupedVersionsByContest", ProposalVersion.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("contestId", contestId)
                .queryParam("start", start)
                .queryParam("end", end)
                .getList();

    }
    public Integer getMaxVersion(long proposalId) {
        return proposalResource.elementService(proposalId, "maxVersion", Integer.class)
                .get();
    }
    public Integer countProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId) {
        return proposalVersionResource.collectionService("countGroupedVersionsByContest", Integer.class)
                .queryParam("proposalId", proposalId)
                .queryParam("contestId", contestId)
                .get();
    }
    public Integer countProposalVersions(Long proposalId) {
        return proposalVersionResource.collectionService("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public ProposalVersion getProposalVersionByProposal(Long proposalId) {
        return proposalVersionResource.collectionService("getByProposalIdVersion", ProposalVersion.class)
                .queryParam("proposalId", proposalId)
                .get()
                ;
    }

    public List<ProposalVersion> getAllProposalVersions(Long proposalId) {
        return proposalVersionResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public ContestType getContestTypeFromProposalId(Long proposalId) {
        try {
            Contest contest = getCurrentContestForProposal(proposalId);
            return ContestTypeClient.getContestType(contest.getContestTypeId());
        } catch (ContestNotFoundException e) {
            throw ReferenceResolutionException.toObject(Contest.class, "")
                    .fromObject(Proposal.class, proposalId);
        }
    }

    public Contest getCurrentContestForProposal(Long proposalId) throws ContestNotFoundException {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        ContestPhase contestPhase = contestClient.getContestPhase(contestPhaseId);
        return contestClient.getContest(contestPhase.getContestId());

    }

    public Long getLatestContestPhaseIdInProposal(Long proposalId) {
        return proposalResource.elementService(proposalId, "getLatestContestPhaseIdInProposal", Long.class)
                .get();
    }

    public Contest getLatestContestInProposal(Long proposalId) throws ContestNotFoundException {
        return contestClient
                .getContest(getLatestContestPhaseInProposal(proposalId).getContestId());
    }


    public ContestPhase getLatestContestPhaseInProposal(Long proposalId) {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        return contestClient.getContestPhase(contestPhaseId);
    }


    public void populateTableWithProposal(long proposalId) {
        proposalReferenceResource.collectionService("populateTableWithProposal", Boolean.class)
                .queryParam("proposalId", proposalId)
                .get();

    }

    public boolean isMemberSubscribedToProposal(long proposalId, long userId) {
        return activityClient.isSubscribed(userId,
                ActivityCategory.PROPOSAL, proposalId);
    }

    public void subscribeMemberToProposal(long proposalId, long userId) {

        subscribeMemberToProposal(proposalId, userId, false);
    }

    private void subscribeMemberToProposal(long proposalId, long userId, boolean automatic) {
        activityClient.addSubscription(userId, ActivityCategory.PROPOSAL, proposalId);
    }

    public void unsubscribeMemberFromProposal(long proposalId, long userId) {
        unsubscribeMemberFromProposal(proposalId, userId, false);

    }

    private void unsubscribeMemberFromProposal(long proposalId, long userId, boolean automatic) {
        activityClient.deleteSubscription(userId, ActivityCategory.PROPOSAL, proposalId);
    }

    private static String convertListToGetParameter(List<Long> list, String parameterName) {
        if(list.isEmpty()){
            return "";
        }
        String parameterList = "";
        for(int i=0; i<list.size()-2; i++) {
            parameterList += list.get(i) + "&" + parameterName + "=";
        }
        parameterList += list.get(list.size()-1);
        return parameterList;
    }

}
