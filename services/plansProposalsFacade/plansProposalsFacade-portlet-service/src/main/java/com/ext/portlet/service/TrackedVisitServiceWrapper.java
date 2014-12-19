package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TrackedVisitService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitService
 * @generated
 */
public class TrackedVisitServiceWrapper implements TrackedVisitService,
    ServiceWrapper<TrackedVisitService> {
    private TrackedVisitService _trackedVisitService;

    public TrackedVisitServiceWrapper(TrackedVisitService trackedVisitService) {
        _trackedVisitService = trackedVisitService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _trackedVisitService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _trackedVisitService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _trackedVisitService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public TrackedVisitService getWrappedTrackedVisitService() {
        return _trackedVisitService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedTrackedVisitService(
        TrackedVisitService trackedVisitService) {
        _trackedVisitService = trackedVisitService;
    }

    @Override
    public TrackedVisitService getWrappedService() {
        return _trackedVisitService;
    }

    @Override
    public void setWrappedService(TrackedVisitService trackedVisitService) {
        _trackedVisitService = trackedVisitService;
    }
}
