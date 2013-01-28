package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeColumnService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeColumnService
 * @generated
 */
public class PlanTypeColumnServiceWrapper implements PlanTypeColumnService,
    ServiceWrapper<PlanTypeColumnService> {
    private PlanTypeColumnService _planTypeColumnService;

    public PlanTypeColumnServiceWrapper(
        PlanTypeColumnService planTypeColumnService) {
        _planTypeColumnService = planTypeColumnService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTypeColumnService getWrappedPlanTypeColumnService() {
        return _planTypeColumnService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTypeColumnService(
        PlanTypeColumnService planTypeColumnService) {
        _planTypeColumnService = planTypeColumnService;
    }

    public PlanTypeColumnService getWrappedService() {
        return _planTypeColumnService;
    }

    public void setWrappedService(PlanTypeColumnService planTypeColumnService) {
        _planTypeColumnService = planTypeColumnService;
    }
}
