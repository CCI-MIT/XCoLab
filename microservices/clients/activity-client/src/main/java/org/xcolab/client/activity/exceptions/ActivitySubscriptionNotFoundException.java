package org.xcolab.client.activity.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ActivitySubscriptionNotFoundException extends EntityNotFoundException {

    public ActivitySubscriptionNotFoundException(Long activitySubscriptionId) {
        super("ActivitySubscription with id " + activitySubscriptionId + " not found.",
                ActivitySubscriptionNotFoundException.class);
    }
}
