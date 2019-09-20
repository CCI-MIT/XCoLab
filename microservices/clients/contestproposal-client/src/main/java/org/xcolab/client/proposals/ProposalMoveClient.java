package org.xcolab.client.proposals;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.proposals.pojo.phases.ProposalMoveHistory;
import org.xcolab.util.enums.proposal.MoveType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public final class ProposalMoveClient {

    private final RestResource1<ProposalMoveHistory, Long> proposalMoveHistoryResource;

    public ProposalMoveClient() {
        proposalMoveHistoryResource = new RestResource1<>(ProposalResource.PROPOSAL_MOVE_HISTORY,
                ProposalMoveHistory.TYPES);
    }

    public List<ProposalMoveHistory> getBySourceProposalIdContestId(Long sourceProposalId,
            Long sourceContestId) {
        return getProposalMoveHistory(sourceProposalId, sourceContestId, null, null);
    }

    public List<ProposalMoveHistory> getProposalMoveHistory(Long sourceProposalId,
            Long sourceContestId, Long targetProposalId, Long targetContestId) {
        return proposalMoveHistoryResource.list()
                .withCache(CacheKeys.withClass(ProposalMoveHistory.class)
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

    public ProposalMoveHistory getByTargetProposalIdContestId(Long targetProposalId,
            Long targetContestId) {
        List<ProposalMoveHistory> list =
                getProposalMoveHistory(null, null, targetProposalId, targetContestId);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public ProposalMoveHistory createProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.MOVE_PERMANENTLY);
    }

    public ProposalMoveHistory createProposalMoveHistory(long srcProposalId, long targetProposalId,
            long srcContestId, long targetContestId,
            long targetPhaseId, long userId, MoveType moveType) {
        ProposalMoveHistory proposalMoveHistory = new ProposalMoveHistory();
        proposalMoveHistory.setSourceProposalId(srcProposalId);
        proposalMoveHistory.setTargetProposalId(targetProposalId);
        ContestPhase sourcePhase = ContestClientUtil.getActivePhase(srcContestId);
        proposalMoveHistory.setSourcePhaseId(sourcePhase.getId());
        proposalMoveHistory.setSourceContestId(srcContestId);
        proposalMoveHistory.setTargetContestId(targetContestId);

        proposalMoveHistory.setTargetPhaseId(targetPhaseId);

        proposalMoveHistory.setMovingUserId(userId);
        proposalMoveHistory.setMovedAt(new Timestamp(new Date().getTime()));
        proposalMoveHistory.setMoveType(moveType.name());

        proposalMoveHistory = createProposalMoveHistory(proposalMoveHistory);

        return proposalMoveHistory;
    }

    public ProposalMoveHistory createProposalMoveHistory(ProposalMoveHistory proposalMoveHistory) {
        return proposalMoveHistoryResource
                .create(new ProposalMoveHistory(proposalMoveHistory))
                .execute();
    }

    public ProposalMoveHistory createCopyProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId,
            long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.COPY);
    }

    public ProposalMoveHistory createForkProposalMoveHistory(long srcProposalId,
            long targetProposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                targetContestId, targetPhaseId, userId, MoveType.FORK);
    }
}
