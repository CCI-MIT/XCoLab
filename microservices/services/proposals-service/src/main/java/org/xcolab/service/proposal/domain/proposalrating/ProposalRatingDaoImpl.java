package org.xcolab.service.proposal.domain.proposalrating;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalRating;
import org.xcolab.model.tables.records.ProposalRatingRecord;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_RATING;
import static org.xcolab.model.Tables.PROPOSAL_RATING_TYPE;
import static org.xcolab.model.Tables.PROPOSAL_RATING_VALUE;

@Repository
public class ProposalRatingDaoImpl implements ProposalRatingDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public ProposalRating create(ProposalRating proposalRating) {

        ProposalRatingRecord ret = this.dslContext.insertInto(PROPOSAL_RATING)
                .set(PROPOSAL_RATING.PROPOSAL_ID, proposalRating.getProposalId())
                .set(PROPOSAL_RATING.CONTEST_PHASE_ID, proposalRating.getContestPhaseId())
                .set(PROPOSAL_RATING.USER_ID, proposalRating.getUserId())
                .set(PROPOSAL_RATING.RATING_VALUE_ID, proposalRating.getRatingValueId())
                .set(PROPOSAL_RATING.COMMENT_, proposalRating.getComment_())
                .set(PROPOSAL_RATING.COMMENT_ENABLED, proposalRating.getCommentEnabled())
                .set(PROPOSAL_RATING.OTHER_DATA_STRING, proposalRating.getOtherDataString())
                .set(PROPOSAL_RATING.ONLY_FOR_INTERNAL_USAGE, proposalRating.getOnlyForInternalUsage())
                .returning(PROPOSAL_RATING.ID_)
                .fetchOne();
        if (ret != null) {
            proposalRating.setId_(ret.getValue(PROPOSAL_RATING.ID_));
            return proposalRating;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(ProposalRating proposalRating) {
        return dslContext.update(PROPOSAL_RATING)
                .set(PROPOSAL_RATING.PROPOSAL_ID, proposalRating.getProposalId())
                .set(PROPOSAL_RATING.CONTEST_PHASE_ID, proposalRating.getContestPhaseId())
                .set(PROPOSAL_RATING.USER_ID, proposalRating.getUserId())
                .set(PROPOSAL_RATING.RATING_VALUE_ID, proposalRating.getRatingValueId())
                .set(PROPOSAL_RATING.COMMENT_, proposalRating.getComment_())
                .set(PROPOSAL_RATING.COMMENT_ENABLED, proposalRating.getCommentEnabled())
                .set(PROPOSAL_RATING.OTHER_DATA_STRING, proposalRating.getOtherDataString())
                .set(PROPOSAL_RATING.ONLY_FOR_INTERNAL_USAGE, proposalRating.getOnlyForInternalUsage())
                .where(PROPOSAL_RATING.ID_.eq(proposalRating.getId_()))
                .execute() > 0;
    }

    @Override
    public ProposalRating get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_RATING)
                .where(PROPOSAL_RATING.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalRating with id " + id_ + " does not exist");
        }
        return record.into(ProposalRating.class);

    }
    public List<ProposalRating> findByProposalIdJudgeTypeJudgeIdContestPhaseId(Long proposalId, Integer judgeType, Long contestPhaseId, Long userId) {

        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_RATING).getQuery();
        query.addJoin(PROPOSAL_RATING_VALUE);
        query.addJoin(PROPOSAL_RATING_TYPE);
        query.addConditions(PROPOSAL_RATING.PROPOSAL_ID.eq(proposalId));
        query.addConditions(PROPOSAL_RATING.RATING_VALUE_ID.eq(PROPOSAL_RATING_VALUE.ID_));
        query.addConditions(PROPOSAL_RATING_VALUE.RATING_TYPE_ID.eq(PROPOSAL_RATING_TYPE.ID_));
        query.addConditions(PROPOSAL_RATING_TYPE.JUDGE_TYPE.eq(judgeType));
        query.addConditions(PROPOSAL_RATING.CONTEST_PHASE_ID.eq(contestPhaseId));
        if (userId != null) {
            query.addConditions(PROPOSAL_RATING.USER_ID.eq(userId));
            query.addOrderBy(PROPOSAL_RATING.USER_ID.asc());
        }
        return query.fetchInto(ProposalRating.class);
    }


    @Override
    public List<ProposalRating> findByGiven(Long proposalId, Long contestPhaseId, Long userId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_RATING).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_RATING.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_RATING.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        if (userId != null) {
            query.addConditions(PROPOSAL_RATING.USER_ID.eq(userId));
        }
        return query.fetchInto(ProposalRating.class);
    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(PROPOSAL_RATING)
                .where(PROPOSAL_RATING.ID_.eq(id_))
                .execute();
    }
}
