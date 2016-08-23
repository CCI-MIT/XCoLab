package org.xcolab.service.contest.domain.contestphase;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestPhase;
import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.model.tables.records.ContestPhaseRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.Date;
import java.util.List;

import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.CONTEST_PHASE;

@Repository
public class ContestPhaseDaoImpl implements ContestPhaseDao {

    @Autowired
    private DSLContext dslContext;


    public ContestPhase create(ContestPhase contestPhase) {

        ContestPhaseRecord ret = this.dslContext.insertInto(CONTEST_PHASE)
                .set(CONTEST_PHASE.CONTEST_PK, contestPhase.getContestPK())
                .set(CONTEST_PHASE.CONTEST_PHASE_TYPE, contestPhase.getContestPhaseType())
                .set(CONTEST_PHASE.CONTEST_SCHEDULE_ID, contestPhase.getContestScheduleId())
                .set(CONTEST_PHASE.FELLOW_SCREENING_ACTIVE, contestPhase.getFellowScreeningActive())
                .set(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE, contestPhase.getContestPhaseAutopromote())
                .set(CONTEST_PHASE.CONTEST_PHASE_DESCRIPTION_OVERRIDE, contestPhase.getContestPhaseDescriptionOverride())
                .set(CONTEST_PHASE.PHASE_ACTIVE_OVERRIDE, contestPhase.getPhaseActiveOverride())
                .set(CONTEST_PHASE.PHASE_INACTIVE_OVERRIDE, contestPhase.getPhaseInactiveOverride())
                .set(CONTEST_PHASE.PHASE_START_DATE, contestPhase.getPhaseStartDate())
                .set(CONTEST_PHASE.PHASE_END_DATE, contestPhase.getPhaseEndDate())
                .set(CONTEST_PHASE.PHASE_BUFFER_END_DATED, contestPhase.getPhaseBufferEndDated())
                .set(CONTEST_PHASE.NEXT_STATUS, contestPhase.getNextStatus())
                .set(CONTEST_PHASE.CREATED, contestPhase.getCreated())
                .set(CONTEST_PHASE.UPDATED, contestPhase.getUpdated())
                .set(CONTEST_PHASE.AUTHOR_ID, contestPhase.getAuthorId())
                .returning(CONTEST_PHASE.CONTEST_PHASE_PK)
                .fetchOne();
        if (ret != null) {
            contestPhase.setContestPhasePK(ret.getValue(CONTEST_PHASE.CONTEST_PHASE_PK));
            return contestPhase;
        } else {
            return null;
        }

    }


    public boolean update(ContestPhase contestPhase) {
        return dslContext.update(CONTEST_PHASE)
                .set(CONTEST_PHASE.CONTEST_PK, contestPhase.getContestPK())
                .set(CONTEST_PHASE.CONTEST_PHASE_TYPE, contestPhase.getContestPhaseType())
                .set(CONTEST_PHASE.CONTEST_SCHEDULE_ID, contestPhase.getContestScheduleId())
                .set(CONTEST_PHASE.FELLOW_SCREENING_ACTIVE, contestPhase.getFellowScreeningActive())
                .set(CONTEST_PHASE.CONTEST_PHASE_AUTOPROMOTE, contestPhase.getContestPhaseAutopromote())
                .set(CONTEST_PHASE.CONTEST_PHASE_DESCRIPTION_OVERRIDE, contestPhase.getContestPhaseDescriptionOverride())
                .set(CONTEST_PHASE.PHASE_ACTIVE_OVERRIDE, contestPhase.getPhaseActiveOverride())
                .set(CONTEST_PHASE.PHASE_INACTIVE_OVERRIDE, contestPhase.getPhaseInactiveOverride())
                .set(CONTEST_PHASE.PHASE_START_DATE, contestPhase.getPhaseStartDate())
                .set(CONTEST_PHASE.PHASE_END_DATE, contestPhase.getPhaseEndDate())
                .set(CONTEST_PHASE.PHASE_BUFFER_END_DATED, contestPhase.getPhaseBufferEndDated())
                .set(CONTEST_PHASE.NEXT_STATUS, contestPhase.getNextStatus())
                .set(CONTEST_PHASE.CREATED, contestPhase.getCreated())
                .set(CONTEST_PHASE.UPDATED, contestPhase.getUpdated())
                .set(CONTEST_PHASE.AUTHOR_ID, contestPhase.getAuthorId())
                .where(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhase.getContestPhasePK()))
                .execute() > 0;
    }


    public int delete(Long contestPhasePK) {
        return dslContext.deleteFrom(CONTEST_PHASE)
                .where(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhasePK))
                .execute();
    }
    /*
    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest
    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId
    ContestPhaseLocalServiceUtil.getPhasesForContest
    */

    public List<ContestPhase> findByGiven(Long contestPK, Long contestScheduleId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE).getQuery();

        if (contestPK != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(contestPK));
        }
        if (contestScheduleId != null) {
            query.addConditions(CONTEST_PHASE.CONTEST_SCHEDULE_ID.eq(contestScheduleId));
            query.addConditions(CONTEST_PHASE.CONTEST_PK.eq(0l));
        }
        query.addOrderBy(CONTEST_PHASE.PHASE_START_DATE.asc());
        return query.fetchInto(ContestPhase.class);
    }

    public boolean isPhaseActive(ContestPhase contestPhase) {
        if (contestPhase.getPhaseActiveOverride() != null) {
            if(contestPhase.getPhaseActiveOverride()) {
                return contestPhase.getPhaseActiveOverride();
            }
        }
        if (contestPhase.getPhaseInactiveOverride()!=null) {
            if(contestPhase.getPhaseInactiveOverride()) {
                return contestPhase.getPhaseInactiveOverride();
            }
        }
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
    public ContestPhase get(Long contestPhasePK) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(CONTEST_PHASE)
                .where(CONTEST_PHASE.CONTEST_PHASE_PK.eq(contestPhasePK))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ContestPhase with id " + contestPhasePK + " does not exist");
        }
        return record.into(ContestPhase.class);

    }

}
