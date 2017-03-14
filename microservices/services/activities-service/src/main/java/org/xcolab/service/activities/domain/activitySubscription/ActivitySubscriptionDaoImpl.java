package org.xcolab.service.activities.domain.activitySubscription;

import org.jooq.DSLContext;
import org.jooq.DeleteFinalStep;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.ACTIVITY_SUBSCRIPTION;

@Repository
public class ActivitySubscriptionDaoImpl implements ActivitySubscriptionDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public ActivitySubscription create(ActivitySubscription activitySubscription) {

        ActivitySubscriptionRecord ret = this.dslContext.insertInto(ACTIVITY_SUBSCRIPTION)
                .set(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID, activitySubscription.getClassNameId())
                .set(ACTIVITY_SUBSCRIPTION.CLASS_PK, activitySubscription.getClassPK())
                .set(ACTIVITY_SUBSCRIPTION.TYPE_, activitySubscription.getType_())
                .set(ACTIVITY_SUBSCRIPTION.AUTOMATIC_SUBSCRIPTION_COUNTER,
                        activitySubscription.getAutomaticSubscriptionCounter())
                .set(ACTIVITY_SUBSCRIPTION.EXTRA_DATA, activitySubscription.getExtraData())
                .set(ACTIVITY_SUBSCRIPTION.RECEIVER_ID, activitySubscription.getReceiverId())
                .set(ACTIVITY_SUBSCRIPTION.CREATE_DATE, activitySubscription.getCreateDate())
                .set(ACTIVITY_SUBSCRIPTION.MODIFIED_DATE, activitySubscription.getModifiedDate())
                .returning(ACTIVITY_SUBSCRIPTION.PK)
                .fetchOne();
        if (ret != null) {
            activitySubscription.setPk(ret.getValue(ACTIVITY_SUBSCRIPTION.PK));
            return activitySubscription;
        } else {
            return null;
        }

    }

    @Override
    public Optional<ActivitySubscription> get(Long activitySubscriptionId) {
        final Record record = this.dslContext.selectFrom(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.PK.eq(activitySubscriptionId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ActivitySubscription.class));
    }

    @Override
    public Optional<ActivitySubscription> get(Long receiverId, Long classNameId, Long classPK,
            String extraInfo) {
        final List<ActivitySubscription> subscriptions = dslContext.select()
                .from(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.RECEIVER_ID.eq(receiverId))
                .and(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID.eq(classNameId))
                .and(ACTIVITY_SUBSCRIPTION.CLASS_PK.eq(classPK))
                .and(ACTIVITY_SUBSCRIPTION.EXTRA_DATA.eq(extraInfo))
                .fetch().into(ActivitySubscription.class);
        if (subscriptions.isEmpty()) {
            return Optional.empty();
        }
        if (subscriptions.size() > 1) {
            throw new IllegalStateException(String.format("Duplicate activity subscription: "
                    + "[receiverId=%d, classNameId=%d, classPK=%d, extraInfo=%s]",
                    receiverId, classNameId, classPK, extraInfo));
        }
        return Optional.of(subscriptions.get(0));
    }

    @Override
    public boolean update(ActivitySubscription activitySubscription) {
        return dslContext.update(ACTIVITY_SUBSCRIPTION)
                .set(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID, activitySubscription.getClassNameId())
                .set(ACTIVITY_SUBSCRIPTION.CLASS_PK, activitySubscription.getClassPK())
                .set(ACTIVITY_SUBSCRIPTION.TYPE_, activitySubscription.getType_())
                .set(ACTIVITY_SUBSCRIPTION.AUTOMATIC_SUBSCRIPTION_COUNTER,
                        activitySubscription.getAutomaticSubscriptionCounter())
                .set(ACTIVITY_SUBSCRIPTION.EXTRA_DATA, activitySubscription.getExtraData())
                .set(ACTIVITY_SUBSCRIPTION.RECEIVER_ID, activitySubscription.getReceiverId())
                .set(ACTIVITY_SUBSCRIPTION.CREATE_DATE, activitySubscription.getCreateDate())
                .set(ACTIVITY_SUBSCRIPTION.MODIFIED_DATE, activitySubscription.getModifiedDate())
                .where(ACTIVITY_SUBSCRIPTION.PK.eq(activitySubscription.getPk()))
                .execute() > 0;
    }

    @Override
    public void batch(List<? extends Query> queries) {
        dslContext.batch(queries).execute();
    }

    @Override
    public boolean delete(Long pk) {
        return getDeleteQuery(pk)
                .execute() > 0;
    }

    @Override
    public DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long pk) {
        return dslContext.delete(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.PK.eq(pk));
    }

    @Override
    public boolean delete(Long receiverId, Long classNameId, Long classPK, String extraInfo) {
        return getDeleteQuery(receiverId, classNameId, classPK, extraInfo).execute() > 0;
    }

    @Override
    public DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long receiverId,
            Long classNameId, Long classPK, String extraInfo) {
        return dslContext
                .delete(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.RECEIVER_ID.eq(receiverId))
                .and(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID.eq(classNameId))
                .and(ACTIVITY_SUBSCRIPTION.CLASS_PK.eq(classPK))
                .and(ACTIVITY_SUBSCRIPTION.EXTRA_DATA.eq(extraInfo));
    }

    @Override
    public boolean isSubscribed(long receiverId, long classNameId, Long classPK, int type,
            String extraInfo) {

        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.RECEIVER_ID.eq(receiverId)
                        .and(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID.eq(classNameId))
                        .and(ACTIVITY_SUBSCRIPTION.CLASS_PK.eq(classPK))
                        .and(ACTIVITY_SUBSCRIPTION.TYPE_.eq(type)))
                .getQuery();

        if (extraInfo != null) {
            query.addConditions((ACTIVITY_SUBSCRIPTION.EXTRA_DATA.eq(extraInfo)));
        }

        return query.fetchOne(0, Integer.class) > 0;
    }

    @Override
    public List<ActivitySubscription> getActivitySubscribers(Long classNameId, Long classPK,
            Long receiverId) {
        if (receiverId == null || receiverId == 0) {
            return this.dslContext.select()
                    .from(ACTIVITY_SUBSCRIPTION)
                    .where(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID.eq(classNameId))
                    .and(ACTIVITY_SUBSCRIPTION.CLASS_PK.eq(classPK))
                    .fetchInto(ActivitySubscription.class);
        } else {
            return this.dslContext.select()
                    .from(ACTIVITY_SUBSCRIPTION)
                    .where(ACTIVITY_SUBSCRIPTION.RECEIVER_ID.eq(receiverId))
                    .fetchInto(ActivitySubscription.class);
        }

    }
}
