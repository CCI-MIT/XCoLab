package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.Activity.ActivitySubscriptionNameGenerator;
import com.ext.portlet.model.ActivitySubscription;

public class DefaultSubscriptionNameGenerator implements ActivitySubscriptionNameGenerator {
    
    @Override
    public String getName(ActivitySubscription subscription) {
        return "Unknown subscription " + subscription.getClassName() + "-" + subscription.getClassPK();
    }

    @Override
    public boolean supports(ActivitySubscription subscription) {
        return true;
    }

}
