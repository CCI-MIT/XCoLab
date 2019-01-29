package org.xcolab.service.contest.proposal.domain.proposalunversionedattribute;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.model.tables.records.ProposalUnversionedAttributeRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_UNVERSIONED_ATTRIBUTE;

@Repository
public class ProposalUnversionedAttributeDaoImpl implements ProposalUnversionedAttributeDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalUnversionedAttributeDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public ProposalUnversionedAttribute create(ProposalUnversionedAttribute proposalUnversionedAttribute) {
        final long additionalId = proposalUnversionedAttribute.getAdditionalId() != null
                ? proposalUnversionedAttribute.getAdditionalId() : 0;
        ProposalUnversionedAttributeRecord ret = this.dslContext.insertInto(PROPOSAL_UNVERSIONED_ATTRIBUTE)
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.ID, proposalUnversionedAttribute.getId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.PROPOSAL_ID, proposalUnversionedAttribute.getProposalId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.FIRST_AUTHOR_USER_ID, proposalUnversionedAttribute.getFirstAuthorUserId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.LAST_AUTHOR_USER_ID, proposalUnversionedAttribute.getLastAuthorUserId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.CREATED_AT, DSL.currentTimestamp())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.UPDATED_AT, DSL.currentTimestamp())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.NAME, proposalUnversionedAttribute.getName())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.ADDITIONAL_ID, additionalId)
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.NUMERIC_VALUE, proposalUnversionedAttribute.getNumericValue())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.STRING_VALUE, proposalUnversionedAttribute.getStringValue())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.REAL_VALUE, proposalUnversionedAttribute.getRealValue())
                .returning(PROPOSAL_UNVERSIONED_ATTRIBUTE.ID)
                .fetchOne();
        if (ret != null) {
            proposalUnversionedAttribute.setId(ret.getValue(PROPOSAL_UNVERSIONED_ATTRIBUTE.ID));
            return proposalUnversionedAttribute;
        } else {
            return null;
        }
    }

    @Override
    public ProposalUnversionedAttribute get(Long id) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(PROPOSAL_UNVERSIONED_ATTRIBUTE)
                .where(PROPOSAL_UNVERSIONED_ATTRIBUTE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalUnversionedAttribute with id " + id + " does not exist");
        }
        return record.into(ProposalUnversionedAttribute.class);
    }

    @Override
    public boolean update(ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return dslContext.update(PROPOSAL_UNVERSIONED_ATTRIBUTE)
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.ID, proposalUnversionedAttribute.getId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.PROPOSAL_ID, proposalUnversionedAttribute.getProposalId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.LAST_AUTHOR_USER_ID, proposalUnversionedAttribute.getLastAuthorUserId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.UPDATED_AT, DSL.currentTimestamp())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.NAME, proposalUnversionedAttribute.getName())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.ADDITIONAL_ID, proposalUnversionedAttribute.getAdditionalId())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.NUMERIC_VALUE, proposalUnversionedAttribute.getNumericValue())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.STRING_VALUE, proposalUnversionedAttribute.getStringValue())
                .set(PROPOSAL_UNVERSIONED_ATTRIBUTE.REAL_VALUE, proposalUnversionedAttribute.getRealValue())
                .where(PROPOSAL_UNVERSIONED_ATTRIBUTE.ID.eq(proposalUnversionedAttribute.getId()))
                .execute() > 0;
    }

    @Override
    public ProposalUnversionedAttribute getByProposalIdName(Long proposalId, String name) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_UNVERSIONED_ATTRIBUTE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_UNVERSIONED_ATTRIBUTE.PROPOSAL_ID.eq(proposalId));
        }
        if (name != null) {
            query.addConditions(PROPOSAL_UNVERSIONED_ATTRIBUTE.NAME.eq(name));
        }
        Record rec = query.fetchOne();
        if(rec == null){
            return null;
        }else {
            return rec.into(ProposalUnversionedAttribute.class);
        }
    }

    @Override
    public List<ProposalUnversionedAttribute> findByGiven(Long proposalId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_UNVERSIONED_ATTRIBUTE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_UNVERSIONED_ATTRIBUTE.PROPOSAL_ID.eq(proposalId));
        }
        return query.fetchInto(ProposalUnversionedAttribute.class);
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(PROPOSAL_UNVERSIONED_ATTRIBUTE)
                .where(PROPOSAL_UNVERSIONED_ATTRIBUTE.ID.eq(id))
                .execute();
    }
}
