package org.xcolab.service.contest.domain.contestschedule;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestSchedule;
import org.xcolab.model.tables.records.ContestScheduleRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_SCHEDULE;

@Repository
public class ContestScheduleDaoImpl implements ContestScheduleDao{

    @Autowired
    private DSLContext dslContext;

    public ContestSchedule create(ContestSchedule contestSchedule) {

        ContestScheduleRecord ret = this.dslContext.insertInto(CONTEST_SCHEDULE)
                .set(CONTEST_SCHEDULE.NAME, contestSchedule.getName())
                .set(CONTEST_SCHEDULE.DESCRIPTION, contestSchedule.getDescription())
                .set(CONTEST_SCHEDULE.STATUS, contestSchedule.getStatus())
                .set(CONTEST_SCHEDULE.BASE_SCHEDULE_ID, contestSchedule.getBaseScheduleId())
                .returning(CONTEST_SCHEDULE.ID_)
                .fetchOne();
        if (ret != null) {
            contestSchedule.setId_(ret.getValue(CONTEST_SCHEDULE.ID_));
            return contestSchedule;
        } else {
            return null;
        }

    }

    public ContestSchedule get(Long id_) throws NotFoundException{

        final Record record =  this.dslContext.selectFrom(CONTEST_SCHEDULE)
                .where(CONTEST_SCHEDULE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ContestSchedule with id " + id_ + " does not exist");
        }
        return record.into(ContestSchedule.class);

    }

    public boolean update(ContestSchedule contestSchedule) {
        return dslContext.update(CONTEST_SCHEDULE)
                .set(CONTEST_SCHEDULE.NAME, contestSchedule.getName())
                .set(CONTEST_SCHEDULE.DESCRIPTION, contestSchedule.getDescription())
                .set(CONTEST_SCHEDULE.STATUS, contestSchedule.getStatus())
                .set(CONTEST_SCHEDULE.BASE_SCHEDULE_ID, contestSchedule.getBaseScheduleId())
                .where(CONTEST_SCHEDULE.ID_.eq(contestSchedule.getId_()))
                .execute() > 0;
    }

    @Override
    public List<ContestSchedule> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_SCHEDULE).getQuery();


        return query.fetchInto(ContestSchedule.class);
    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(CONTEST_SCHEDULE)
                .where(CONTEST_SCHEDULE.ID_.eq(id_))
                .execute();
    }


}
