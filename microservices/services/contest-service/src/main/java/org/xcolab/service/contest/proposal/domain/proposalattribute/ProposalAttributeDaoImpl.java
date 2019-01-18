package org.xcolab.service.contest.proposal.domain.proposalattribute;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.model.tables.records.ProposalAttributeRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_ATTRIBUTE;

@Repository
public class ProposalAttributeDaoImpl implements ProposalAttributeDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public ProposalAttribute create(ProposalAttribute proposalAttribute) {

        ProposalAttributeRecord ret = this.dslContext.insertInto(PROPOSAL_ATTRIBUTE)
                .set(PROPOSAL_ATTRIBUTE.PROPOSAL_ID, proposalAttribute.getProposalId())
                .set(PROPOSAL_ATTRIBUTE.VERSION, proposalAttribute.getVersion())
                .set(PROPOSAL_ATTRIBUTE.NAME, proposalAttribute.getName())
                .set(PROPOSAL_ATTRIBUTE.ADDITIONAL_ID, proposalAttribute.getAdditionalId())
                .set(PROPOSAL_ATTRIBUTE.NUMERIC_VALUE, proposalAttribute.getNumericValue())
                .set(PROPOSAL_ATTRIBUTE.STRING_VALUE, proposalAttribute.getStringValue())
                .set(PROPOSAL_ATTRIBUTE.REAL_VALUE, proposalAttribute.getRealValue())
                .returning(PROPOSAL_ATTRIBUTE.ID)
                .fetchOne();
        if (ret != null) {
            proposalAttribute.setId(ret.getValue(PROPOSAL_ATTRIBUTE.ID));
            return proposalAttribute;
        } else {
            return null;
        }

    }

    @Override
    public ProposalAttribute get(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_ATTRIBUTE)
                .where(PROPOSAL_ATTRIBUTE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalAttribute with id " + id + " does not exist");
        }
        return record.into(ProposalAttribute.class);

    }

    @Override
    public boolean update(ProposalAttribute proposalAttribute) {
        return dslContext.update(PROPOSAL_ATTRIBUTE)
                .set(PROPOSAL_ATTRIBUTE.PROPOSAL_ID, proposalAttribute.getProposalId())
                .set(PROPOSAL_ATTRIBUTE.VERSION, proposalAttribute.getVersion())
                .set(PROPOSAL_ATTRIBUTE.NAME, proposalAttribute.getName())
                .set(PROPOSAL_ATTRIBUTE.ADDITIONAL_ID, proposalAttribute.getAdditionalId())
                .set(PROPOSAL_ATTRIBUTE.NUMERIC_VALUE, proposalAttribute.getNumericValue())
                .set(PROPOSAL_ATTRIBUTE.STRING_VALUE, proposalAttribute.getStringValue())
                .set(PROPOSAL_ATTRIBUTE.REAL_VALUE, proposalAttribute.getRealValue())
                .where(PROPOSAL_ATTRIBUTE.ID.eq(proposalAttribute.getId()))
                .execute() > 0;
    }

    @Override
    public List<ProposalAttribute> findByGiven(Long proposalId, String name, Long additionalId, Integer version) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_ATTRIBUTE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_ATTRIBUTE.PROPOSAL_ID.eq(proposalId));
        }
        if (name != null) {
            query.addConditions(PROPOSAL_ATTRIBUTE.NAME.eq(name));
        }
        if (additionalId != null) {
            query.addConditions(PROPOSAL_ATTRIBUTE.ADDITIONAL_ID.eq(additionalId));
        }
        if (version != null) {
            query.addConditions(PROPOSAL_ATTRIBUTE.VERSION.le(version));
        }
        return query.fetchInto(ProposalAttribute.class);
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(PROPOSAL_ATTRIBUTE)
                .where(PROPOSAL_ATTRIBUTE.ID.eq(id))
                .execute();
    }


}
