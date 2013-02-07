package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link LandingPageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LandingPageService
 * @generated
 */
public class LandingPageServiceWrapper implements LandingPageService,
    ServiceWrapper<LandingPageService> {
    private LandingPageService _landingPageService;

    public LandingPageServiceWrapper(LandingPageService landingPageService) {
        _landingPageService = landingPageService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public LandingPageService getWrappedLandingPageService() {
        return _landingPageService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedLandingPageService(
        LandingPageService landingPageService) {
        _landingPageService = landingPageService;
    }

    public LandingPageService getWrappedService() {
        return _landingPageService;
    }

    public void setWrappedService(LandingPageService landingPageService) {
        _landingPageService = landingPageService;
    }
}
