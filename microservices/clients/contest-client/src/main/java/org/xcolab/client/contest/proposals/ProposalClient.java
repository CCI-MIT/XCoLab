package org.xcolab.client.contest.proposals;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalDto;
import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public final class ProposalClient {

    private final RestResource<ProposalDto, Long> proposalResource = null; // proposals

    //TODO COLAB-2594: rethink these endpoints
    private final RestResource<Long, Long> proposalThreadIdResource = null; // proposalThreadIds

    //TODO COLAB-2594: rethink these endpoints
    private final RestResource<Long, Long> proposalIdResource = null; // proposalIds

    private final RestResource1<ProposalVersionWrapper, Long> proposalVersionResource = null; // proposalVersions
    private final RestResource1<IProposalReference, Long> proposalReferenceResource = null; // proposalReferences

    //TODO COLAB-2600: methods that use this should be in the service!
    private final ContestClient contestClient;

    private final ActivitiesClient activitiesClient;

    public ProposalClient() {
        contestClient = ContestClientUtil.getClient();
        activitiesClient = ActivitiesClientUtil.getClient();
    }

    public ProposalWrapper createProposal(ProposalWrapper proposal) {
        return proposalResource
                .create(new ProposalDto(proposal))
                .execute().toProposal();
    }

    public List<ProposalWrapper> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    public List<ProposalWrapper> listProposals(int start, int limit, Long contestId, Boolean visible,
        Long contestPhaseId, Integer ribbon) {
        return listProposals(start, limit, null, contestId, null,
            null, visible, contestPhaseId, ribbon);
    }

    public List<ProposalWrapper> listProposalsInActiveContests() {
        return ProposalDto.toProposals(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("visible", true)
                .queryParam("contestPrivate", false)
                .queryParam("contestActive", true)
                .withCache(CacheName.PROPOSAL_LIST)
                .execute());
    }

    public List<ProposalWrapper> listProposalsInCompletedContests(List<Integer> ribbons) {
        return ProposalDto.toProposals(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .queryParam("visible", true)
                .queryParam("contestPrivate", false)
                .queryParam("contestActive", false)
                .optionalQueryParam("ribbon", ribbons)
                .withCache(CacheName.PROPOSAL_LIST_CLOSED)
                .execute());
    }

    public List<ProposalWrapper> listProposals(int start, int limit, String filterText, Long contestId,
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

    public List<ProposalWrapper> getProposalsInPublicContests(List<Long> contestTypeIds,
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

    public List<ProposalWrapper> getProposalsInContestPhase(Long contestPhaseId) {
        return listProposals(0, Integer.MAX_VALUE, null, true, contestPhaseId, null);
    }

    public List<ProposalWrapper> getAllProposals() {
        return listProposals(0, Integer.MAX_VALUE, null, true, null, null);
    }

    public List<ProposalWrapper> getProposalsInContest(Long contestId) {
        ContestPhaseWrapper cp = contestClient.getActivePhase(contestId);

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

        ActivitiesClient activityClient = ActivitiesClientUtil.getClient();
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

    public List<ProposalWrapper> getActiveProposalsInContestPhase(Long contestPhaseId, CacheName cacheName) {
        return ProposalDto.toProposals(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .optionalQueryParam("visible", true)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .withCache(CacheKeys.withClass(ProposalDto.class)
                        .withParameter("contestPhaseId", contestPhaseId).asList(), cacheName)
                .execute());
    }

    public List<ProposalWrapper> getActiveProposalsInContestPhase(Long contestPhaseId) {
        return getActiveProposalsInContestPhase(contestPhaseId, CacheName.PROPOSAL_LIST);
    }

    public ProposalWrapper createProposal(long authorUserId, long contestPhaseId, boolean publishActivity) {
        return proposalResource.collectionService("createProposal", ProposalWrapper.class)
                .queryParam("authorUserId", authorUserId)
                .queryParam("contestPhaseId", contestPhaseId)
                .queryParam("publishActivity", publishActivity)
                .post();
    }

    public List<ProposalWrapper> getContestIntegrationRelevantSubproposals(Long proposalId) {
        return ProposalDto.toProposals(proposalResource.elementService(proposalId, "contestIntegrationRelevantSubproposal",
                ProposalDto.TYPES.getTypeReference()).getList());
    }

    public List<ProposalWrapper> getLinkingProposalsForUser(long userId) {
        final List<ProposalWrapper> userProposals = getMemberProposals(userId);
        List<ProposalWrapper> linkingProposals = new ArrayList<>();
        for (ProposalWrapper proposal : userProposals) {
            linkingProposals.addAll(getLinkingProposals(proposal.getId()));
        }
        return linkingProposals;
    }

    public List<ProposalWrapper> getMemberProposals(Long userId) {
        return ProposalDto.toProposals(proposalResource.collectionService("getMemberProposals", ProposalDto.TYPES.getTypeReference())
                .queryParam("userId", userId)
                .getList());
    }

    public List<ProposalWrapper> getLinkingProposals(long proposalId) {
        List<IProposalReference> proposalReferences = getProposalReference(null, proposalId);
        List<ProposalWrapper> linkingProposals = new ArrayList<>();
        for (IProposalReference proposalReference : proposalReferences) {
            try {
                final ProposalWrapper proposal = getProposal(proposalReference.getProposalId());
                if (!linkingProposals.contains(proposal)) {
                    linkingProposals.add(proposal);
                }
            } catch (ProposalNotFoundException ignored) {

            }
        }
        return linkingProposals;
    }

    public ProposalWrapper getProposalByThreadId(long threadId) {
        final ProposalDto proposalDto = proposalResource.list()
                .queryParam("threadId", threadId)
                .executeWithResult()
                .getOneIfExists();
        if (proposalDto != null) {
            return proposalDto.toProposal();
        }
        throw new ProposalNotFoundException("No proposal with threadId = " + threadId);
    }

    public ProposalWrapper getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
    }

    public void invalidateProposalCache(long proposalId) {
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(ProposalWrapper.class)
                .withParameter("proposalId", proposalId)
                .withParameter("includeDeleted", false).build(), CacheName.MISC_REQUEST);
    }

    public ProposalWrapper getProposal(long proposalId, boolean includeDeleted)
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

    public List<IProposalReference> getProposalReference(Long proposalId, Long subProposalId) {
        return proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute();
    }

    public List<ProposalWrapper> getSubproposals(Long proposalId, Boolean includeProposalsInSameContest) {
        return ProposalDto.toProposals(proposalResource
                .elementService(proposalId, "getSubproposals", ProposalDto.TYPES.getTypeReference())
                .queryParam("includeProposalsInSameContest", includeProposalsInSameContest)
                .getList());
    }


    public List<ProposalWrapper> getLinkingProposalsForProposalId(Long proposalId) {
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

    public IProposalReference getProposalReferenceByProposalIdSubProposalId(Long proposalId,
            Long subProposalId) {
        List<IProposalReference> list = proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public boolean updateProposal(ProposalWrapper proposal) {
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

    public ProposalVersionWrapper getProposalVersionByProposalIdVersion(Long proposalId, Integer version) {
        return proposalVersionResource.collectionService("getByProposalIdVersion", ProposalVersionWrapper.class)
                .queryParam("proposalId", proposalId)
                .queryParam("version", version)
                .get()
                ;
    }
    public List<ProposalVersionWrapper> getProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId, int start, int end) {
        return proposalVersionResource.collectionService("getGroupedVersionsByContest", ProposalVersionWrapper.TYPES.getTypeReference())
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

    public ProposalVersionWrapper getProposalVersionByProposal(Long proposalId) {
        return proposalVersionResource.collectionService("getByProposalIdVersion", ProposalVersionWrapper.class)
                .queryParam("proposalId", proposalId)
                .get()
                ;
    }

    public List<ProposalVersionWrapper> getAllProposalVersions(Long proposalId) {
        return proposalVersionResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public ContestType getContestTypeFromProposalId(Long proposalId) {
        try {
            ContestWrapper contest = getCurrentContestForProposal(proposalId);
            return StaticAdminContext.getContestTypeClient()
                    .getContestType(contest.getContestTypeId());
        } catch (ContestNotFoundException e) {
            throw ReferenceResolutionException.toObject(ContestWrapper.class, "")
                    .fromObject(ProposalWrapper.class, proposalId);
        }
    }

    public ContestWrapper getCurrentContestForProposal(Long proposalId) throws ContestNotFoundException {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        ContestPhaseWrapper contestPhase = contestClient.getContestPhase(contestPhaseId);
        return contestClient.getContest(contestPhase.getContestId());

    }

    public Long getLatestContestPhaseIdInProposal(Long proposalId) {
        return proposalResource.elementService(proposalId, "getLatestContestPhaseIdInProposal", Long.class)
                .get();
    }

    public ContestWrapper getLatestContestInProposal(Long proposalId) throws ContestNotFoundException {
        return contestClient
                .getContest(getLatestContestPhaseInProposal(proposalId).getContestId());
    }


    public ContestPhaseWrapper getLatestContestPhaseInProposal(Long proposalId) {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        return contestClient.getContestPhase(contestPhaseId);
    }


    public void populateTableWithProposal(long proposalId) {
        proposalReferenceResource.collectionService("populateTableWithProposal", Boolean.class)
                .queryParam("proposalId", proposalId)
                .get();

    }

    public boolean isMemberSubscribedToProposal(long proposalId, long userId) {
        return activitiesClient.isSubscribedToActivity(userId,
                ActivityCategory.PROPOSAL, proposalId);
    }

    public void subscribeMemberToProposal(long proposalId, long userId) {

        subscribeMemberToProposal(proposalId, userId, false);
    }

    private void subscribeMemberToProposal(long proposalId, long userId, boolean automatic) {
        activitiesClient.addSubscription(userId, ActivityCategory.PROPOSAL, proposalId, null);
    }

    public void unsubscribeMemberFromProposal(long proposalId, long userId) {
        unsubscribeMemberFromProposal(proposalId, userId, false);

    }

    private void unsubscribeMemberFromProposal(long proposalId, long userId, boolean automatic) {
        activitiesClient.deleteSubscription(userId, ActivityCategory.PROPOSAL, proposalId);
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
