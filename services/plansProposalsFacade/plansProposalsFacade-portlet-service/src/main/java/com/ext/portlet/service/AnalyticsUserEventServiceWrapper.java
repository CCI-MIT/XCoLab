package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AnalyticsUserEventService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AnalyticsUserEventService
 * @generated
 */
public class AnalyticsUserEventServiceWrapper
    implements AnalyticsUserEventService,
        ServiceWrapper<AnalyticsUserEventService> {
    private AnalyticsUserEventService _analyticsUserEventService;

    public AnalyticsUserEventServiceWrapper(
        AnalyticsUserEventService analyticsUserEventService) {
        _analyticsUserEventService = analyticsUserEventService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public AnalyticsUserEventService getWrappedAnalyticsUserEventService() {
        return _analyticsUserEventService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedAnalyticsUserEventService(
        AnalyticsUserEventService analyticsUserEventService) {
        _analyticsUserEventService = analyticsUserEventService;
    }

    public AnalyticsUserEventService getWrappedService() {
        return _analyticsUserEventService;
    }

    public void setWrappedService(
        AnalyticsUserEventService analyticsUserEventService) {
        _analyticsUserEventService = analyticsUserEventService;
    }
}
