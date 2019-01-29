package org.xcolab.service.contest.proposal.domain.proposalmovehistory;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposalMoveHistory;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalMoveHistory;
import org.xcolab.model.tables.records.ProposalMoveHistoryRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_MOVE_HISTORY;

@Repository
public class ProposalMoveHistoryDaoImpl implements ProposalMoveHistoryDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalMoveHistoryDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}

    @Override
    public IProposalMoveHistory create(IProposalMoveHistory proposalMoveHistory) {
        ProposalMoveHistoryRecord ret = this.dslContext.insertInto(PROPOSAL_MOVE_HISTORY)
                .set(PROPOSAL_MOVE_HISTORY.SOURCE_PROPOSAL_ID, proposalMoveHistory.getSourceProposalId())
                .set(PROPOSAL_MOVE_HISTORY.SOURCE_CONTEST_ID, proposalMoveHistory.getSourceContestId())
                .set(PROPOSAL_MOVE_HISTORY.SOURCE_PHASE_ID, proposalMoveHistory.getSourcePhaseId())
                .set(PROPOSAL_MOVE_HISTORY.TARGET_PROPOSAL_ID, proposalMoveHistory.getTargetProposalId())
                .set(PROPOSAL_MOVE_HISTORY.TARGET_CONTEST_ID, proposalMoveHistory.getTargetContestId())
                .set(PROPOSAL_MOVE_HISTORY.TARGET_PHASE_ID, proposalMoveHistory.getTargetPhaseId())
                .set(PROPOSAL_MOVE_HISTORY.MOVING_USER_ID, proposalMoveHistory.getMovingUserId())
                .set(PROPOSAL_MOVE_HISTORY.MOVED_AT, proposalMoveHistory.getMovedAt())
                .set(PROPOSAL_MOVE_HISTORY.MOVE_TYPE, proposalMoveHistory.getMoveType())
                .returning(PROPOSAL_MOVE_HISTORY.ID)
                .fetchOne();
        if (ret != null) {
            proposalMoveHistory.setId(ret.getValue(PROPOSAL_MOVE_HISTORY.ID));
            return proposalMoveHistory;
        } else {
            return null;
        }
    }

    @Override
    public IProposalMoveHistory get(Long id) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(PROPOSAL_MOVE_HISTORY)
                .where(PROPOSAL_MOVE_HISTORY.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalMoveHistory with id " + id + " does not exist");
        }
        return record.into(ProposalMoveHistory.class);
    }

    @Override
    public List<IProposalMoveHistory> findByGiven(Long sourceProposalId, Long sourceContestId, Long targetProposalId, Long targetContestId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_MOVE_HISTORY).getQuery();

        if (sourceProposalId != null) {
            query.addConditions(PROPOSAL_MOVE_HISTORY.SOURCE_PROPOSAL_ID.eq(sourceProposalId));
        }
        if (sourceContestId != null) {
            query.addConditions(PROPOSAL_MOVE_HISTORY.SOURCE_CONTEST_ID.eq(sourceContestId));
        }
        if (targetProposalId != null) {
            query.addConditions(PROPOSAL_MOVE_HISTORY.TARGET_PROPOSAL_ID.eq(targetProposalId));
        }
        if (targetContestId != null) {
            query.addConditions(PROPOSAL_MOVE_HISTORY.TARGET_CONTEST_ID.eq(targetContestId));
        }
        return query.fetchInto(ProposalMoveHistory.class);
    }
}
