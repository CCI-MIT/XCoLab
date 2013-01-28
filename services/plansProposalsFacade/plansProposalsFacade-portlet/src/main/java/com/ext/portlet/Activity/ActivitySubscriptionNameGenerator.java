package com.ext.portlet.Activity;

import com.ext.portlet.model.ActivitySubscription;

public interface ActivitySubscriptionNameGenerator {
    String getName(ActivitySubscription subscription);
    boolean supports(ActivitySubscription subscription);
}
