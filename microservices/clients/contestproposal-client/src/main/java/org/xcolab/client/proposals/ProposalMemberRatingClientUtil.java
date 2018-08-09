package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.SupportedProposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public final class ProposalMemberRatingClientUtil {

    private static final ProposalMemberRatingClient client =
            ProposalMemberRatingClient.fromNamespace(ServiceNamespace.instance());

    public static ProposalMemberRatingClient getClient() {
        return client;
    }

    public static List<ProposalSupporter> getProposalSupporters(
            Long proposalId) {
        return client.getProposalSupporters(proposalId);
    }

    public static List<ProposalSupporter> getProposalSupportersByUserId(
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

    public static ProposalSupporter createProposalSupporter(
            ProposalSupporter proposalSupporter) {
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

    public static ProposalVote addProposalVote(Long proposalId, Long contestPhaseId, Long userId,
            int value) {
        return client.addProposalVote(proposalId,contestPhaseId, userId, value);
    }

    public static List<ProposalVote> getProposalVotesInPhase(
            Long contestPhaseId) {
        return client.getProposalVotesInPhase(contestPhaseId);
    }

    public static List<ProposalVote> getProposalVotes(
            Long contestPhaseId, Long proposalId, Long userId) {
        return client.getProposalVotes(contestPhaseId, proposalId, userId);
    }

    public static List<ProposalVote> getVotesByMember(long userId) {
        return client.getVotesByMember(userId);
    }

    public static void invalidateVotesForMember(long userId, String reason) {
        client.invalidateVotesForMember(userId, reason);
    }

    public static boolean updateProposalVote(ProposalVote proposalVote) {
        return client.updateProposalVote(proposalVote);
    }

    public static ProposalVote getProposalVoteByProposalIdUserId(
            Long proposalId, Long userId) {
        return client.getProposalVoteByProposalIdUserId(proposalId, userId);
    }
}
