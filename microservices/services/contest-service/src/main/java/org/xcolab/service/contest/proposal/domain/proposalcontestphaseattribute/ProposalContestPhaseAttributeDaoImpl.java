package org.xcolab.service.contest.proposal.domain.proposalcontestphaseattribute;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.model.tables.records.ProposalContestPhaseAttributeRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_CONTEST_PHASE_ATTRIBUTE;

@Repository
public class ProposalContestPhaseAttributeDaoImpl implements ProposalContestPhaseAttributeDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalContestPhaseAttributeDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IProposalContestPhaseAttribute create(IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        ProposalContestPhaseAttributeRecord ret = this.dslContext.insertInto(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.PROPOSAL_ID, proposalContestPhaseAttribute.getProposalId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.CONTEST_PHASE_ID, proposalContestPhaseAttribute.getContestPhaseId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NAME, proposalContestPhaseAttribute.getName())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ADDITIONAL_ID, proposalContestPhaseAttribute.getAdditionalId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NUMERIC_VALUE, proposalContestPhaseAttribute.getNumericValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.STRING_VALUE, proposalContestPhaseAttribute.getStringValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.REAL_VALUE, proposalContestPhaseAttribute.getRealValue())
                .returning(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID)
                .fetchOne();
        if (ret != null) {
            proposalContestPhaseAttribute.setId(ret.getValue(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID));
            return proposalContestPhaseAttribute;
        } else {
            return null;
        }
    }

    @Override
    public IProposalContestPhaseAttribute get(Long id) throws NotFoundException {
        final Record record =  this.dslContext.selectFrom(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ProposalContestPhaseAttribute with id " + id + " does not exist");
        }
        return record.into(ProposalContestPhaseAttribute.class);
    }

    @Override
    public IProposalContestPhaseAttribute getByProposalIdContestPhaseIdName(Long proposalId, Long contestPhaseId, String name) throws NotFoundException {
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
        final Record record = query.fetchOne();
        if(record == null){
            return null;
        }
        return record.into(ProposalContestPhaseAttribute.class);
    }
    @Override
    public List<IProposalContestPhaseAttribute> findByGiven(Long proposalId, Long contestPhaseId, String name) {
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
    public int delete(Long id) {
        return dslContext.deleteFrom(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID.eq(id))
                .execute();
    }

    @Override
    public boolean update(IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return dslContext.update(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ADDITIONAL_ID, proposalContestPhaseAttribute.getAdditionalId())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NUMERIC_VALUE, proposalContestPhaseAttribute.getNumericValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.STRING_VALUE, proposalContestPhaseAttribute.getStringValue())
                .set(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.REAL_VALUE, proposalContestPhaseAttribute.getRealValue())
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.ID.eq(proposalContestPhaseAttribute.getId()))
                .execute() > 0;
    }
}
