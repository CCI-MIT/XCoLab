package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansUserSettingsService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansUserSettingsService
 * @generated
 */
public class PlansUserSettingsServiceWrapper implements PlansUserSettingsService,
    ServiceWrapper<PlansUserSettingsService> {
    private PlansUserSettingsService _plansUserSettingsService;

    public PlansUserSettingsServiceWrapper(
        PlansUserSettingsService plansUserSettingsService) {
        _plansUserSettingsService = plansUserSettingsService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlansUserSettingsService getWrappedPlansUserSettingsService() {
        return _plansUserSettingsService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlansUserSettingsService(
        PlansUserSettingsService plansUserSettingsService) {
        _plansUserSettingsService = plansUserSettingsService;
    }

    public PlansUserSettingsService getWrappedService() {
        return _plansUserSettingsService;
    }

    public void setWrappedService(
        PlansUserSettingsService plansUserSettingsService) {
        _plansUserSettingsService = plansUserSettingsService;
    }
}
