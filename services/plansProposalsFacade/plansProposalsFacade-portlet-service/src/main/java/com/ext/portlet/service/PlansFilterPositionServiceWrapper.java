package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilterPositionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansFilterPositionService
 * @generated
 */
public class PlansFilterPositionServiceWrapper
    implements PlansFilterPositionService,
        ServiceWrapper<PlansFilterPositionService> {
    private PlansFilterPositionService _plansFilterPositionService;

    public PlansFilterPositionServiceWrapper(
        PlansFilterPositionService plansFilterPositionService) {
        _plansFilterPositionService = plansFilterPositionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlansFilterPositionService getWrappedPlansFilterPositionService() {
        return _plansFilterPositionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlansFilterPositionService(
        PlansFilterPositionService plansFilterPositionService) {
        _plansFilterPositionService = plansFilterPositionService;
    }

    public PlansFilterPositionService getWrappedService() {
        return _plansFilterPositionService;
    }

    public void setWrappedService(
        PlansFilterPositionService plansFilterPositionService) {
        _plansFilterPositionService = plansFilterPositionService;
    }
}
