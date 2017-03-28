package org.xcolab.client.proposals;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalDto;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.ProposalVersionDto;
import org.xcolab.client.proposals.pojo.group.GroupDto;
import org.xcolab.client.proposals.pojo.group.Group_;
import org.xcolab.client.proposals.pojo.tiers.ProposalReference;
import org.xcolab.client.proposals.pojo.tiers.ProposalReferenceDto;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalClient {

    private static final Map<RestService, ProposalClient> instances = new HashMap<>();

    private final RestService proposalService;

    private final RestResource<ProposalDto, Long> proposalResource;
    private final RestResource<Long, Long> proposalThreadIdResource;
    private final RestResource<Long, Long> proposalIdResource;
    private final RestResource1<ProposalVersionDto, Long> proposalVersionResource;
    private final RestResource1<ProposalReferenceDto, Long> proposalReferenceResource;

    private final RestResource<GroupDto,Long> groupResource;

    //TODO: methods that use this should be in the service!
    private final ContestClient contestClient;

    private final ActivitiesClient activitiesClient;

    private ProposalClient(RestService proposalService) {
        this.proposalService = proposalService;

        proposalResource = new RestResource1<>(
                proposalService, "proposals", ProposalDto.TYPES);
        proposalIdResource = new RestResource1<>(
                proposalService, "proposalIds", new TypeProvider<>(Long.class,
                new ParameterizedTypeReference<List<Long>>() {
                })
        );
        proposalThreadIdResource = new RestResource1<>(
                proposalService, "proposalThreadIds", new TypeProvider<>(Long.class,
                new ParameterizedTypeReference<List<Long>>() {
                })
        );
        proposalVersionResource = new RestResource1<>(proposalService,
                "proposalVersions", ProposalVersionDto.TYPES);
        proposalReferenceResource = new RestResource1<>(proposalService,
                "proposalReference", ProposalReferenceDto.TYPES);

        groupResource = new RestResource1<>(proposalService, "groups", GroupDto.TYPES);

        contestClient = ContestClient.fromService(proposalService.withServiceName(CoLabService.CONTEST.getServiceName()));



        RestService activitiesService  = proposalService.withServiceName(CoLabService.ACTIVITY.getServiceName());
         activitiesClient = ActivitiesClient.fromService(activitiesService);

    }

    public static ProposalClient fromService(RestService proposalService) {
        return instances
                .computeIfAbsent(proposalService, k -> new ProposalClient(proposalService));
    }

    public Proposal createProposal(Proposal proposal) {
        return proposalResource
                .create(new ProposalDto(proposal))
                .execute().toPojo(proposalService);
    }

    public List<Proposal> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    public List<Proposal> listProposals(int start, int limit, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        return DtoUtil.toPojos(proposalResource.list()
                .addRange(start, limit)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("visible", visible)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("ribbon", ribbon)
                .execute(), proposalService);
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

    public List<Long> listThreadIds(int start, int limit, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        return proposalThreadIdResource.list()
                .addRange(start, limit)
                .optionalQueryParam("contestId", contestId)
                .optionalQueryParam("visible", visible)
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

    public List<Proposal> getProposalsInContest(Long contestPK) {
        ContestPhase cp = contestClient.getActivePhase(contestPK);

        return listProposals(0, Integer.MAX_VALUE, null, true, cp.getContestPhasePK(), null);
    }

    public List<Member> getProposalMembers(Long proposalId) {
        return proposalResource.service(proposalId, "allMembers", Member.TYPES.getTypeReference())
                .getList();
    }

    public void removeUserFromProposalTeam(Long proposalId, Long memberUserId) {
        proposalResource.service(proposalId, "removeUserFromProposalTeam", Boolean.class)
                .queryParam("memberUserId", memberUserId)
                .delete();
    }

    public Boolean isUserInProposalTeam(Long proposalId, Long memberUserId) {
        return proposalResource.service(proposalId, "isUserInProposalTeam", Boolean.class)
                .queryParam("memberUserId", memberUserId)
                .get();
    }

    public  List<Proposal> getProposalsByCurrentContests(List<Long> contestTypeIds, List<Long> contestTierIds,
            String filterText) {
        return DtoUtil.toPojos(proposalResource.service("getProposalsByCurrentContests", ProposalDto.TYPES.getTypeReference())
                .queryParam("contestTypeIds", convertListToGetParameter(contestTypeIds,"contestTypeIds"))
                .queryParam("contestTierIds", convertListToGetParameter(contestTierIds,"contestTierIds"))
                .queryParam("filterText", filterText)
                .getList(), proposalService);
    }

    public List<Proposal> getActiveProposalsInContestPhase(Long contestPhaseId, CacheName cacheName) {
        return DtoUtil.toPojos(proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .optionalQueryParam("visible", true)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .withCache(CacheKeys.withClass(ProposalDto.class)
                        .withParameter("contestPhaseId", contestPhaseId).asList(), cacheName)
                .execute(), proposalService);
    }

    public List<Proposal> getActiveProposalsInContestPhase(Long contestPhaseId) {
        return getActiveProposalsInContestPhase(contestPhaseId, CacheName.PROPOSAL_LIST);
    }

    public Proposal createProposal(long authorId, long contestPhaseId, boolean publishActivity) {
        return proposalResource.service("createProposal", ProposalDto.class)
                .queryParam("authorId", authorId)
                .queryParam("contestPhaseId", contestPhaseId)
                .queryParam("publishActivity", publishActivity)
                .post().toPojo(proposalService);
    }

    public List<Proposal> getContestIntegrationRelevantSubproposals(Long proposalId) {
        return DtoUtil.toPojos(proposalResource.service(proposalId, "contestIntegrationRelevantSubproposal",
                ProposalDto.TYPES.getTypeReference()).getList(), proposalService);
    }

    public List<Proposal> getLinkingProposalsForUser(long userId) {
        final List<Proposal> userProposals = getMemberProposals(userId);
        List<Proposal> linkingProposals = new ArrayList<>();
        for (Proposal proposal : userProposals) {
            linkingProposals.addAll(getLinkingProposals(proposal.getProposalId()));
        }
        return linkingProposals;
    }

    public List<Proposal> getMemberProposals(Long userId) {
        return DtoUtil.toPojos(proposalResource.service("getMemberProposals", ProposalDto.TYPES.getTypeReference())
                .queryParam("userId", userId)
                .getList(), proposalService);
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

    public Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
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
                    .executeChecked().toPojo(proposalService);
        } catch (EntityNotFoundException e) {
            throw new ProposalNotFoundException(proposalId);
        }
    }

    public List<ProposalReference> getProposalReference(Long proposalId, Long subProposalId) {
        return DtoUtil.toPojos(proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute(), proposalService);
    }

    public List<Proposal> getSubproposals(Long proposalId, Boolean includeProposalsInSameContest) {
        return DtoUtil.toPojos(proposalResource
                .service(proposalId, "getSubproposals", ProposalDto.TYPES.getTypeReference())
                .queryParam("includeProposalsInSameContest", includeProposalsInSameContest)
                .getList(), proposalService);
    }

    public Integer getProposalMaterializedPoints(Long proposalId) {
        return proposalResource.service(proposalId, "materializedPoints", Integer.class).get();
    }

    public Integer getNumberOfProposalsForJudge(Long userId, Long contestPhaseId) {
        return proposalResource.service("numberOfProposalsForJudge", Integer.class)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();

    }

    public ProposalReference getProposalReferenceByProposalIdSubProposalId(Long proposalId,
            Long subProposalId) {
        List<ProposalReference> list = DtoUtil.toPojos(proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute(), proposalService);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public boolean updateProposal(Proposal proposal) {
        return proposalResource
                .update(new ProposalDto(proposal), proposal.getProposalId())
                .cacheKey(CacheKeys.withClass(ProposalDto.class)
                                .withParameter("proposalId", proposal.getProposalId())
                                .withParameter("includeDeleted", false).build())
                .execute();
    }

    public boolean deleteProposal(long proposalId) {
        return proposalResource.delete(proposalId).execute();
    }

    public ProposalVersion getProposalVersionByProposalIdVersion(Long proposalId, Integer version) {
        return proposalVersionResource.service("getByProposalIdVersion", ProposalVersionDto.class)
                .queryParam("proposalId", proposalId)
                .queryParam("version", version)
                .get()
                .toPojo(proposalService);
    }
    public List<ProposalVersion> getProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId, int start, int end) {
        return DtoUtil.toPojos(proposalVersionResource.service("getGroupedVersionsByContest", ProposalVersionDto.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("contestId", contestId)
                .queryParam("start", start)
                .queryParam("end", end)
                .getList(),proposalService);

    }
    public Integer countProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId) {
        return proposalVersionResource.service("countGroupedVersionsByContest", Integer.class)
                .queryParam("proposalId", proposalId)
                .queryParam("contestId", contestId)
                .get();
    }
    public Integer countProposalVersions(Long proposalId) {
        return proposalVersionResource.service("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public ProposalVersion getProposalVersionByProposal(Long proposalId) {
        return proposalVersionResource.service("getByProposalIdVersion", ProposalVersionDto.class)
                .queryParam("proposalId", proposalId)
                .get()
                .toPojo(proposalService);
    }

    public List<ProposalVersion> getAllProposalVersions(Long proposalId) {
        return DtoUtil.toPojos(proposalVersionResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute(), proposalService);
    }

    public ContestType getContestTypeFromProposalId(Long proposalId) {
        try {
            Contest contest = getCurrentContestForProposal(proposalId);
            return contestClient.getContestType(contest.getContestTypeId());
        } catch (ContestNotFoundException e) {
            throw ReferenceResolutionException.toObject(Contest.class, "")
                    .fromObject(Proposal.class, proposalId);
        }
    }

    public Contest getCurrentContestForProposal(Long proposalId) throws ContestNotFoundException {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        ContestPhase contestPhase = contestClient.getContestPhase(contestPhaseId);
        return contestClient.getContest(contestPhase.getContestPK());

    }

    public Long getLatestContestPhaseIdInProposal(Long proposalId) {
        return proposalResource.service(proposalId, "getLatestContestPhaseIdInProposal", Long.class)
                .get();
    }

    public Contest getLatestContestInProposal(Long proposalId) throws ContestNotFoundException {
        return contestClient
                .getContest(getLatestContestPhaseInProposal(proposalId).getContestPK());
    }


    public ContestPhase getLatestContestPhaseInProposal(Long proposalId) {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        return contestClient.getContestPhase(contestPhaseId);
    }


    public void populateTableWithProposal(long proposalId) {
        proposalReferenceResource.service("populateTableWithProposal", Boolean.class)
                .queryParam("proposalId", proposalId)
                .get();

    }

    public boolean isMemberSubscribedToProposal(long proposalId, long userId) {
        return activitiesClient.isSubscribedToActivity(userId,
                ActivityEntryType.PROPOSAL.getPrimaryTypeId(), proposalId, 0, "");
    }

    public void subscribeMemberToProposal(long proposalId, long userId) {

        subscribeMemberToProposal(proposalId, userId, false);
    }

    private void subscribeMemberToProposal(long proposalId, long userId, boolean automatic) {
        activitiesClient.addSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);
        Proposal proposal = getProposal(proposalId);
        activitiesClient.addSubscription(userId, ActivityEntryType.DISCUSSION, proposal.getDiscussionId(), null);
    }

    public void unsubscribeMemberFromProposal(long proposalId, long userId) {
        unsubscribeMemberFromProposal(proposalId, userId, false);

    }

    private void unsubscribeMemberFromProposal(long proposalId, long userId, boolean automatic) {
        activitiesClient.deleteSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);
        Proposal proposal = getProposal(proposalId);
        activitiesClient.deleteSubscription(userId, ActivityEntryType.DISCUSSION, proposal.getDiscussionId(), null);
    }
    public  Group_ createGroup(Group_ group) {
        return groupResource.create(new GroupDto(group)).execute().toPojo(proposalService);
    }

    public boolean updateGroup(Group_ group) {
        return groupResource.update(new GroupDto(group), group.getGroupId()).execute();
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
