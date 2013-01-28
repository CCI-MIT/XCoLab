package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ActivitySubscriptionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ActivitySubscriptionService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ActivitySubscriptionService getWrappedActivitySubscriptionService() {
        return _activitySubscriptionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedActivitySubscriptionService(
        ActivitySubscriptionService activitySubscriptionService) {
        _activitySubscriptionService = activitySubscriptionService;
    }

    public ActivitySubscriptionService getWrappedService() {
        return _activitySubscriptionService;
    }

    public void setWrappedService(
        ActivitySubscriptionService activitySubscriptionService) {
        _activitySubscriptionService = activitySubscriptionService;
    }
}
