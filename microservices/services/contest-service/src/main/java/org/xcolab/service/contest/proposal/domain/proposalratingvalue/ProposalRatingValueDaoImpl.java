package org.xcolab.service.contest.proposal.domain.proposalratingvalue;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalRatingValue;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_RATING_VALUE;

@Repository
public class ProposalRatingValueDaoImpl implements ProposalRatingValueDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalRatingValueDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IProposalRatingValue get(Long id) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(PROPOSAL_RATING_VALUE)
                .where(PROPOSAL_RATING_VALUE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalRatingValue with id " + id + " does not exist");
        }
        return record.into(ProposalRatingValue.class);
    }

    @Override
    public List<IProposalRatingValue> findByGiven(Long ratingTypeId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_RATING_VALUE).getQuery();

        if (ratingTypeId != null) {
            query.addConditions(PROPOSAL_RATING_VALUE.RATING_TYPE_ID.eq(ratingTypeId));
        }
        return query.fetchInto(ProposalRatingValue.class);
    }
}
