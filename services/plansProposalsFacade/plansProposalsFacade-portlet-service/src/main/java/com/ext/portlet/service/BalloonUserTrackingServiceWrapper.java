package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BalloonUserTrackingService}.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonUserTrackingService
 * @generated
 */
public class BalloonUserTrackingServiceWrapper
    implements BalloonUserTrackingService,
        ServiceWrapper<BalloonUserTrackingService> {
    private BalloonUserTrackingService _balloonUserTrackingService;

    public BalloonUserTrackingServiceWrapper(
        BalloonUserTrackingService balloonUserTrackingService) {
        _balloonUserTrackingService = balloonUserTrackingService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _balloonUserTrackingService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _balloonUserTrackingService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _balloonUserTrackingService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BalloonUserTrackingService getWrappedBalloonUserTrackingService() {
        return _balloonUserTrackingService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBalloonUserTrackingService(
        BalloonUserTrackingService balloonUserTrackingService) {
        _balloonUserTrackingService = balloonUserTrackingService;
    }

    @Override
    public BalloonUserTrackingService getWrappedService() {
        return _balloonUserTrackingService;
    }

    @Override
    public void setWrappedService(
        BalloonUserTrackingService balloonUserTrackingService) {
        _balloonUserTrackingService = balloonUserTrackingService;
    }
}
