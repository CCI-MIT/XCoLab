package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BalloonStatsEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonStatsEntryService
 * @generated
 */
public class BalloonStatsEntryServiceWrapper implements BalloonStatsEntryService,
    ServiceWrapper<BalloonStatsEntryService> {
    private BalloonStatsEntryService _balloonStatsEntryService;

    public BalloonStatsEntryServiceWrapper(
        BalloonStatsEntryService balloonStatsEntryService) {
        _balloonStatsEntryService = balloonStatsEntryService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _balloonStatsEntryService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _balloonStatsEntryService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _balloonStatsEntryService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BalloonStatsEntryService getWrappedBalloonStatsEntryService() {
        return _balloonStatsEntryService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBalloonStatsEntryService(
        BalloonStatsEntryService balloonStatsEntryService) {
        _balloonStatsEntryService = balloonStatsEntryService;
    }

    @Override
    public BalloonStatsEntryService getWrappedService() {
        return _balloonStatsEntryService;
    }

    @Override
    public void setWrappedService(
        BalloonStatsEntryService balloonStatsEntryService) {
        _balloonStatsEntryService = balloonStatsEntryService;
    }
}
