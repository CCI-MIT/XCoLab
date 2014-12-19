package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TrackedVisitor2UserService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2UserService
 * @generated
 */
public class TrackedVisitor2UserServiceWrapper
    implements TrackedVisitor2UserService,
        ServiceWrapper<TrackedVisitor2UserService> {
    private TrackedVisitor2UserService _trackedVisitor2UserService;

    public TrackedVisitor2UserServiceWrapper(
        TrackedVisitor2UserService trackedVisitor2UserService) {
        _trackedVisitor2UserService = trackedVisitor2UserService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _trackedVisitor2UserService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _trackedVisitor2UserService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _trackedVisitor2UserService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public TrackedVisitor2UserService getWrappedTrackedVisitor2UserService() {
        return _trackedVisitor2UserService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedTrackedVisitor2UserService(
        TrackedVisitor2UserService trackedVisitor2UserService) {
        _trackedVisitor2UserService = trackedVisitor2UserService;
    }

    @Override
    public TrackedVisitor2UserService getWrappedService() {
        return _trackedVisitor2UserService;
    }

    @Override
    public void setWrappedService(
        TrackedVisitor2UserService trackedVisitor2UserService) {
        _trackedVisitor2UserService = trackedVisitor2UserService;
    }
}
