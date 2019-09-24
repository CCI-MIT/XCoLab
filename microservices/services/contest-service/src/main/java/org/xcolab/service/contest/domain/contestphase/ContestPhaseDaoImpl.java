package org.xcolab.service.contest.domain.contestphase;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.model.tables.records.ContestPhaseRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_PHASE;
import static org.xcolab.model.Tables.PROPOSAL;
import static org.xcolab.model.Tables.PROPOSAL2_PHASE;

@Repository
public class ContestPhaseDaoImpl implements ContestPhaseDao {

    private final DSLContext dslContext;

    @Autowired
    public ContestPhaseDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public ContestPhaseWrapper create(ContestPhaseWrapper contestPhase) {
        ContestPhaseRecord record = this.dslContext.insertInto(CONTEST_PHASE)
                .set(CONTEST_PHASE.CONTEST_ID, contestPhase.getContestId())
                .set(CONTEST_PHASE.CONTEST_PHASE_TYPE_ID, contestPhase.getContestPhaseTypeId())
                .set(CONTEST_PHASE.CONTEST_SCHEDULE_ID, contestPhase.getContestScheduleId())
                .set(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE, contestPhase.getContestPhaseAutopromote())
                .set(CONTEST_PHASE.PHASE_START_DATE, contestPhase.getPhaseStartDate())
                .set(CONTEST_PHASE.PHASE_END_DATE, contestPhase.getPhaseEndDate())
                .set(CONTEST_PHASE.CREATED_AT, DSL.currentTimestamp())
                .set(CONTEST_PHASE.UPDATED_AT, DSL.currentTimestamp())
                .returning(CONTEST_PHASE.ID)
                .fetchOne();
        if (record == null) {
            throw new IllegalStateException("Could not retrieve inserted id");
        } else {
            contestPhase.setId(record.getValue(CONTEST_PHASE.ID));
            return contestPhase;
        }
    }

    @Override
    public boolean update(ContestPhaseWrapper contestPhase) {
        return dslContext.update(CONTEST_PHASE)
                .set(CONTEST_PHASE.CONTEST_ID, contestPhase.getContestId())
                .set(CONTEST_PHASE.CONTEST_PHASE_TYPE_ID, contestPhase.getContestPhaseTypeId())
                .set(CONTEST_PHASE.CONTEST_SCHEDULE_ID, contestPhase.getContestScheduleId())
                .set(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE, contestPhase.getContestPhaseAutopromote())
                .set(CONTEST_PHASE.PHASE_START_DATE, contestPhase.getPhaseStartDate())
                .set(CONTEST_PHASE.PHASE_END_DATE, contestPhase.getPhaseEndDate())
                .set(CONTEST_PHASE.UPDATED_AT, DSL.currentTimestamp())
                .where(CONTEST_PHASE.ID.eq(contestPhase.getId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(Long contestPhaseId) {
        dslContext.deleteFrom(PROPOSAL2_PHASE)
                .where(PROPOSAL2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId))
                .execute();
        return dslContext.deleteFrom(CONTEST_PHASE)
                .where(CONTEST_PHASE.ID.eq(contestPhaseId))
                .execute() > 0;
    }

    @Override
    public List<ContestPhaseWrapper> findByPhaseAutopromote(String contestPhaseAutoPromote) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE).getQuery();

        query.addConditions(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE.eq(contestPhaseAutoPromote));

        query.addOrderBy(CONTEST_PHASE.PHASE_START_DATE.asc());
        return query.fetchInto(ContestPhaseWrapper.class);
    }

    @Override
    public List<ContestPhaseWrapper> findByGiven(Long contestId, Long contestScheduleId,
            Long contestPhaseTypeId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE).getQuery();

        if (contestId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_ID.eq(contestId));
        }

        if (contestScheduleId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_SCHEDULE_ID.eq(contestScheduleId));
        }

        if (contestPhaseTypeId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PHASE_TYPE_ID.eq(contestPhaseTypeId));
        }

        query.addOrderBy(CONTEST_PHASE.PHASE_START_DATE.asc());
        return query.fetchInto(ContestPhaseWrapper.class);
    }

    @Override
    public boolean isPhaseActive(ContestPhaseWrapper contestPhase) {
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
    public Optional<ContestPhaseWrapper> get(Long contestPhaseId) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(CONTEST_PHASE)
                .where(CONTEST_PHASE.ID.eq(contestPhaseId))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestPhaseWrapper.class));
    }

    @Override
    public boolean exists(Long contestPhaseId) {
        return dslContext.selectCount()
                .from(CONTEST_PHASE)
                .where(CONTEST_PHASE.ID.eq(contestPhaseId))
                .fetchOne().into(Integer.class) > 0;
    }

    @Override
    public List<Long> getProposalDiscussionThreadsInPhase(long phaseId) {
        return dslContext.select(PROPOSAL.DISCUSSION_ID)
                .from(PROPOSAL2_PHASE)
                .join(PROPOSAL).on(PROPOSAL2_PHASE.PROPOSAL_ID.eq(PROPOSAL.ID))
                .where(PROPOSAL2_PHASE.CONTEST_PHASE_ID.eq(phaseId)
                        .and(PROPOSAL.VISIBLE.eq(true))
                        .and(PROPOSAL.DISCUSSION_ID.isNotNull()))
                .fetch().into(Long.class);
    }
}
