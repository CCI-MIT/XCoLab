package org.xcolab.client.proposals;


import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalReference;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public final class ProposalsClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource<Proposal, Long> proposalResource = new RestResource1<>(
            proposalService, "proposals", Proposal.TYPES);


    private static final RestResource1<ProposalVersion, Long> proposalVersionResource = new RestResource1<>(proposalService,
            "proposalVersions", ProposalVersion.TYPES);





    private static final RestResource1<ProposalReference, Long> proposalReferenceResource = new RestResource1<>(proposalService,
            "proposalReference", ProposalReference.TYPES);

    public static Proposal createProposal(Proposal proposal) {
        return proposalResource.create(proposal).execute();
    }

    public static List<Proposal> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    public static List<Proposal> getProposalsInContestPhase(Long contestPhaseId) {
        return listProposals(0, Integer.MAX_VALUE, null, true, contestPhaseId, null);
    }

    public static List<Proposal> getAllProposals() {
        return listProposals(0, Integer.MAX_VALUE, null, true, null, null);
    }


    public static List<Proposal> getProposalsInContest(Long contestPK) {
        ContestPhase cp = ContestClient.getActivePhase(contestPK);

        return listProposals(0, Integer.MAX_VALUE, null, true, cp.getContestPhasePK(), null);
    }

    public static List<Member> getProposalMembers(Long proposalId) {
        return proposalResource.service(proposalId, "allMembers", Member.TYPES.getTypeReference())
                .getList();
    }

    public static void removeUserFromProposalTeam(Long proposalId, Long memberUserId) {
        proposalResource.service(proposalId, "removeUserFromProposalTeam", Boolean.class)
                .queryParam("memberUserId", memberUserId)
                .delete();
    }

    public static Boolean isUserInProposalTeam(Long proposalId, Long memberUserId) {
        return proposalResource.service(proposalId, "isUserInProposalTeam", Boolean.class)
                .queryParam("memberUserId", memberUserId)
                .get();
    }


    public static List<Proposal> getProposalsByCurrentContests(List<Long> contestTierIds,
            String filterText) {
        return proposalResource.service("getProposalsByCurrentContests", Proposal.TYPES.getTypeReference())
                .queryParam("contestTierIds", 1)
                .queryParam("filterText", filterText)
                .getList();
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

    public static List<Proposal> getActiveProposalsInContestPhase(Long contestPhaseId) {
        return proposalResource.list()
                .addRange(0, Integer.MAX_VALUE)
                .optionalQueryParam("visible", true)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .execute();
    }


    public static Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
    }

    public static Proposal createProposal(long authorId, long contestPhaseId, boolean publishActivity) {
        return proposalResource.service("createProposal", Proposal.class)
                .queryParam("authorId", authorId)
                .queryParam("contestPhaseId", contestPhaseId)
                .queryParam("publishActivity", publishActivity)
                .post();
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
        return proposalResource.service(proposalId, "contestIntegrationRelevantSubproposal", Proposal.TYPES.getTypeReference()).getList();
    }

    public static Long getLatestContestPhaseIdInProposal(Long proposalId) {
        return proposalResource.service(proposalId, "getLatestContestPhaseIdInProposal", Long.class).get();
    }


    public static List<Proposal> getLinkingProposalsForUser(long userId) {
        final List<Proposal> userProposals = getMemberProposals(userId);
        List<Proposal> linkingProposals = new ArrayList<>();
        for (Proposal proposal : userProposals) {
            linkingProposals.addAll(getLinkingProposals(proposal.getProposalId()));
        }
        return linkingProposals;
    }


    public static List<Proposal> getMemberProposals(Long userId) {
        return proposalResource.service("getMemberProposals", Proposal.TYPES.getTypeReference())
                .queryParam("userId", userId)
                .getList();
    }

    public static List<Proposal> getSubproposals(Long proposalId, Boolean includeProposalsInSameContest) {
        return proposalResource.service(proposalId, "getSubproposals", Proposal.TYPES.getTypeReference())
                .queryParam("includeProposalsInSameContest", includeProposalsInSameContest).getList();
    }

    public static Integer getProposalMaterializedPoints(Long proposalId) {
        return proposalResource.service(proposalId, "materializedPoints", Integer.class).get();
    }

    public static Integer getNumberOfProposalsForJudge(Long userId, Long contestPhaseId) {
        return proposalResource.service("numberOfProposalsForJudge", Integer.class)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .get();

    }

    public static List<ProposalReference> getProposalReference(Long proposalId, Long subProposalId) {
        return proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute();
    }

    public static ProposalReference getProposalReferenceByProposalIdSubProposalId(Long proposalId, Long subProposalId) {
        List<ProposalReference> list = proposalReferenceResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("subProposalId", subProposalId)
                .execute();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static List<Proposal> getLinkingProposals(long proposalId) {
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

    public static boolean updateProposal(Proposal proposal) {
        return proposalResource.update(proposal, proposal.getProposalId()).execute();
    }

    public static boolean deleteProposal(long proposalId) {
        return proposalResource.delete(proposalId).execute();
    }


    public static ProposalVersion getProposalVersionByProposalIdVersion(Long proposalId, Integer version) {
        return proposalVersionResource.service("getByProposalIdVersion", ProposalVersion.class)
                .queryParam("proposalId", proposalId)
                .queryParam("version", version)
                .get();
    }

    public static Integer countProposalVersions(Long proposalId) {
        return proposalVersionResource.service("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }
    public static ProposalVersion getProposalVersionByProposal(Long proposalId) {
        return proposalVersionResource.service("getByProposalIdVersion", ProposalVersion.class)
                .queryParam("proposalId", proposalId)
                .get();
    }


    public static List<ProposalVersion> getAllProposalVersions(Long proposalId) {
        return proposalVersionResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }


    public static Contest getCurrentContestForProposal(Long proposalId) throws ContestNotFoundException {

        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        ContestPhase contestPhase = ContestClient.getContestPhase(contestPhaseId);
        return ContestClient.getContest(contestPhase.getContestPK());

    }

    public static ContestType getContestTypeFromProposalId(Long proposalId) {
        try {
            Contest contest = getCurrentContestForProposal(proposalId);
            return ContestClient.getContestType(contest.getContestTypeId());
        } catch (ContestNotFoundException ignored) {
            return null;
        }
    }

    public static Contest getLatestContestInProposal(Long proposalId) throws ContestNotFoundException {
        return ContestClient.getContest(getLatestContestPhaseInContest(proposalId).getContestPK());
    }


    public static ContestPhase getLatestContestPhaseInContest(Long proposalId) {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        return ContestClient.getContestPhase(contestPhaseId);
    }


    public static void populateTableWithProposal(long proposalId) {
        proposalReferenceResource.service("populateTableWithProposal", Boolean.class)
                .queryParam("proposalId", proposalId)
                .get();

    }

    public static boolean isMemberSubscribedToProposal(long proposalId, long userId) {
        return ActivitiesClient.isSubscribedToActivity(userId,
                ActivityEntryType.PROPOSAL.getPrimaryTypeId(), proposalId, 0, "");
    }

    public static void subscribeMemberToProposal(long proposalId, long userId) {

        subscribeMemberToProposal(proposalId, userId, false);
    }

    private static void subscribeMemberToProposal(long proposalId, long userId, boolean automatic) {
        ActivitiesClient.addSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);
    }

    public static void unsubscribeMemberFromProposal(long proposalId, long userId) {
        unsubscribeMemberFromProposal(proposalId, userId, false);
    }

    private static void unsubscribeMemberFromProposal(long proposalId, long userId, boolean automatic) {
        ActivitiesClient.deleteSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);
    }


}
