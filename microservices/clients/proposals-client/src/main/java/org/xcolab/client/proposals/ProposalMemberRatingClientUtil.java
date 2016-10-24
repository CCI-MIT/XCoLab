package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalMemberRatingClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final ProposalMemberRatingClient client =
            ProposalMemberRatingClient.fromService(proposalService);

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

    public static Integer getProposalSupportersCount(Long proposalId) {
        return client.getProposalSupportersCount(proposalId);
    }

    public static Boolean isMemberProposalSupporter(Long proposalId, Long memberId) {
        return client.isMemberProposalSupporter(proposalId, memberId);
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

    public static void removeProposalSupporter(long proposalId, long userId) {
        client.removeProposalSupporter(proposalId, userId);
    }

    public static Boolean deleteProposalSupporter(Long proposalId, Long memberId) {
        return client.deleteProposalSupporter(proposalId, memberId);
    }

    public static Integer countProposalVotesInContestPhase(Long contestPhaseId) {
        return client.countProposalVotesInContestPhase(contestPhaseId);

    }

    public static Integer countProposalVotesInContestPhaseProposalId(Long proposalId,
            Long contestPhaseId) {
        return client.countProposalVotesInContestPhaseProposalId(proposalId, contestPhaseId);
    }

    public static Boolean hasUserVoted(Long proposalId, Long contestPhaseId, Long memberId) {
        return client.hasUserVoted(proposalId, contestPhaseId, memberId);
    }

    public static Boolean hasUserVoted(Long contestPhaseId, Long memberId) {
        return client.hasUserVoted(contestPhaseId, memberId);
    }

    public static boolean deleteProposalVote(Long contestPhaseId, Long memberId) {
        return client.deleteProposalVote(contestPhaseId, memberId);
    }

    public static ProposalVote addProposalVote(Long proposalId, Long contestPhaseId, Long memberId){
        return client.addProposalVote(proposalId,contestPhaseId, memberId);
    }


    public static List<ProposalVote> getProposalVotes(
            Long contestPhaseId, Long proposalId, Long userId) {
        return client.getProposalVotes(contestPhaseId, proposalId, userId);
    }

    public static boolean updateProposalVote(ProposalVote proposalVote) {
        return client.updateProposalVote(proposalVote);
    }

    public static ProposalVote getProposalVoteByProposalIdUserId(
            Long proposalId, Long userId) {
        return client.getProposalVoteByProposalIdUserId(proposalId, userId);
    }
}
