package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BalloonTextService}.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonTextService
 * @generated
 */
public class BalloonTextServiceWrapper implements BalloonTextService,
    ServiceWrapper<BalloonTextService> {
    private BalloonTextService _balloonTextService;

    public BalloonTextServiceWrapper(BalloonTextService balloonTextService) {
        _balloonTextService = balloonTextService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _balloonTextService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _balloonTextService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _balloonTextService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BalloonTextService getWrappedBalloonTextService() {
        return _balloonTextService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBalloonTextService(
        BalloonTextService balloonTextService) {
        _balloonTextService = balloonTextService;
    }

    @Override
    public BalloonTextService getWrappedService() {
        return _balloonTextService;
    }

    @Override
    public void setWrappedService(BalloonTextService balloonTextService) {
        _balloonTextService = balloonTextService;
    }
}
