package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalVoteClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final ProposalVoteClient client =
            ProposalVoteClient.fromService(proposalService);

    public static ProposalVoteClient getClient() {
        return client;
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
