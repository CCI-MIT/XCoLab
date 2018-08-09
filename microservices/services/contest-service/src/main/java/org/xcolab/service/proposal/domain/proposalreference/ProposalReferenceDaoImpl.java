package org.xcolab.service.proposal.domain.proposalreference;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_REFERENCE;

@Repository
public class ProposalReferenceDaoImpl implements ProposalReferenceDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ProposalReference> findByGiven(Long proposalId, Long subProposalId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_REFERENCE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_REFERENCE.PROPOSAL_ID.eq(proposalId));
        }
        if (subProposalId != null) {
            query.addConditions(PROPOSAL_REFERENCE.SUB_PROPOSAL_ID.eq(subProposalId));
        }
        return query.fetchInto(ProposalReference.class);
    }

    @Override
    public ProposalReference get(Long proposalId, Long subProposalId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(PROPOSAL_REFERENCE)
                .where(PROPOSAL_REFERENCE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_REFERENCE.SUB_PROPOSAL_ID.eq(subProposalId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalReference with id " + proposalId + " does not exist");
        }
        return record.into(ProposalReference.class);

    }

    @Override
    public ProposalReference create(ProposalReference proposalReference) {

        this.dslContext.insertInto(PROPOSAL_REFERENCE)
                .set(PROPOSAL_REFERENCE.PROPOSAL_ID, proposalReference.getProposalId())
                .set(PROPOSAL_REFERENCE.SUB_PROPOSAL_ID, proposalReference.getSubProposalId())
                .set(PROPOSAL_REFERENCE.SECTION_ATTRIBUTE_ID, proposalReference.getSectionAttributeId())
                .execute();
        return proposalReference;

    }

    @Override
    public boolean update(ProposalReference proposalReference) {
        return dslContext.update(PROPOSAL_REFERENCE)
                .set(PROPOSAL_REFERENCE.SECTION_ATTRIBUTE_ID, proposalReference.getSectionAttributeId())
                .where(PROPOSAL_REFERENCE.PROPOSAL_ID.eq(proposalReference.getProposalId()))
                .and(PROPOSAL_REFERENCE.SUB_PROPOSAL_ID.eq(proposalReference.getSubProposalId()))
                .execute() > 0;
    }
    @Override
    public int delete(Long proposalId, Long subproposalId) {
        return dslContext.deleteFrom(PROPOSAL_REFERENCE)
                .where(PROPOSAL_REFERENCE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_REFERENCE.SUB_PROPOSAL_ID.eq(subproposalId))
                .execute();
    }


}
