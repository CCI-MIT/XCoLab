package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemPhaseMapService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItemPhaseMapService
 * @generated
 */
public class PlanItemPhaseMapServiceWrapper implements PlanItemPhaseMapService,
    ServiceWrapper<PlanItemPhaseMapService> {
    private PlanItemPhaseMapService _planItemPhaseMapService;

    public PlanItemPhaseMapServiceWrapper(
        PlanItemPhaseMapService planItemPhaseMapService) {
        _planItemPhaseMapService = planItemPhaseMapService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanItemPhaseMapService getWrappedPlanItemPhaseMapService() {
        return _planItemPhaseMapService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanItemPhaseMapService(
        PlanItemPhaseMapService planItemPhaseMapService) {
        _planItemPhaseMapService = planItemPhaseMapService;
    }

    public PlanItemPhaseMapService getWrappedService() {
        return _planItemPhaseMapService;
    }

    public void setWrappedService(
        PlanItemPhaseMapService planItemPhaseMapService) {
        _planItemPhaseMapService = planItemPhaseMapService;
    }
}
