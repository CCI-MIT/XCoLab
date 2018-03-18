package org.xcolab.service.activities.domain.activityEntry;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.model.tables.records.ActivityEntryRecord;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.activities.enums.ActivityCategory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.xcolab.model.Tables.ACTIVITY_ENTRY;

@Repository
public class ActivityEntryDaoImpl implements ActivityEntryDao {

    private final DSLContext dslContext;

    @Autowired
    public ActivityEntryDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}

    @Override
    public ActivityEntry create(ActivityEntry activityEntry) {
        ActivityEntryRecord ret = this.dslContext.insertInto(ACTIVITY_ENTRY)
                .set(ACTIVITY_ENTRY.MEMBER_ID, activityEntry.getMemberId())
                .set(ACTIVITY_ENTRY.CREATE_DATE, DSL.currentTimestamp())
                .set(ACTIVITY_ENTRY.ACTIVITY_CATEGORY, activityEntry.getActivityCategory())
                .set(ACTIVITY_ENTRY.ACTIVITY_TYPE, activityEntry.getActivityType())
                .set(ACTIVITY_ENTRY.CATEGORY_ID, activityEntry.getCategoryId())
                .set(ACTIVITY_ENTRY.ADDITIONAL_ID, activityEntry.getAdditionalId())
                .returning(ACTIVITY_ENTRY.ACTIVITY_ENTRY_ID)
                .fetchOne();
        if (ret != null) {
            activityEntry.setActivityEntryId(ret.getValue(ACTIVITY_ENTRY.ACTIVITY_ENTRY_ID));
            return activityEntry;
        } else {
            return null;
        }
    }

    @Override
    public ActivityEntry get(Long activityEntryId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(ACTIVITY_ENTRY)
                .where(ACTIVITY_ENTRY.ACTIVITY_ENTRY_ID.eq(activityEntryId)).fetchOne();

        if (record == null) {
            throw new NotFoundException(
                    "ActivityEntry with id " + activityEntryId + " does not exist");
        }
        return record.into(ActivityEntry.class);

    }

    @Override
    public List<ActivityEntry> getActivitiesAfter(Date date) {
        if (date == null) {
            return null;
        }
        final SelectQuery<Record> query = dslContext.select()
                .from(ACTIVITY_ENTRY)
                .getQuery();
        query.addConditions(ACTIVITY_ENTRY.CREATE_DATE.gt(new Timestamp(date.getTime())));
        query.addOrderBy(ACTIVITY_ENTRY.CREATE_DATE.desc());
        return query.fetchInto(ActivityEntry.class);

    }

    @Override
    public boolean delete(ActivityCategory activityCategory, List<Long> categoryIds) {
        return dslContext.deleteFrom(ACTIVITY_ENTRY)
                .where(ACTIVITY_ENTRY.ACTIVITY_CATEGORY.eq(activityCategory.name()))
                .and(ACTIVITY_ENTRY.CATEGORY_ID.in(categoryIds))
                .execute() > 0;
    }

    @Override
    public List<ActivityEntry> findByGiven(PaginationHelper paginationHelper,
            Long memberId, List<Long> memberIdsToExclude) {
        final SelectQuery<Record> query = dslContext.select()
                .from(ACTIVITY_ENTRY)
                .getQuery();

        if (memberId != null) {
            query.addConditions(ACTIVITY_ENTRY.MEMBER_ID.eq(memberId));
        }

        if (memberIdsToExclude != null) {
            query.addConditions(ACTIVITY_ENTRY.MEMBER_ID.notIn(memberIdsToExclude));
        }

        query.addOrderBy(ACTIVITY_ENTRY.CREATE_DATE.desc());

        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(ActivityEntry.class);
    }

    @Override
    public Integer countByGiven(Long memberId, List<Long> memberIdsToExclude) {

        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(ACTIVITY_ENTRY)
                .getQuery();

        if (memberId != null) {
            query.addConditions(ACTIVITY_ENTRY.MEMBER_ID.eq(memberId));
        }

        if (memberIdsToExclude != null) {
            query.addConditions(ACTIVITY_ENTRY.MEMBER_ID.notIn(memberIdsToExclude));
        }

        query.addOrderBy(ACTIVITY_ENTRY.ACTIVITY_ENTRY_ID.desc());

        return query.fetchOne(0, Integer.class);
    }
}
