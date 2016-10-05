package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.ProposalMoveHistory;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public final class ProposalMoveHistoryClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource1<ProposalMoveHistory, Long> proposalMoveHistoryResource = new RestResource1<>(proposalService,
            "proposalMoveHistories", ProposalMoveHistory.TYPES);

    public static List<ProposalMoveHistory> getBySourceProposalIdContestId(Long sourceProposalId, Long sourceContestId) {
        return getProposalMoveHistory(sourceProposalId, sourceContestId, null, null);
    }

    public static ProposalMoveHistory getByTargetProposalIdContestId(Long targetProposalId, Long targetContestId) {
        List<ProposalMoveHistory> list = getProposalMoveHistory(null, null, targetProposalId, targetContestId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static List<ProposalMoveHistory> getProposalMoveHistory(Long sourceProposalId, Long sourceContestId, Long targetProposalId, Long targetContestId) {
        return proposalMoveHistoryResource.list()
                .optionalQueryParam("sourceProposalId", sourceProposalId)
                .optionalQueryParam("sourceContestId", sourceContestId)
                .optionalQueryParam("targetProposalId", targetProposalId)
                .optionalQueryParam("targetContestId", targetContestId)
                .execute();
    }

    public static ProposalMoveHistory createProposalMoveHistory(ProposalMoveHistory proposalMoveHistory) {
        return proposalMoveHistoryResource.create(proposalMoveHistory).execute();
    }

    public static ProposalMoveHistory createProposalMoveHistory(long proposalId, long srcContestId, long targetContestId,
                                                                long srcPhaseId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId, srcPhaseId, targetPhaseId, userId, "MOVE_PERMANENTLY");
    }

    public static ProposalMoveHistory createCopyProposalMoveHistory(long proposalId, long srcContestId, long targetContestId,
                                                                    long srcPhaseId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId, srcPhaseId, targetPhaseId, userId, "COPY");
    }

    public static ProposalMoveHistory createForkProposalMoveHistory(long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
                                                                    long srcPhaseId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId, targetContestId, srcPhaseId, targetPhaseId, userId, "FORK");
    }

    public static ProposalMoveHistory createProposalMoveHistory(long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
                                                                long sourcePhaseId, long targetPhaseId, long userId, String moveType) {
        ProposalMoveHistory proposalMoveHistory = new ProposalMoveHistory();
        proposalMoveHistory.setSourceProposalId(srcProposalId);
        proposalMoveHistory.setTargetProposalId(targetProposalId);

        proposalMoveHistory.setSourceContestId(srcContestId);
        proposalMoveHistory.setTargetContestId(targetContestId);

        proposalMoveHistory.setSourcePhaseId(sourcePhaseId);
        proposalMoveHistory.setTargetPhaseId(targetPhaseId);

        proposalMoveHistory.setMovingUserId(userId);
        proposalMoveHistory.setMoveDate(new Timestamp(new Date().getTime()));
        proposalMoveHistory.setMoveType(moveType);

        proposalMoveHistory = createProposalMoveHistory(proposalMoveHistory);

        return proposalMoveHistory;
    }
}
