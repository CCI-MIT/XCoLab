package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilterService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansFilterService
 * @generated
 */
public class PlansFilterServiceWrapper implements PlansFilterService,
    ServiceWrapper<PlansFilterService> {
    private PlansFilterService _plansFilterService;

    public PlansFilterServiceWrapper(PlansFilterService plansFilterService) {
        _plansFilterService = plansFilterService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlansFilterService getWrappedPlansFilterService() {
        return _plansFilterService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlansFilterService(
        PlansFilterService plansFilterService) {
        _plansFilterService = plansFilterService;
    }

    public PlansFilterService getWrappedService() {
        return _plansFilterService;
    }

    public void setWrappedService(PlansFilterService plansFilterService) {
        _plansFilterService = plansFilterService;
    }
}
