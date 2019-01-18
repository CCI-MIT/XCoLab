package org.xcolab.client.contest.proposals;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.SupportedProposal;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalSupporter;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalVote;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class ProposalMemberRatingClient {

    private final RestResource1<IProposalSupporter, Long> proposalSupporterResource = null; // proposalSupporters
    private final RestResource<IProposalVote, Long> proposalVoteResource = null; // proposalVotes
    private final RestResource<SupportedProposal, Long> supportedProposalsResource = null; // supportedProposals

    public List<IProposalSupporter> getProposalSupporters(long proposalId) {
        return proposalSupporterResource.list()
            .withCache(CacheName.MISC_REQUEST)
            .queryParam("proposalId", proposalId)
            .execute();
    }

    public List<IProposalSupporter> getProposalSupportersByUserId(Long userId) {
        return proposalSupporterResource.list()
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public List<SupportedProposal> getSupportedProposals(long userId) {
        return supportedProposalsResource
                .list()
                .queryParam("userId", userId)
                .execute()
                .stream()
                    .map(sr -> new SupportedProposal(sr))
                    .collect(Collectors.toList());
    }

    public Integer getProposalSupportersCount(Long proposalId) {
        return proposalSupporterResource.<IProposalSupporter, Integer>collectionService("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .withCache(CacheKeys.withClass(IProposalSupporter.class)
                        .withParameter("proposalId", proposalId)
                        .asCount(), CacheName.MISC_REQUEST)
                .get();
    }

    public Integer getProposalSupportersCountCached(Long proposalId) {
        return proposalSupporterResource.<IProposalSupporter, Integer>collectionService("count", Integer.class)
            .optionalQueryParam("proposalId", proposalId)
            .withCache(CacheKeys.withClass(IProposalSupporter.class)
                .withParameter("proposalId", proposalId)
                .asCount(), CacheName.PROPOSAL_DETAILS)
            .get();
    }

    public Boolean isMemberProposalSupporter(Long proposalId, Long userId) {
        return proposalSupporterResource.collectionService("isMemberProposalSupporter", Boolean.class)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .get();
    }

    public void addProposalSupporter(long proposalId, long userId) {
        addProposalSupporter(proposalId, userId, true);
    }

    public void addProposalSupporter(long proposalId, long userId, boolean publishActivity) {
        IProposalSupporter supporter = new ProposalSupporter();
        supporter.setProposalId(proposalId);
        supporter.setUserId(userId);
        supporter.setCreatedAt(new Timestamp(new Date().getTime()));
        createProposalSupporter(supporter);

        ActivitiesClient activityClient = ActivitiesClientUtil.getClient();

        if (publishActivity) {
            activityClient.createActivityEntry(ProposalActivityType.SUPPORT_ADDED, userId,
                    proposalId);
        }
    }

    public IProposalSupporter createProposalSupporter(IProposalSupporter proposalSupporter) {
        return proposalSupporterResource
                .create(proposalSupporter)
                .execute();
    }

    public Boolean deleteProposalSupporter(Long proposalId, Long userId) {
        return proposalSupporterResource.collectionService("deleteProposalSupporter", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("userId", userId)
                .delete();
    }

    public Integer countProposalVotesInContestPhase(Long contestPhaseId) {
        try {
            return proposalVoteResource.<ProposalWrapper, Integer>collectionService("count", Integer.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .queryParam("isValidOverride", true)
                    .withCache(CacheKeys.withClass(ProposalWrapper.class)
                            .withParameter("contestPhaseId", contestPhaseId)
                            .asCount(), CacheName.MEMBER_RATING)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public int countVotesByUserInPhase(long userId, long phaseId) {
        return proposalVoteResource.<IProposalVote, Integer>collectionService("count", Integer.class)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", phaseId)
                .get();
    }

    public Integer countProposalVotesInContestPhaseProposalId(long contestPhaseId, long proposalId,
            CacheName cacheName) {
        return proposalVoteResource.<IProposalVote, Integer>collectionService("count", Integer.class)
                .queryParam("contestPhaseId", contestPhaseId)
                .queryParam("proposalId", proposalId)
                .queryParam("isValidOverride", true)
                .withCache(CacheKeys.withClass(IProposalVote.class)
                        .withParameter("contestPhaseId", contestPhaseId)
                        .withParameter("proposalId", proposalId)
                        .asCount(), cacheName)
                .get();
    }

    public Boolean hasUserVoted(Long proposalId, Long contestPhaseId, Long userId) {
        return proposalVoteResource.collectionService("hasUserVoted", Boolean.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }
    public  Boolean hasUserVoted(Long contestPhaseId, Long userId) {
        return proposalVoteResource.collectionService("hasUserVoted", Boolean.class)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("userId", userId)
                .get();
    }

    public List<IProposalVote> getProposalVotesInPhase(long contestPhaseId) {
        return getProposalVotes(contestPhaseId, null, null);
    }

    public List<IProposalVote> getVotesByMember(long userId) {
        return proposalVoteResource.list()
                .queryParam("userId", userId)
                .execute();
    }

    public void invalidateVotesForMember(long userId, String reason) {
        final List<IProposalVote> votes = getVotesByMember(userId);
        votes.stream()
                .filter(IProposalVote::getIsValid)
                .forEach(vote -> {
                    vote.setIsValid(false);
                    vote.setLastValidationResult(reason);
                    updateProposalVote(vote);
                });
    }

    public List<IProposalVote> getProposalVotes(Long contestPhaseId, Long proposalId, Long userId) {
        return proposalVoteResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public List<IProposalVote> getProposalVotesByUserInPhase(long userId, long contestPhaseId) {
        return getProposalVotes(contestPhaseId, null, userId);
    }

    public boolean updateProposalVote(IProposalVote proposalVote) {
        return proposalVoteResource.collectionService("updateVote", Boolean.class)
                .post(proposalVote);
    }

    public boolean deleteProposalVote(long proposalId, long contestPhaseId, long userId) {
        return proposalVoteResource.collectionService("deleteVote", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .delete();
    }

    public IProposalVote addProposalVote(Long proposalId, Long contestPhaseId, Long userId,
            int value) {
        IProposalVote pv = new ProposalVote();
        pv.setProposalId(proposalId);
        pv.setContestPhaseId(contestPhaseId);
        pv.setUserId(userId);
        pv.setValue(value);
        pv.setIsValid(true);// should this default to true?
        return createProposalVote(pv);
    }
    public IProposalVote createProposalVote(IProposalVote proposalVote) {
        return proposalVoteResource.create(proposalVote).execute()
                ;
    }

    public IProposalVote getProposalVoteByProposalIdUserId(Long proposalId, Long userId) {
        try {
            return proposalVoteResource
                    .collectionService("getProposalVoteByProposalIdUserId", IProposalVote.class)
                    .optionalQueryParam("proposalId", proposalId)
                    .optionalQueryParam("userId", userId)
                    .getChecked()
                    ;
        } catch (EntityNotFoundException ig) {
            return null;
        }
    }
}
