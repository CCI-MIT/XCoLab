package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BalloonLinkService}.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonLinkService
 * @generated
 */
public class BalloonLinkServiceWrapper implements BalloonLinkService,
    ServiceWrapper<BalloonLinkService> {
    private BalloonLinkService _balloonLinkService;

    public BalloonLinkServiceWrapper(BalloonLinkService balloonLinkService) {
        _balloonLinkService = balloonLinkService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _balloonLinkService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _balloonLinkService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _balloonLinkService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BalloonLinkService getWrappedBalloonLinkService() {
        return _balloonLinkService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBalloonLinkService(
        BalloonLinkService balloonLinkService) {
        _balloonLinkService = balloonLinkService;
    }

    @Override
    public BalloonLinkService getWrappedService() {
        return _balloonLinkService;
    }

    @Override
    public void setWrappedService(BalloonLinkService balloonLinkService) {
        _balloonLinkService = balloonLinkService;
    }
}
