package org.xcolab.service.contest.domain.contestschedule;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ContestSchedule;
import org.xcolab.model.tables.records.ContestScheduleRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_SCHEDULE;

@Repository
public class ContestScheduleDaoImpl implements ContestScheduleDao{

    private final DSLContext dslContext;

    @Autowired
    public ContestScheduleDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public ContestSchedule create(ContestSchedule contestSchedule) {

        ContestScheduleRecord ret = dslContext.insertInto(CONTEST_SCHEDULE)
                .set(CONTEST_SCHEDULE.NAME, contestSchedule.getName())
                .set(CONTEST_SCHEDULE.DESCRIPTION, contestSchedule.getDescription())
                .set(CONTEST_SCHEDULE.STATUS, contestSchedule.getStatus())
                .set(CONTEST_SCHEDULE.BASE_SCHEDULE_ID, contestSchedule.getBaseScheduleId())
                .returning(CONTEST_SCHEDULE.ID_)
                .fetchOne();
        if (ret == null) {
            throw new IllegalStateException("Could not retrieve inserted id");
        } else {
            contestSchedule.setId_(ret.getValue(CONTEST_SCHEDULE.ID_));
            return contestSchedule;
        }
    }

    @Override
    public Optional<ContestSchedule> get(Long id_){
        final Record record =  dslContext.selectFrom(CONTEST_SCHEDULE)
                .where(CONTEST_SCHEDULE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestSchedule.class));
    }

    @Override
    public boolean exists(Long id_) {
        return dslContext.selectCount()
                .from(CONTEST_SCHEDULE)
                .where(CONTEST_SCHEDULE.ID_.eq(id_))
                .fetchOne().into(Integer.class) > 0;
    }

    @Override
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
    public boolean delete(Long id_) {
        return dslContext.deleteFrom(CONTEST_SCHEDULE)
                .where(CONTEST_SCHEDULE.ID_.eq(id_))
                .execute() > 0;
    }
}
