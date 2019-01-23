package org.xcolab.client.proposals;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.SupportedProposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
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

    private final RestResource1<ProposalSupporter, Long> proposalSupporterResource;
    private final RestResource<ProposalVote, Long> proposalVoteResource;
    private final RestResource<SupportedProposal, Long> supportedProposalsResource;

    public ProposalMemberRatingClient() {
        proposalSupporterResource = new RestResource1<>(ProposalResource.PROPOSAL_SUPPORTER,
                ProposalSupporter.TYPES);
        proposalVoteResource = new RestResource1<>(ProposalResource.PROPOSAL_VOTE,
                ProposalVote.TYPES);

        supportedProposalsResource = new RestResource1<>(ProposalResource.SUPPORTED_PROPOSALS,
                 SupportedProposal.TYPES);
    }

    public List<ProposalSupporter> getProposalSupporters(long proposalId) {
        return proposalSupporterResource.list()
            .withCache(CacheName.MISC_REQUEST)
            .queryParam("proposalId", proposalId)
            .execute();
    }

    public List<ProposalSupporter> getProposalSupportersByUserId(Long userId) {
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
        return proposalSupporterResource.<ProposalSupporter, Integer>collectionService("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .withCache(CacheKeys.withClass(ProposalSupporter.class)
                        .withParameter("proposalId", proposalId)
                        .asCount(), CacheName.MISC_REQUEST)
                .get();
    }

    public Integer getProposalSupportersCountCached(Long proposalId) {
        return proposalSupporterResource.<ProposalSupporter, Integer>collectionService("count", Integer.class)
            .optionalQueryParam("proposalId", proposalId)
            .withCache(CacheKeys.withClass(ProposalSupporter.class)
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
        ProposalSupporter supporter = new ProposalSupporter();
        supporter.setProposalId(proposalId);
        supporter.setUserId(userId);
        supporter.setCreatedAt(new Timestamp(new Date().getTime()));
        createProposalSupporter(supporter);

        if (publishActivity) {
            StaticActivityContext.getActivityClient()
                    .createActivityEntry(ProposalActivityType.SUPPORT_ADDED, userId, proposalId);
        }
    }

    public ProposalSupporter createProposalSupporter(ProposalSupporter proposalSupporter) {
        return proposalSupporterResource
                .create(new ProposalSupporter(proposalSupporter))
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
            return proposalVoteResource.<Proposal, Integer>collectionService("count", Integer.class)
                    .optionalQueryParam("contestPhaseId", contestPhaseId)
                    .queryParam("isValidOverride", true)
                    .withCache(CacheKeys.withClass(Proposal.class)
                            .withParameter("contestPhaseId", contestPhaseId)
                            .asCount(), CacheName.MEMBER_RATING)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public int countVotesByUserInPhase(long userId, long phaseId) {
        return proposalVoteResource.<ProposalVote, Integer>collectionService("count", Integer.class)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", phaseId)
                .get();
    }

    public Integer countProposalVotesInContestPhaseProposalId(long contestPhaseId, long proposalId,
            CacheName cacheName) {
        return proposalVoteResource.<ProposalVote, Integer>collectionService("count", Integer.class)
                .queryParam("contestPhaseId", contestPhaseId)
                .queryParam("proposalId", proposalId)
                .queryParam("isValidOverride", true)
                .withCache(CacheKeys.withClass(ProposalVote.class)
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

    public List<ProposalVote> getProposalVotesInPhase(long contestPhaseId) {
        return getProposalVotes(contestPhaseId, null, null);
    }

    public List<ProposalVote> getVotesByMember(long userId) {
        return proposalVoteResource.list()
                .queryParam("userId", userId)
                .execute();
    }

    public void invalidateVotesForMember(long userId, String reason) {
        final List<ProposalVote> votes = getVotesByMember(userId);
        votes.stream()
                .filter(ProposalVote::getIsValid)
                .forEach(vote -> {
                    vote.setIsValid(false);
                    vote.setLastValidationResult(reason);
                    updateProposalVote(vote);
                });
    }

    public List<ProposalVote> getProposalVotes(Long contestPhaseId, Long proposalId, Long userId) {
        return proposalVoteResource.list()
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public List<ProposalVote> getProposalVotesByUserInPhase(long userId, long contestPhaseId) {
        return getProposalVotes(contestPhaseId, null, userId);
    }

    public boolean updateProposalVote(ProposalVote proposalVote) {
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

    public ProposalVote addProposalVote(Long proposalId, Long contestPhaseId, Long userId,
            int value) {
        ProposalVote pv = new ProposalVote();
        pv.setProposalId(proposalId);
        pv.setContestPhaseId(contestPhaseId);
        pv.setUserId(userId);
        pv.setValue(value);
        pv.setIsValid(true);// should this default to true?
        return createProposalVote(pv);
    }
    public ProposalVote createProposalVote(ProposalVote proposalVote) {
        return proposalVoteResource.create(new ProposalVote(proposalVote)).execute()
                ;
    }

    public ProposalVote getProposalVoteByProposalIdUserId(Long proposalId, Long userId) {
        try {
            return proposalVoteResource
                    .collectionService("getProposalVoteByProposalIdUserId", ProposalVote.class)
                    .optionalQueryParam("proposalId", proposalId)
                    .optionalQueryParam("userId", userId)
                    .getChecked()
                    ;
        } catch (EntityNotFoundException ig) {
            return null;
        }
    }
}
