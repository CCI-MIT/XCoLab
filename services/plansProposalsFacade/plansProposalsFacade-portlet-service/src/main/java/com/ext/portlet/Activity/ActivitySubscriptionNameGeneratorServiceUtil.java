package com.ext.portlet.Activity;

import com.ext.portlet.model.ActivitySubscription;

public class ActivitySubscriptionNameGeneratorServiceUtil {
    private static ActivitySubscriptionNameGeneratorService _service;
    
    public static String getName(ActivitySubscription subscription) {
        return _service.getName(subscription);
    }

    public ActivitySubscriptionNameGeneratorService getService() {
        return _service;
    }

    public void setService(ActivitySubscriptionNameGeneratorService _service) {
        ActivitySubscriptionNameGeneratorServiceUtil._service = _service;
    }

}
