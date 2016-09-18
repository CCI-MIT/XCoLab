package org.xcolab.service.proposal.domain.proposalcontestphaseattribute;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.model.tables.records.ProposalContestPhaseAttributeRecord;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_CONTEST_PHASE_ATTRIBUTE;

@Repository
public class ProposalContestPhaseAttributeDaoImpl implements ProposalContestPhaseAttributeDao {

    @Autowired
    private DSLContext dslContext;

    public ProposalContestPhaseAttribute create(ProposalContestPhaseAttribute proposalContestPhaseAttribute) {

        ProposalContestPhaseAttributeRecord ret = this.dslContext.insertInto(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.PROPOSAL_ID, proposalContestPhaseAttribute.getProposalId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.CONTEST_PHASE_ID, proposalContestPhaseAttribute.getContestPhaseId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NAME, proposalContestPhaseAttribute.getName())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ADDITIONAL_ID, proposalContestPhaseAttribute.getAdditionalId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NUMERIC_VALUE, proposalContestPhaseAttribute.getNumericValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.STRING_VALUE, proposalContestPhaseAttribute.getStringValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.REAL_VALUE, proposalContestPhaseAttribute.getRealValue())
                .returning(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID_)
                .fetchOne();
        if (ret != null) {
            proposalContestPhaseAttribute.setId_(ret.getValue(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID_));
            return proposalContestPhaseAttribute;
        } else {
            return null;
        }

    }

    public ProposalContestPhaseAttribute get(Long id_) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalContestPhaseAttribute with id " + id_ + " does not exist");
        }
        return record.into(ProposalContestPhaseAttribute.class);

    }

    public ProposalContestPhaseAttribute getByProposalIdContestPhaseIdName(Long proposalId, Long contestPhaseId, String name) throws NotFoundException {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_CONTEST_PHASE_ATTRIBUTE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        if (name != null) {
            query.addConditions(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NAME.eq(name));
        }
        return query.fetchOne().into(ProposalContestPhaseAttribute.class);
    }
    @Override
    public List<ProposalContestPhaseAttribute> findByGiven(Long proposalId, Long contestPhaseId, String name) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_CONTEST_PHASE_ATTRIBUTE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        if (name != null) {
            query.addConditions(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NAME.eq(name));
        }
        return query.fetchInto(ProposalContestPhaseAttribute.class);
    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID_.eq(id_))
                .execute();
    }
    @Override
    public boolean update(ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return dslContext.update(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ADDITIONAL_ID, proposalContestPhaseAttribute.getAdditionalId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NUMERIC_VALUE, proposalContestPhaseAttribute.getNumericValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.STRING_VALUE, proposalContestPhaseAttribute.getStringValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.REAL_VALUE, proposalContestPhaseAttribute.getRealValue())
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID_.eq(proposalContestPhaseAttribute.getId_()))
                .execute() > 0;
    }


}
