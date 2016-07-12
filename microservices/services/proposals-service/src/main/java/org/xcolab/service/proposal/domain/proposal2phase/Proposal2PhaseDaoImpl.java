package org.xcolab.service.proposal.domain.proposal2phase;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.model.tables.records.Proposal2PhaseRecord;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_2_PHASE;

@Repository
public class Proposal2PhaseDaoImpl implements Proposa2PhaseDao {

    @Autowired
    private DSLContext dslContext;

    /*

	Proposal2PhaseLocalServiceUtil.create

    * */

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

    /*

    Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal

	Proposal2PhaseLocalServiceUtil.getProposal2Phase
	Proposal2PhaseLocalServiceUtil.getByProposalId
    * */
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
    //Proposal2PhaseLocalServiceUtil.updateProposal2Phase
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

/*
    Lists
    Proposal2PhaseLocalServiceUtil.getByContestPhaseId
	Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal
	Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId
	Proposal2PhaseLocalServiceUtil.getProposal2Phases
	Proposal2PhaseLocalServiceUtil.getLatestContestPhaseInContest

* */
    public List<Proposal2Phase> findByGiven(Long proposalId, Long contestPhaseId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_2_PHASE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        return query.fetchInto(Proposal2Phase.class);
    }

    //Proposal2PhaseLocalServiceUtil.deleteProposal2Phase
    public int delete(Long proposalId, Long contestPhaseId) {
        return dslContext.deleteFrom(PROPOSAL_2_PHASE)
                .where(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .execute();
    }


}
