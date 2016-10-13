package org.xcolab.service.proposal.domain.proposal2phase;

import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.*;

@Repository
public class Proposal2PhaseDaoImpl implements Proposal2PhaseDao {

    @Autowired
    private DSLContext dslContext;


    public Proposal2Phase create(Proposal2Phase proposal2Phase) {

        this.dslContext.insertInto(PROPOSAL_2_PHASE)
                .set(PROPOSAL_2_PHASE.PROPOSAL_ID, proposal2Phase.getProposalId())
                .set(PROPOSAL_2_PHASE.CONTEST_PHASE_ID, proposal2Phase.getContestPhaseId())
                .set(PROPOSAL_2_PHASE.VERSION_FROM, proposal2Phase.getVersionFrom())
                .set(PROPOSAL_2_PHASE.VERSION_TO, proposal2Phase.getVersionTo())
                .set(PROPOSAL_2_PHASE.SORT_WEIGHT, proposal2Phase.getSortWeight())
                .set(PROPOSAL_2_PHASE.AUTOPROMOTE_CANDIDATE, proposal2Phase.getAutopromoteCandidate())
                .execute();
        return proposal2Phase;

    }


    public Proposal2Phase getByProposalIdContestPhaseId(Long proposalId, Long contestPhaseId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PROPOSAL_2_PHASE)
                .where(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("Proposal2Phase with id " + proposalId + " does not exist");
        }
        return record.into(Proposal2Phase.class);

    }

    public boolean update(Proposal2Phase proposal2Phase) {
        return dslContext.update(PROPOSAL_2_PHASE)
                .set(PROPOSAL_2_PHASE.PROPOSAL_ID, proposal2Phase.getProposalId())
                .set(PROPOSAL_2_PHASE.CONTEST_PHASE_ID, proposal2Phase.getContestPhaseId())
                .set(PROPOSAL_2_PHASE.VERSION_FROM, proposal2Phase.getVersionFrom())
                .set(PROPOSAL_2_PHASE.VERSION_TO, proposal2Phase.getVersionTo())
                .set(PROPOSAL_2_PHASE.SORT_WEIGHT, proposal2Phase.getSortWeight())
                .set(PROPOSAL_2_PHASE.AUTOPROMOTE_CANDIDATE, proposal2Phase.getAutopromoteCandidate())
                .where(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposal2Phase.getProposalId()))
                .and(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(proposal2Phase.getContestPhaseId()))
                .execute() > 0;
    }


    public List<Proposal2Phase> findByGiven(Long proposalId, Long contestPhaseId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_2_PHASE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }

        Result<Record> records = query.fetch();
        if(records != null && records.size()> 0){
            return records.into(Proposal2Phase.class);
        }else{
            return null;
        }
    }

    public Integer getProposalCountForActiveContestPhase(Long contestPhasePK) {

        final SelectQuery<Record1<Long>> query = dslContext.select(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.PROPOSAL_ID)
                .from(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NAME.eq("VISIBLE"))
                .and(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NUMERIC_VALUE.eq(0l))
                .and(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.CONTEST_PHASE_ID.eq(contestPhasePK))
                .getQuery();

        SelectQuery<Record1<Integer>> query2 = dslContext.selectCount()
                .from(PROPOSAL_2_PHASE)
                .join(PROPOSAL).on(PROPOSAL.PROPOSAL_ID.eq(PROPOSAL_2_PHASE.PROPOSAL_ID))
                .where(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhasePK))
                .and(PROPOSAL_2_PHASE.PROPOSAL_ID.notIn(query))
                .and(PROPOSAL.VISIBLE.eq(true)).getQuery();

        if (query2 != null){
            return query2.fetchOne().into(Integer.class);
        }else{
            return new Integer(0);
        }

    }


    public int delete(Long proposalId, Long contestPhaseId) {
        return dslContext.deleteFrom(PROPOSAL_2_PHASE)
                .where(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .execute();
    }


}
