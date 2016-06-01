package org.xcolab.service.activities.domain.activitysubscription;

import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.exceptions.NotFoundException;

import java.util.List;

public interface ActivitySubscriptionDao {

    ActivitySubscription create(ActivitySubscription activitySubscription);

    ActivitySubscription get(Long pk) throws NotFoundException;

    boolean delete(Long pk);

    boolean isSubscribed(Long receiverId, Long classNameId, Long classPK, Integer type , String extraInfo);

    List<ActivitySubscription> getActivitySubscribers(Long classNameId, Long classPK, Long receiverId);

    boolean deleteSubcription(Long receiverId, Long classNameId, Long classPK, Integer type, String extraInfo);
}
