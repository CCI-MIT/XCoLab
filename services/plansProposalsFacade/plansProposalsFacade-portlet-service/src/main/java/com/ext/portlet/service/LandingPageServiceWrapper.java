package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LandingPageService}.
 *
 * @author Brian Wing Shun Chan
 * @see LandingPageService
 * @generated
 */
public class LandingPageServiceWrapper implements LandingPageService,
    ServiceWrapper<LandingPageService> {
    private LandingPageService _landingPageService;

    public LandingPageServiceWrapper(LandingPageService landingPageService) {
        _landingPageService = landingPageService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _landingPageService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _landingPageService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _landingPageService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public LandingPageService getWrappedLandingPageService() {
        return _landingPageService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedLandingPageService(
        LandingPageService landingPageService) {
        _landingPageService = landingPageService;
    }

    @Override
    public LandingPageService getWrappedService() {
        return _landingPageService;
    }

    @Override
    public void setWrappedService(LandingPageService landingPageService) {
        _landingPageService = landingPageService;
    }
}
