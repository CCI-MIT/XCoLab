package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link BalloonStatsEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       BalloonStatsEntryService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public BalloonStatsEntryService getWrappedBalloonStatsEntryService() {
        return _balloonStatsEntryService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedBalloonStatsEntryService(
        BalloonStatsEntryService balloonStatsEntryService) {
        _balloonStatsEntryService = balloonStatsEntryService;
    }

    public BalloonStatsEntryService getWrappedService() {
        return _balloonStatsEntryService;
    }

    public void setWrappedService(
        BalloonStatsEntryService balloonStatsEntryService) {
        _balloonStatsEntryService = balloonStatsEntryService;
    }
}
