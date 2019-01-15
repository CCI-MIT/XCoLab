package org.xcolab.service.activity.domain.activitySubscription;

import org.jooq.DSLContext;
import org.jooq.DeleteFinalStep;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.activity.pojo.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.ACTIVITY_SUBSCRIPTION;

@Repository
public class ActivitySubscriptionDaoImpl implements ActivitySubscriptionDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public IActivitySubscription create(IActivitySubscription activitySubscription) {

        ActivitySubscriptionRecord ret = this.dslContext.insertInto(ACTIVITY_SUBSCRIPTION)
                .set(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID, activitySubscription.getClassNameId())
                .set(ACTIVITY_SUBSCRIPTION.RECEIVER_USER_ID,
                        activitySubscription.getReceiverUserId())
                .set(ACTIVITY_SUBSCRIPTION.ACTIVITY_CATEGORY,
                        activitySubscription.getActivityCategory())
                .set(ACTIVITY_SUBSCRIPTION.CATEGORY_ID, activitySubscription.getCategoryId())
                .set(ACTIVITY_SUBSCRIPTION.AUTOMATIC_SUBSCRIPTION_COUNTER,
                        activitySubscription.getAutomaticSubscriptionCounter())
                .set(ACTIVITY_SUBSCRIPTION.CREATED_AT, DSL.currentTimestamp())
                .set(ACTIVITY_SUBSCRIPTION.UPDATED_AT, DSL.currentTimestamp())
                .returning(ACTIVITY_SUBSCRIPTION.ID).fetchOne();
        if (ret != null) {
            activitySubscription.setId(ret.getValue(ACTIVITY_SUBSCRIPTION.ID));
            return activitySubscription;
        } else {
            return null;
        }
    }

    @Override
    public Optional<IActivitySubscription> get(Long activitySubscriptionId) {
        final Record record = this.dslContext.selectFrom(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.ID.eq(activitySubscriptionId)).fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ActivitySubscription.class));
    }

    @Override
    public Optional<IActivitySubscription> get(ActivityCategory activityCategory, Long receiverId,
            Long categoryId) {
        SelectQuery<Record> subscriptionsQuery = dslContext.select().from(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.RECEIVER_USER_ID.eq(receiverId))
                .and(ACTIVITY_SUBSCRIPTION.ACTIVITY_CATEGORY.eq(activityCategory.name()))
                .and(ACTIVITY_SUBSCRIPTION.CATEGORY_ID.eq(categoryId)).getQuery();

        final List<IActivitySubscription> subscriptions =
                subscriptionsQuery.fetch().into(ActivitySubscription.class);

        if (subscriptions.isEmpty()) {
            return Optional.empty();
        }
        if (subscriptions.size() > 1) {
            throw new IllegalStateException(String.format("Duplicate activity subscription: "
                            + "[receiverId=%d, activityCategory=%s, categoryId=%d]",
                    receiverId, activityCategory, categoryId));
        }
        return Optional.of(subscriptions.get(0));
    }

    @Override
    public boolean update(IActivitySubscription activitySubscription) {
        return dslContext.update(ACTIVITY_SUBSCRIPTION)
                .set(ACTIVITY_SUBSCRIPTION.CLASS_NAME_ID, activitySubscription.getClassNameId())
                .set(ACTIVITY_SUBSCRIPTION.RECEIVER_USER_ID,
                        activitySubscription.getReceiverUserId())
                .set(ACTIVITY_SUBSCRIPTION.ACTIVITY_CATEGORY,
                        activitySubscription.getActivityCategory())
                .set(ACTIVITY_SUBSCRIPTION.CATEGORY_ID, activitySubscription.getCategoryId())
                .set(ACTIVITY_SUBSCRIPTION.AUTOMATIC_SUBSCRIPTION_COUNTER,
                        activitySubscription.getAutomaticSubscriptionCounter())
                .set(ACTIVITY_SUBSCRIPTION.UPDATED_AT, DSL.currentTimestamp())
                .where(ACTIVITY_SUBSCRIPTION.ID.eq(activitySubscription.getId())).execute() > 0;
    }

    @Override
    public void batch(List<? extends Query> queries) {
        dslContext.batch(queries).execute();
    }

    @Override
    public boolean delete(Long pk) {
        return getDeleteQuery(pk).execute() > 0;
    }

    @Override
    public DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long pk) {
        return dslContext.delete(ACTIVITY_SUBSCRIPTION).where(ACTIVITY_SUBSCRIPTION.ID.eq(pk));
    }

    @Override
    public boolean delete(ActivityCategory activityCategory, Long receiverId, Long categoryId) {
        return getDeleteQuery(activityCategory, receiverId, categoryId).execute() > 0;
    }

    @Override
    public boolean delete(ActivityCategory activityCategory,
            List<Long> categoryIds) {// Delete proposal subscriptions
        return dslContext.deleteFrom(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.ACTIVITY_CATEGORY.eq(activityCategory.name()))
                .and(ACTIVITY_SUBSCRIPTION.CATEGORY_ID.in(categoryIds))
                .execute() > 0;
    }

    @Override
    public DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(
            ActivityCategory activityCategory, Long receiverId, Long categoryId) {
        return dslContext.delete(ACTIVITY_SUBSCRIPTION)
                .where(ACTIVITY_SUBSCRIPTION.RECEIVER_USER_ID.eq(receiverId))
                .and(ACTIVITY_SUBSCRIPTION.ACTIVITY_CATEGORY.eq(activityCategory.name()))
                .and(ACTIVITY_SUBSCRIPTION.CATEGORY_ID.eq(categoryId));
    }

    @Override
    public boolean isSubscribed(ActivityCategory activityCategory, long receiverId,
            Long categoryId) {
        final SelectQuery<Record1<Integer>> query =
                dslContext.selectCount().from(ACTIVITY_SUBSCRIPTION)
                        .where(ACTIVITY_SUBSCRIPTION.RECEIVER_USER_ID.eq(receiverId)
                                .and(ACTIVITY_SUBSCRIPTION.ACTIVITY_CATEGORY
                                        .eq(activityCategory.name()))
                                .and(ACTIVITY_SUBSCRIPTION.CATEGORY_ID.eq(categoryId))).getQuery();
        return query.fetchOne(0, Integer.class) > 0;
    }

    @Override
    public List<IActivitySubscription> getActivitySubscribers(ActivityCategory activityCategory,
            Long categoryId,
            Long receiverId) {
        if (receiverId == null || receiverId == 0) {
            return this.dslContext.select().from(ACTIVITY_SUBSCRIPTION)
                    .where(ACTIVITY_SUBSCRIPTION.ACTIVITY_CATEGORY.eq(activityCategory.name()))
                    .and(ACTIVITY_SUBSCRIPTION.CATEGORY_ID.eq(categoryId))
                    .fetchInto(ActivitySubscription.class);
        } else {
            return this.dslContext.select().from(ACTIVITY_SUBSCRIPTION)
                    .where(ACTIVITY_SUBSCRIPTION.RECEIVER_USER_ID.eq(receiverId))
                    .fetchInto(ActivitySubscription.class);
        }
    }
}
