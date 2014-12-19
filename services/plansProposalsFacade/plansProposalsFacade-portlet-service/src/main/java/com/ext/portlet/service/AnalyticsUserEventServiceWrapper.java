package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AnalyticsUserEventService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEventService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _analyticsUserEventService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _analyticsUserEventService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _analyticsUserEventService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AnalyticsUserEventService getWrappedAnalyticsUserEventService() {
        return _analyticsUserEventService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAnalyticsUserEventService(
        AnalyticsUserEventService analyticsUserEventService) {
        _analyticsUserEventService = analyticsUserEventService;
    }

    @Override
    public AnalyticsUserEventService getWrappedService() {
        return _analyticsUserEventService;
    }

    @Override
    public void setWrappedService(
        AnalyticsUserEventService analyticsUserEventService) {
        _analyticsUserEventService = analyticsUserEventService;
    }
}
