package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IProposalMoveHistory;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalMoveHistory;
import org.xcolab.util.enums.proposal.MoveType;
import org.xcolab.client.contest.StaticContestContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IProposalMoveClient {

    @GetMapping("/proposalMoveHistories")
    List<IProposalMoveHistory> getProposalMoveHistories(
            @RequestParam(value = "sourceProposalId", required = false) Long sourceProposalId,
            @RequestParam(value = "sourceContestId", required = false) Long sourceContestId,
            @RequestParam(value = "targetProposalId", required = false) Long targetProposalId,
            @RequestParam(value = "targetContestId", required = false) Long targetContestId);

    default List<IProposalMoveHistory> getBySourceProposalIdContestId(Long sourceProposalId,
            Long sourceContestId) {
        return getProposalMoveHistories(sourceProposalId, sourceContestId, null, null);
    }

    default IProposalMoveHistory getByTargetProposalIdContestId(Long targetProposalId,
            Long targetContestId) {
        List<IProposalMoveHistory> list =
                getProposalMoveHistories(null, null, targetProposalId, targetContestId);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @PostMapping("/proposalMoveHistories")
    IProposalMoveHistory createProposalMoveHistory(
            @RequestBody IProposalMoveHistory proposalMoveHistory);

    default IProposalMoveHistory createProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.MOVE_PERMANENTLY);
    }

    default IProposalMoveHistory createProposalMoveHistory(long srcProposalId,
            long targetProposalId, long srcContestId, long targetContestId, long targetPhaseId,
            long userId, MoveType moveType) {
        IProposalMoveHistory proposalMoveHistory = new ProposalMoveHistory();
        proposalMoveHistory.setSourceProposalId(srcProposalId);
        proposalMoveHistory.setTargetProposalId(targetProposalId);

        org.xcolab.client.contest.pojo.tables.pojos.ContestPhase
                sourcePhase = StaticContestContext.getContestClient().getActivePhase(srcContestId);
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

    default IProposalMoveHistory createCopyProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.COPY);
    }

    default IProposalMoveHistory createForkProposalMoveHistory(long srcProposalId,
            long targetProposalId, long srcContestId, long targetContestId, long targetPhaseId,
            long userId) {
        return createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                targetContestId, targetPhaseId, userId, MoveType.FORK);
    }
}
