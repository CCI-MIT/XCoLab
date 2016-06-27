package org.xcolab.service.activities.domain.activitySubscription;

import org.jooq.DeleteFinalStep;
import org.jooq.Query;

import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.service.activities.exceptions.NotFoundException;

import java.util.List;

public interface ActivitySubscriptionDao {

    ActivitySubscription create(ActivitySubscription activitySubscription);

    ActivitySubscription get(Long pk) throws NotFoundException;
    ActivitySubscription get(Long receiverId, Long classNameId, Long classPK,String extraInfo)
            throws NotFoundException;

    boolean update(ActivitySubscription activitySubscription);

    void batch(List<? extends Query> queries);
    boolean delete(Long pk);
    boolean delete(Long receiverId, Long classNameId, Long classPK, String extraInfo);
    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long pk);
    DeleteFinalStep<ActivitySubscriptionRecord> getDeleteQuery(Long receiverId, Long classNameId,
            Long classPK, String extraInfo);

    boolean isSubscribed(Long receiverId, Long classNameId, Long classPK, Integer type,
            String extraInfo);

    List<ActivitySubscription> getActivitySubscribers(Long classNameId, Long classPK,
            Long receiverId);
}
