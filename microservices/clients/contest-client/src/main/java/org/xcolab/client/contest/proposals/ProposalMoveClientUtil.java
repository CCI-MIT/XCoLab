package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.IProposalMoveHistory;
import org.xcolab.util.enums.proposal.MoveType;

import java.util.List;

public final class ProposalMoveClientUtil {

    private static final ProposalMoveClient client = new ProposalMoveClient();

    public static ProposalMoveClient getClient() {
        return client;
    }

    public static List<IProposalMoveHistory> getBySourceProposalIdContestId(
            Long sourceProposalId, Long sourceContestId) {
        return client.getBySourceProposalIdContestId(sourceProposalId, sourceContestId);
    }

    public static List<IProposalMoveHistory> getProposalMoveHistory(
            Long sourceProposalId, Long sourceContestId, Long targetProposalId,
            Long targetContestId) {
        return client.getProposalMoveHistory(sourceProposalId, sourceContestId, targetProposalId,
                targetContestId);
    }

    public static IProposalMoveHistory getByTargetProposalIdContestId(
            Long targetProposalId, Long targetContestId) {
        return client.getByTargetProposalIdContestId(targetProposalId, targetContestId);
    }

    public static IProposalMoveHistory createProposalMoveHistory(
            long proposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return client
                .createProposalMoveHistory(proposalId, srcContestId, targetContestId,
                        targetPhaseId, userId);
    }

    public static IProposalMoveHistory createProposalMoveHistory(
            long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
            long sourcePhaseId, long targetPhaseId, long userId, MoveType moveType) {
        return client
                .createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                        targetContestId,
                        targetPhaseId, userId, moveType);
    }

    public static IProposalMoveHistory createProposalMoveHistory(
            IProposalMoveHistory proposalMoveHistory) {
        return client.createProposalMoveHistory(proposalMoveHistory);
    }

    public static IProposalMoveHistory createCopyProposalMoveHistory(
            long proposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return client.createCopyProposalMoveHistory(proposalId, srcContestId, targetContestId,
                targetPhaseId, userId);
    }

    public static IProposalMoveHistory createForkProposalMoveHistory(
            long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return client.createForkProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                targetContestId, targetPhaseId, userId);
    }
}
