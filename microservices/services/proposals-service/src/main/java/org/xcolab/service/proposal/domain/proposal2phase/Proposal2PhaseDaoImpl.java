package org.xcolab.service.proposal.domain.proposal2phase;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.Collections;
import java.util.List;

import static org.xcolab.model.Tables.CONTEST_PHASE;
import static org.xcolab.model.Tables.PROPOSAL;
import static org.xcolab.model.Tables.PROPOSAL_2_PHASE;
import static org.xcolab.model.Tables.PROPOSAL_CONTEST_PHASE_ATTRIBUTE;

@Repository
public class Proposal2PhaseDaoImpl implements Proposal2PhaseDao {

    private final DSLContext dslContext;

    @Autowired
    public Proposal2PhaseDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}


    @Override
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


    @Override
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

    @Override
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


    @Override
    public List<Proposal2Phase> findByGiven(Long proposalId, Long contestPhaseId, Integer version) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_2_PHASE).getQuery();

        if (proposalId != null) {
            query.addConditions(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        if (version != null) {
            query.addConditions(PROPOSAL_2_PHASE.VERSION_FROM.le(version).and(
                    PROPOSAL_2_PHASE.VERSION_TO.ge(version).or(PROPOSAL_2_PHASE.VERSION_TO.eq(-1))));
        }

        Result<Record> records = query.fetch();
        if (records != null && !records.isEmpty()) {
            return records.into(Proposal2Phase.class);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Proposal2Phase> findByContestAndProposal(Long proposalId, Long contestId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL_2_PHASE)
                .join(CONTEST_PHASE).on(CONTEST_PHASE.CONTEST_PHASE_PK.eq(PROPOSAL_2_PHASE.CONTEST_PHASE_ID))
                .getQuery();


        query.addConditions(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId));
        query.addConditions(PROPOSAL_2_PHASE.VERSION_TO.ne(-1));


        query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(contestId));

        Result<Record> records = query.fetch();
        if (records != null && !records.isEmpty()) {
            return records.into(Proposal2Phase.class);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Integer getProposalCountForActiveContestPhase(Long contestPhasePK) {

        final SelectQuery<Record1<Long>> query = dslContext.select(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.PROPOSAL_ID)
                .from(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NAME.eq("VISIBLE")
                    .and(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NUMERIC_VALUE.eq(0L))
                    .and(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.CONTEST_PHASE_ID.eq(contestPhasePK)))
                .getQuery();

        SelectQuery<Record1<Integer>> query2 = dslContext.selectCount()
                .from(PROPOSAL_2_PHASE)
                .join(PROPOSAL).on(PROPOSAL.PROPOSAL_ID.eq(PROPOSAL_2_PHASE.PROPOSAL_ID))
                .where(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhasePK)
                    .and(PROPOSAL_2_PHASE.PROPOSAL_ID.notIn(query))
                    .and(PROPOSAL.VISIBLE.eq(true)))
                .getQuery();

        if (query2 != null) {
            return query2.fetchOne().into(Integer.class);
        } else {
            return 0;
        }
    }


    @Override
    public int delete(Long proposalId, Long contestPhaseId) {
        return dslContext.deleteFrom(PROPOSAL_2_PHASE)
                .where(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .execute();
    }

}
