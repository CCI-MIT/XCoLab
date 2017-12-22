package org.xcolab.service.activities.domain.activitySubscription;

import org.jooq.DeleteFinalStep;
import org.jooq.Query;

import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.List;
import java.util.Optional;

public interface ActivitySubscriptionDao {

    ActivitySubscription create(ActivitySubscription activitySubscription);

    Optional<ActivitySubscription> get(Long pk);

    Optional<ActivitySubscription> get(ActivityCategory activityCategory, Long receiverId,
            Long categoryId);

    boolean update(ActivitySubscription activitySubscription);

    void batch(List<? extends Query> queries);

    boolean delete(Long pk);

    boolean delete(ActivityCategory activityCategory, Long receiverId, Long categoryId);

    boolean delete(ActivityCategory activityCategory, List<Long> categoryIds);

    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long pk);

    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(ActivityCategory activityCategory,
            Long receiverId, Long categoryId);

    boolean isSubscribed(ActivityCategory activityCategory, long receiverId, Long categoryId);

    List<ActivitySubscription> getActivitySubscribers(ActivityCategory activityCategory,
            Long categoryId, Long receiverId);
}
