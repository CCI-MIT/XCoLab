package org.xcolab.service.activity.domain.activitySubscription;

import org.jooq.DeleteFinalStep;
import org.jooq.Query;

import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.List;
import java.util.Optional;

public interface ActivitySubscriptionDao {

    IActivitySubscription create(IActivitySubscription activitySubscription);

    Optional<IActivitySubscription> get(Long pk);

    Optional<IActivitySubscription> get(ActivityCategory activityCategory, Long receiverId,
            Long categoryId);

    boolean update(IActivitySubscription activitySubscription);

    void batch(List<? extends Query> queries);

    boolean delete(Long pk);

    boolean delete(ActivityCategory activityCategory, Long receiverId, Long categoryId);

    boolean delete(ActivityCategory activityCategory, List<Long> categoryIds);

    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long pk);

    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(ActivityCategory activityCategory,
            Long receiverId, Long categoryId);

    boolean isSubscribed(ActivityCategory activityCategory, long receiverId, Long categoryId);

    List<IActivitySubscription> getActivitySubscribers(ActivityCategory activityCategory,
            Long categoryId, Long receiverId);
}
