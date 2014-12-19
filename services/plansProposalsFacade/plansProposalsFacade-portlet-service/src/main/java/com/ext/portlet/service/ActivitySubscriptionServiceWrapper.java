package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ActivitySubscriptionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscriptionService
 * @generated
 */
public class ActivitySubscriptionServiceWrapper
    implements ActivitySubscriptionService,
        ServiceWrapper<ActivitySubscriptionService> {
    private ActivitySubscriptionService _activitySubscriptionService;

    public ActivitySubscriptionServiceWrapper(
        ActivitySubscriptionService activitySubscriptionService) {
        _activitySubscriptionService = activitySubscriptionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _activitySubscriptionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _activitySubscriptionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _activitySubscriptionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ActivitySubscriptionService getWrappedActivitySubscriptionService() {
        return _activitySubscriptionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedActivitySubscriptionService(
        ActivitySubscriptionService activitySubscriptionService) {
        _activitySubscriptionService = activitySubscriptionService;
    }

    @Override
    public ActivitySubscriptionService getWrappedService() {
        return _activitySubscriptionService;
    }

    @Override
    public void setWrappedService(
        ActivitySubscriptionService activitySubscriptionService) {
        _activitySubscriptionService = activitySubscriptionService;
    }
}
