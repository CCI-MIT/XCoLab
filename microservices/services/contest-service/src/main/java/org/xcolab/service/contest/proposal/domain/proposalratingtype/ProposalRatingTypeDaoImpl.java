package org.xcolab.service.contest.proposal.domain.proposalratingtype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalRatingType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_RATING_TYPE;

@Repository
public class ProposalRatingTypeDaoImpl implements ProposalRatingTypeDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalRatingTypeDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext is required");
        this.dslContext = dslContext;
    }

    @Override
    public IProposalRatingType get(Long id) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(PROPOSAL_RATING_TYPE)
                .where(PROPOSAL_RATING_TYPE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalRatingType with id " + id + " does not exist");
        }
        return record.into(ProposalRatingType.class);
    }

    @Override
    public List<IProposalRatingType> findByGiven(Integer judgeType, Boolean active) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_RATING_TYPE).getQuery();

        if (judgeType != null) {
            query.addConditions(PROPOSAL_RATING_TYPE.JUDGE_TYPE.eq(judgeType));
        }
        if (active != null) {
            query.addConditions(PROPOSAL_RATING_TYPE.IS_ACTIVE.eq(active));
        }

        return query.fetchInto(ProposalRatingType.class);
    }
}
