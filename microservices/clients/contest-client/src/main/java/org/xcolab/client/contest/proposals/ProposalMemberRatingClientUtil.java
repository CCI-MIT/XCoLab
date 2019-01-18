package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.wrapper.SupportedProposal;
import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.util.http.caching.CacheName;

import java.util.List;

public final class ProposalMemberRatingClientUtil {

    private static final ProposalMemberRatingClient client = new ProposalMemberRatingClient();

    public static ProposalMemberRatingClient getClient() {
        return client;
    }

    public static List<IProposalSupporter> getProposalSupporters(
            Long proposalId) {
        return client.getProposalSupporters(proposalId);
    }

    public static List<IProposalSupporter> getProposalSupportersByUserId(
            Long userId) {
        return client.getProposalSupportersByUserId(userId);
    }

    public static List<SupportedProposal> getSupportedProposals(long userId) {
        return client.getSupportedProposals(userId);
    }

    public static Integer getProposalSupportersCount(Long proposalId) {
        return client.getProposalSupportersCount(proposalId);
    }

    public static Boolean isMemberProposalSupporter(Long proposalId, Long userId) {
        return client.isMemberProposalSupporter(proposalId, userId);
    }

    public static void addProposalSupporter(long proposalId, long userId) {
        client.addProposalSupporter(proposalId, userId);
    }

    public static void addProposalSupporter(long proposalId, long userId, boolean publishActivity) {
        client.addProposalSupporter(proposalId, userId, publishActivity);
    }

    public static IProposalSupporter createProposalSupporter(
            IProposalSupporter proposalSupporter) {
        return client.createProposalSupporter(proposalSupporter);
    }

    public static Boolean deleteProposalSupporter(Long proposalId, Long userId) {
        return client.deleteProposalSupporter(proposalId, userId);
    }

    public static Integer countProposalVotesInContestPhase(Long contestPhaseId) {
        return client.countProposalVotesInContestPhase(contestPhaseId);

    }

    public static Integer countProposalVotesInContestPhaseProposalId(long contestPhaseId,
            long proposalId, CacheName cacheName) {
        return client.countProposalVotesInContestPhaseProposalId(contestPhaseId,
                proposalId, cacheName);
    }

    public static Boolean hasUserVoted(Long proposalId, Long contestPhaseId, Long userId) {
        return client.hasUserVoted(proposalId, contestPhaseId, userId);
    }

    public static Boolean hasUserVoted(Long contestPhaseId, Long userId) {
        return client.hasUserVoted(contestPhaseId, userId);
    }

    public static boolean deleteProposalVote(long proposalId, long contestPhaseId, long userId) {
        return client.deleteProposalVote(proposalId, contestPhaseId, userId);
    }

    public static IProposalVote addProposalVote(Long proposalId, Long contestPhaseId, Long userId,
            int value) {
        return client.addProposalVote(proposalId,contestPhaseId, userId, value);
    }

    public static List<IProposalVote> getProposalVotesInPhase(
            Long contestPhaseId) {
        return client.getProposalVotesInPhase(contestPhaseId);
    }

    public static List<IProposalVote> getProposalVotes(
            Long contestPhaseId, Long proposalId, Long userId) {
        return client.getProposalVotes(contestPhaseId, proposalId, userId);
    }

    public static List<IProposalVote> getVotesByMember(long userId) {
        return client.getVotesByMember(userId);
    }

    public static void invalidateVotesForMember(long userId, String reason) {
        client.invalidateVotesForMember(userId, reason);
    }

    public static boolean updateProposalVote(IProposalVote proposalVote) {
        return client.updateProposalVote(proposalVote);
    }

    public static IProposalVote getProposalVoteByProposalIdUserId(
            Long proposalId, Long userId) {
        return client.getProposalVoteByProposalIdUserId(proposalId, userId);
    }
}
