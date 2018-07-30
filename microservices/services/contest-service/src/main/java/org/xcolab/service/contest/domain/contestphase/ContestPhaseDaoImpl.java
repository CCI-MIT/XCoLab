package org.xcolab.service.contest.domain.contestphase;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.records.ContestPhaseRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_PHASE;
import static org.xcolab.model.Tables.PROPOSAL;
import static org.xcolab.model.Tables.PROPOSAL_2_PHASE;

@Repository
public class ContestPhaseDaoImpl implements ContestPhaseDao {

    private final DSLContext dslContext;

    @Autowired
    public ContestPhaseDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public ContestPhase create(ContestPhase contestPhase) {

        ContestPhaseRecord record = this.dslContext.insertInto(CONTEST_PHASE)
                .set(CONTEST_PHASE.CONTEST_PK, contestPhase.getContestPK())
                .set(CONTEST_PHASE.CONTEST_PHASE_TYPE, contestPhase.getContestPhaseType())
                .set(CONTEST_PHASE.CONTEST_SCHEDULE_ID, contestPhase.getContestScheduleId())
                .set(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE, contestPhase.getContestPhaseAutopromote())
                .set(CONTEST_PHASE.PHASE_START_DATE, contestPhase.getPhaseStartDate())
                .set(CONTEST_PHASE.PHASE_END_DATE, contestPhase.getPhaseEndDate())
                .set(CONTEST_PHASE.CREATED, DSL.currentTimestamp())
                .set(CONTEST_PHASE.UPDATED, DSL.currentTimestamp())
                .returning(CONTEST_PHASE.CONTEST_PHASE_PK)
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve inserted id");
        } else {
            contestPhase.setContestPhasePK(record.getValue(CONTEST_PHASE.CONTEST_PHASE_PK));
            return contestPhase;
        }
    }

    @Override
    public boolean update(ContestPhase contestPhase) {
        return dslContext.update(CONTEST_PHASE)
                .set(CONTEST_PHASE.CONTEST_PK, contestPhase.getContestPK())
                .set(CONTEST_PHASE.CONTEST_PHASE_TYPE, contestPhase.getContestPhaseType())
                .set(CONTEST_PHASE.CONTEST_SCHEDULE_ID, contestPhase.getContestScheduleId())
                .set(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE, contestPhase.getContestPhaseAutopromote())
                .set(CONTEST_PHASE.PHASE_START_DATE, contestPhase.getPhaseStartDate())
                .set(CONTEST_PHASE.PHASE_END_DATE, contestPhase.getPhaseEndDate())
                .set(CONTEST_PHASE.UPDATED, DSL.currentTimestamp())
                .where(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhase.getContestPhasePK()))
                .execute() > 0;
    }


    @Override
    public boolean delete(Long contestPhasePK) {
        dslContext.deleteFrom(PROPOSAL_2_PHASE)
                .where(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhasePK))
                .execute();
        return dslContext.deleteFrom(CONTEST_PHASE)
                .where(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhasePK))
                .execute() > 0;
    }

    @Override
    public List<ContestPhase> findByPhaseAutopromote(String contestPhaseAutoPromote) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE).getQuery();

        query.addConditions(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE.eq(contestPhaseAutoPromote));

        query.addOrderBy(CONTEST_PHASE.PHASE_START_DATE.asc());
        return query.fetchInto(ContestPhase.class);
    }

    @Override
    public List<ContestPhase> findByGiven(Long contestPK, Long contestScheduleId,
            Long contestPhaseTypeId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE).getQuery();

        if (contestPK != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(contestPK));
        }

        if (contestScheduleId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_SCHEDULE_ID.eq(contestScheduleId));
            query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(0L));
        }

        if (contestPhaseTypeId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PHASE_TYPE.eq(contestPhaseTypeId));
        }

        query.addOrderBy(CONTEST_PHASE.PHASE_START_DATE.asc());
        return query.fetchInto(ContestPhase.class);
    }

    @Override
    public boolean isPhaseActive(ContestPhase contestPhase) {
        if (contestPhase.getPhaseStartDate() != null) {
            Date now = new Date();
            if (now.after(contestPhase.getPhaseStartDate())) {
                return contestPhase.getPhaseEndDate() == null
                        || now.before(contestPhase.getPhaseEndDate());
            }
        }
        return false;
    }

    //ContestPhaseLocalServiceUtil.getContestPhase
    @Override
    public Optional<ContestPhase> get(Long contestPhasePK) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(CONTEST_PHASE)
                .where(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhasePK))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestPhase.class));
    }

    @Override
    public boolean exists(Long contestPhasePK) {
        return dslContext.selectCount()
                .from(CONTEST_PHASE)
                .where(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhasePK))
                .fetchOne().into(Integer.class) > 0;
    }

    @Override
    public List<Long> getProposalDiscussionThreadsInPhase(long phaseId) {
        return dslContext.select(PROPOSAL.DISCUSSION_ID)
                .from(PROPOSAL_2_PHASE)
                .join(PROPOSAL).on(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(PROPOSAL.PROPOSAL_ID))
                .where(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(phaseId)
                        .and(PROPOSAL.VISIBLE.eq(true)))
                .fetch().into(Long.class);
    }

}
