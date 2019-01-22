package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.IProposalMoveHistory;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalMoveHistory;
import org.xcolab.util.enums.proposal.MoveType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public final class ProposalMoveClient {

    private final RestResource1<IProposalMoveHistory, Long> proposalMoveHistoryResource = null; // proposalMoveHistories

    public List<IProposalMoveHistory> getBySourceProposalIdContestId(Long sourceProposalId,
            Long sourceContestId) {
        return getProposalMoveHistory(sourceProposalId, sourceContestId, null, null);
    }

    public List<IProposalMoveHistory> getProposalMoveHistory(Long sourceProposalId,
            Long sourceContestId, Long targetProposalId, Long targetContestId) {
        return proposalMoveHistoryResource.list()
                .withCache(CacheKeys.withClass(IProposalMoveHistory.class)
                                .withParameter("sourceProposalId", sourceProposalId)
                                .withParameter("sourceContestId", sourceContestId)
                                .withParameter("targetProposalId", targetProposalId)
                                .withParameter("targetContestId", targetContestId)
                                .asList(),
                        CacheName.MISC_MEDIUM)
                .optionalQueryParam("sourceProposalId", sourceProposalId)
                .optionalQueryParam("sourceContestId", sourceContestId)
                .optionalQueryParam("targetProposalId", targetProposalId)
                .optionalQueryParam("targetContestId", targetContestId)
                .execute();
    }

    public IProposalMoveHistory getByTargetProposalIdContestId(Long targetProposalId,
            Long targetContestId) {
        List<IProposalMoveHistory> list =
                getProposalMoveHistory(null, null, targetProposalId, targetContestId);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public IProposalMoveHistory createProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.MOVE_PERMANENTLY);
    }

    public IProposalMoveHistory createProposalMoveHistory(long srcProposalId, long targetProposalId,
            long srcContestId, long targetContestId,
            long targetPhaseId, long userId, MoveType moveType) {
        IProposalMoveHistory proposalMoveHistory = new ProposalMoveHistory();
        proposalMoveHistory.setSourceProposalId(srcProposalId);
        proposalMoveHistory.setTargetProposalId(targetProposalId);

        proposalMoveHistory.setSourceContestId(srcContestId);
        proposalMoveHistory.setTargetContestId(targetContestId);

        proposalMoveHistory.setTargetPhaseId(targetPhaseId);

        proposalMoveHistory.setMovingUserId(userId);
        proposalMoveHistory.setMovedAt(new Timestamp(new Date().getTime()));
        proposalMoveHistory.setMoveType(moveType.name());

        proposalMoveHistory = createProposalMoveHistory(proposalMoveHistory);

        return proposalMoveHistory;
    }

    public IProposalMoveHistory createProposalMoveHistory(IProposalMoveHistory proposalMoveHistory) {
        return proposalMoveHistoryResource
                .create(proposalMoveHistory)
                .execute();
    }

    public IProposalMoveHistory createCopyProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId,
            long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.COPY);
    }

    public IProposalMoveHistory createForkProposalMoveHistory(long srcProposalId,
            long targetProposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                targetContestId, targetPhaseId, userId, MoveType.FORK);
    }
}