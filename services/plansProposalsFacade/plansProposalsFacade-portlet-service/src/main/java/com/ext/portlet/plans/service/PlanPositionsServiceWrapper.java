package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositionsService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPositionsService
 * @generated
 */
public class PlanPositionsServiceWrapper implements PlanPositionsService,
    ServiceWrapper<PlanPositionsService> {
    private PlanPositionsService _planPositionsService;

    public PlanPositionsServiceWrapper(
        PlanPositionsService planPositionsService) {
        _planPositionsService = planPositionsService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanPositionsService getWrappedPlanPositionsService() {
        return _planPositionsService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanPositionsService(
        PlanPositionsService planPositionsService) {
        _planPositionsService = planPositionsService;
    }

    public PlanPositionsService getWrappedService() {
        return _planPositionsService;
    }

    public void setWrappedService(PlanPositionsService planPositionsService) {
        _planPositionsService = planPositionsService;
    }
}
