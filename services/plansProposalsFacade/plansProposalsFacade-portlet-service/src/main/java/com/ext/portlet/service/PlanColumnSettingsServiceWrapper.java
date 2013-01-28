package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanColumnSettingsService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanColumnSettingsService
 * @generated
 */
public class PlanColumnSettingsServiceWrapper
    implements PlanColumnSettingsService,
        ServiceWrapper<PlanColumnSettingsService> {
    private PlanColumnSettingsService _planColumnSettingsService;

    public PlanColumnSettingsServiceWrapper(
        PlanColumnSettingsService planColumnSettingsService) {
        _planColumnSettingsService = planColumnSettingsService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanColumnSettingsService getWrappedPlanColumnSettingsService() {
        return _planColumnSettingsService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanColumnSettingsService(
        PlanColumnSettingsService planColumnSettingsService) {
        _planColumnSettingsService = planColumnSettingsService;
    }

    public PlanColumnSettingsService getWrappedService() {
        return _planColumnSettingsService;
    }

    public void setWrappedService(
        PlanColumnSettingsService planColumnSettingsService) {
        _planColumnSettingsService = planColumnSettingsService;
    }
}
