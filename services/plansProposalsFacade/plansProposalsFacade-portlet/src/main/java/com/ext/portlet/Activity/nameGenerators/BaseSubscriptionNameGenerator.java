package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.Activity.ActivitySubscriptionNameGenerator;
import com.ext.portlet.model.ActivitySubscription;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

public abstract class BaseSubscriptionNameGenerator implements ActivitySubscriptionNameGenerator {
    private long supportedClassNameId = -1;


    @Override
    public boolean supports(ActivitySubscription subscription) {
        if (this.supportedClassNameId < 0) {
            this.supportedClassNameId = ClassNameLocalServiceUtil.getClassNameId(getSupportedClass().getName());
        }
        return subscription.getClassNameId() == this.supportedClassNameId;
    }
    
    protected abstract Class<?> getSupportedClass();

}
