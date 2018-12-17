package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.phases.ProposalMoveHistory;
import org.xcolab.util.enums.proposal.MoveType;

import java.util.List;

public final class ProposalMoveClientUtil {

    private static final ProposalMoveClient client = new ProposalMoveClient();

    public static ProposalMoveClient getClient() {
        return client;
    }

    public static List<ProposalMoveHistory> getBySourceProposalIdContestId(
            Long sourceProposalId, Long sourceContestId) {
        return client.getBySourceProposalIdContestId(sourceProposalId, sourceContestId);
    }

    public static List<ProposalMoveHistory> getProposalMoveHistory(
            Long sourceProposalId, Long sourceContestId, Long targetProposalId,
            Long targetContestId) {
        return client.getProposalMoveHistory(sourceProposalId, sourceContestId, targetProposalId,
                targetContestId);
    }

    public static ProposalMoveHistory getByTargetProposalIdContestId(
            Long targetProposalId, Long targetContestId) {
        return client.getByTargetProposalIdContestId(targetProposalId, targetContestId);
    }

    public static ProposalMoveHistory createProposalMoveHistory(
            long proposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return client
                .createProposalMoveHistory(proposalId, srcContestId, targetContestId,
                        targetPhaseId, userId);
    }

    public static ProposalMoveHistory createProposalMoveHistory(
            long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
            long sourcePhaseId, long targetPhaseId, long userId, MoveType moveType) {
        return client
                .createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                        targetContestId,
                        targetPhaseId, userId, moveType);
    }

    public static ProposalMoveHistory createProposalMoveHistory(
            ProposalMoveHistory proposalMoveHistory) {
        return client.createProposalMoveHistory(proposalMoveHistory);
    }

    public static ProposalMoveHistory createCopyProposalMoveHistory(
            long proposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return client.createCopyProposalMoveHistory(proposalId, srcContestId, targetContestId,
                targetPhaseId, userId);
    }

    public static ProposalMoveHistory createForkProposalMoveHistory(
            long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return client.createForkProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                targetContestId, targetPhaseId, userId);
    }
}
