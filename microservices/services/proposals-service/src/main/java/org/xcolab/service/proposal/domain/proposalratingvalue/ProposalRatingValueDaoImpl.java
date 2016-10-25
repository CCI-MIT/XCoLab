package org.xcolab.service.proposal.domain.proposalratingvalue;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalRatingValue;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import static org.xcolab.model.Tables.PROPOSAL_RATING_VALUE;

@Repository
public class ProposalRatingValueDaoImpl implements ProposalRatingValueDao {

    @Autowired
    private DSLContext dslContext;

    public ProposalRatingValue get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_RATING_VALUE)
                .where(PROPOSAL_RATING_VALUE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalRatingValue with id " + id_ + " does not exist");
        }
        return record.into(ProposalRatingValue.class);

    }
}
