package org.xcolab.service.activities.domain.activitySubscription;

import org.jooq.DeleteFinalStep;
import org.jooq.Query;

import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.util.List;
import java.util.Optional;

public interface ActivitySubscriptionDao {

    ActivitySubscription create(ActivitySubscription activitySubscription);

    Optional<ActivitySubscription> get(Long pk);
    Optional<ActivitySubscription> get(Long receiverId, Long classNameId,
            Long classPK, String extraInfo);

    boolean update(ActivitySubscription activitySubscription);

    void batch(List<? extends Query> queries);
    boolean delete(Long pk);
    boolean delete(Long receiverId, Long classNameId, Long classPK, String extraInfo);
    boolean delete(ActivityEntryType activityEntryType, List<Long> classPKs);
    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long pk);
    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long receiverId, Long classNameId,
            Long classPK, String extraInfo);

    boolean isSubscribed(long receiverId, long classNameId, Long classPK, int type,
            String extraInfo);

    List<ActivitySubscription> getActivitySubscribers(Long classNameId, Long classPK,
            Long receiverId);
}
