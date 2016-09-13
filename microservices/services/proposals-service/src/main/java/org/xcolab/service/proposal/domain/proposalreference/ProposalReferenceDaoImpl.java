package org.xcolab.service.proposal.domain.proposalreference;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalReference;
import static org.xcolab.model.Tables.PROPOSAL_REFERENCE;

import java.util.List;

@Repository
public class ProposalReferenceDaoImpl implements ProposalReferenceDao {


    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ProposalReference> findByGiven(Long proposalId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_REFERENCE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_REFERENCE.PROPOSAL_ID.eq(proposalId));
        }
        return query.fetchInto(ProposalReference.class);
    }

}
