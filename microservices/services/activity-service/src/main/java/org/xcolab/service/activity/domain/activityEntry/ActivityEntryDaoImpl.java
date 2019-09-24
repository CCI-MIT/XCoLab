package org.xcolab.service.activity.domain.activityEntry;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.activity.pojo.tables.pojos.ActivityEntry;
import org.xcolab.model.tables.records.ActivityEntryRecord;
import org.xcolab.service.activity.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ActivityCategory;

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
    public IActivityEntry create(IActivityEntry activityEntry) {
        ActivityEntryRecord ret = this.dslContext.insertInto(ACTIVITY_ENTRY)
                .set(ACTIVITY_ENTRY.USER_ID, activityEntry.getUserId())
                .set(ACTIVITY_ENTRY.CREATED_AT, DSL.currentTimestamp())
                .set(ACTIVITY_ENTRY.ACTIVITY_CATEGORY, activityEntry.getActivityCategory())
                .set(ACTIVITY_ENTRY.ACTIVITY_TYPE, activityEntry.getActivityType())
                .set(ACTIVITY_ENTRY.CATEGORY_ID, activityEntry.getCategoryId())
                .set(ACTIVITY_ENTRY.ADDITIONAL_ID, activityEntry.getAdditionalId())
                .returning(ACTIVITY_ENTRY.ID)
                .fetchOne();
        if (ret != null) {
            activityEntry.setId(ret.getValue(ACTIVITY_ENTRY.ID));
            return activityEntry;
        } else {
            return null;
        }
    }

    @Override
    public IActivityEntry get(Long activityEntryId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(ACTIVITY_ENTRY)
                .where(ACTIVITY_ENTRY.ID.eq(activityEntryId)).fetchOne();

        if (record == null) {
            throw new NotFoundException(
                    "ActivityEntry with id " + activityEntryId + " does not exist");
        }
        return record.into(ActivityEntry.class);

    }

    @Override
    public List<IActivityEntry> getActivitiesAfter(Date date) {
        if (date == null) {
            return null;
        }
        final SelectQuery<Record> query = dslContext.select()
                .from(ACTIVITY_ENTRY)
                .getQuery();
        query.addConditions(ACTIVITY_ENTRY.CREATED_AT.gt(new Timestamp(date.getTime())));
        query.addOrderBy(ACTIVITY_ENTRY.CREATED_AT.desc());
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
    public List<IActivityEntry> findByGiven(PaginationHelper paginationHelper,
            String activityCategory, Long categoryId, Long userId,
            List<Long> userIdsToExclude) {
        final SelectQuery<Record> query = dslContext.select()
                .from(ACTIVITY_ENTRY)
                .getQuery();

        if (activityCategory != null) {
            query.addConditions(ACTIVITY_ENTRY.ACTIVITY_CATEGORY.eq(activityCategory));
        }

        if (categoryId != null) {
            query.addConditions(ACTIVITY_ENTRY.CATEGORY_ID.eq(categoryId));
        }

        if (userId != null) {
            query.addConditions(ACTIVITY_ENTRY.USER_ID.eq(userId));
        }

        if (userIdsToExclude != null) {
            query.addConditions(ACTIVITY_ENTRY.USER_ID.notIn(userIdsToExclude));
        }

        query.addOrderBy(ACTIVITY_ENTRY.CREATED_AT.desc());

        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(ActivityEntry.class);
    }

    @Override
    public Integer countByGiven(Long userId, List<Long> userIdsToExclude) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(ACTIVITY_ENTRY)
                .getQuery();

        if (userId != null) {
            query.addConditions(ACTIVITY_ENTRY.USER_ID.eq(userId));
        }

        if (userIdsToExclude != null) {
            query.addConditions(ACTIVITY_ENTRY.USER_ID.notIn(userIdsToExclude));
        }

        query.addOrderBy(ACTIVITY_ENTRY.ID.desc());

        return query.fetchOne(0, Integer.class);
    }
}
