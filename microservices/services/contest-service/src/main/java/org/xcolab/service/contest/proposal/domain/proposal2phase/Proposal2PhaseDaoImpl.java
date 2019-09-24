package org.xcolab.service.contest.proposal.domain.proposal2phase;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.tables.pojos.Proposal2Phase;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.Collections;
import java.util.List;

import static org.xcolab.model.Tables.CONTEST_PHASE;
import static org.xcolab.model.Tables.PROPOSAL;
import static org.xcolab.model.Tables.PROPOSAL2_PHASE;
import static org.xcolab.model.Tables.PROPOSAL_CONTEST_PHASE_ATTRIBUTE;

@Repository
public class Proposal2PhaseDaoImpl implements Proposal2PhaseDao {

    private final DSLContext dslContext;

    @Autowired
    public Proposal2PhaseDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}


    @Override
    public IProposal2Phase create(IProposal2Phase proposal2Phase) {
        this.dslContext.insertInto(PROPOSAL2_PHASE)
                .set(PROPOSAL2_PHASE.PROPOSAL_ID, proposal2Phase.getProposalId())
                .set(PROPOSAL2_PHASE.CONTEST_PHASE_ID, proposal2Phase.getContestPhaseId())
                .set(PROPOSAL2_PHASE.VERSION_FROM, proposal2Phase.getVersionFrom())
                .set(PROPOSAL2_PHASE.VERSION_TO, proposal2Phase.getVersionTo())
                .set(PROPOSAL2_PHASE.SORT_WEIGHT, proposal2Phase.getSortWeight())
                .set(PROPOSAL2_PHASE.AUTOPROMOTE_CANDIDATE, proposal2Phase.isAutopromoteCandidate())
                .execute();
        return proposal2Phase;
    }

    @Override
    public IProposal2Phase getByProposalIdContestPhaseId(Long proposalId, Long contestPhaseId) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(PROPOSAL2_PHASE)
                .where(PROPOSAL2_PHASE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("Proposal2Phase with id " + proposalId + " does not exist");
        }
        return record.into(Proposal2Phase.class);
    }

    @Override
    public boolean update(IProposal2Phase proposal2Phase) {
        return dslContext.update(PROPOSAL2_PHASE)
                .set(PROPOSAL2_PHASE.PROPOSAL_ID, proposal2Phase.getProposalId())
                .set(PROPOSAL2_PHASE.CONTEST_PHASE_ID, proposal2Phase.getContestPhaseId())
                .set(PROPOSAL2_PHASE.VERSION_FROM, proposal2Phase.getVersionFrom())
                .set(PROPOSAL2_PHASE.VERSION_TO, proposal2Phase.getVersionTo())
                .set(PROPOSAL2_PHASE.SORT_WEIGHT, proposal2Phase.getSortWeight())
                .set(PROPOSAL2_PHASE.AUTOPROMOTE_CANDIDATE, proposal2Phase.isAutopromoteCandidate())
                .where(PROPOSAL2_PHASE.PROPOSAL_ID.eq(proposal2Phase.getProposalId()))
                .and(PROPOSAL2_PHASE.CONTEST_PHASE_ID.eq(proposal2Phase.getContestPhaseId()))
                .execute() > 0;
    }

    @Override
    public List<IProposal2Phase> findByGiven(Long proposalId, Long contestPhaseId, Integer version) {
        final SelectQuery<Record> query = dslContext.select()
                .from(PROPOSAL2_PHASE).getQuery();
        if (proposalId != null) {
            query.addConditions(PROPOSAL2_PHASE.PROPOSAL_ID.eq(proposalId));
        }
        if (contestPhaseId != null) {
            query.addConditions(PROPOSAL2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId));
        }
        if (version != null) {
            query.addConditions(PROPOSAL2_PHASE.VERSION_FROM.le(version).and(
                    PROPOSAL2_PHASE.VERSION_TO.ge(version).or(PROPOSAL2_PHASE.VERSION_TO.eq(-1))));
        }

        Result<Record> records = query.fetch();
        if (records != null && !records.isEmpty()) {
            return records.into(Proposal2Phase.class);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<IProposal2Phase> findByContestAndProposal(Long proposalId, Long contestId) {
        final SelectQuery<Record> query = dslContext.select(PROPOSAL2_PHASE.fields())
                .from(PROPOSAL2_PHASE)
                .join(CONTEST_PHASE).on(CONTEST_PHASE.ID.eq(PROPOSAL2_PHASE.CONTEST_PHASE_ID))
                .getQuery();

        query.addConditions(PROPOSAL2_PHASE.PROPOSAL_ID.eq(proposalId));

        query.addConditions(CONTEST_PHASE.CONTEST_ID.eq(contestId));

        Result<Record> records = query.fetch();
        if (records != null && !records.isEmpty()) {
            return records.into(Proposal2Phase.class);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Integer getProposalCountForActiveContestPhase(Long contestPhaseId) {
        final SelectQuery<Record1<Long>> query = dslContext.select(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.PROPOSAL_ID)
                .from(PROPOSAL_CONTEST_PHASE_ATTRIBUTE)
                .where(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NAME.eq("VISIBLE")
                    .and(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.NUMERIC_VALUE.eq(0L))
                    .and(PROPOSAL_CONTEST_PHASE_ATTRIBUTE.CONTEST_PHASE_ID.eq(contestPhaseId)))
                .getQuery();

        SelectQuery<Record1<Integer>> query2 = dslContext.selectCount()
                .from(PROPOSAL2_PHASE)
                .join(PROPOSAL).on(PROPOSAL.ID.eq(PROPOSAL2_PHASE.PROPOSAL_ID))
                .where(PROPOSAL2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId)
                    .and(PROPOSAL2_PHASE.PROPOSAL_ID.notIn(query))
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
        return dslContext.deleteFrom(PROPOSAL2_PHASE)
                .where(PROPOSAL2_PHASE.PROPOSAL_ID.eq(proposalId))
                .and(PROPOSAL2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .execute();
    }
}
