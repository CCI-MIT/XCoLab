package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPositionService
 * @generated
 */
public class PlanPositionServiceWrapper implements PlanPositionService,
    ServiceWrapper<PlanPositionService> {
    private PlanPositionService _planPositionService;

    public PlanPositionServiceWrapper(PlanPositionService planPositionService) {
        _planPositionService = planPositionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanPositionService getWrappedPlanPositionService() {
        return _planPositionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanPositionService(
        PlanPositionService planPositionService) {
        _planPositionService = planPositionService;
    }

    public PlanPositionService getWrappedService() {
        return _planPositionService;
    }

    public void setWrappedService(PlanPositionService planPositionService) {
        _planPositionService = planPositionService;
    }
}
