package org.xcolab.client.activity.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ActivitySubscriptionNotFoundException extends EntityNotFoundException {

    public ActivitySubscriptionNotFoundException(String msg) {
        super(msg, ActivitySubscriptionNotFoundException.class);
    }
}
