package org.xcolab.service.proposal.domain.proposalratingtype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalRatingType;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import static org.xcolab.model.Tables.PROPOSAL_RATING_TYPE;

@Repository
public class ProposalRatingTypeDaoImpl implements ProposalRatingTypeDao {
    @Autowired
    private DSLContext dslContext;

    public ProposalRatingType get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_RATING_TYPE)
                .where(PROPOSAL_RATING_TYPE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalRatingType with id " + id_ + " does not exist");
        }
        return record.into(ProposalRatingType.class);

    }
}
