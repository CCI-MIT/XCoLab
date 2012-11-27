package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanService
 * @generated
 */
public class PlanServiceWrapper implements PlanService,
    ServiceWrapper<PlanService> {
    private PlanService _planService;

    public PlanServiceWrapper(PlanService planService) {
        _planService = planService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanService getWrappedPlanService() {
        return _planService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanService(PlanService planService) {
        _planService = planService;
    }

    public PlanService getWrappedService() {
        return _planService;
    }

    public void setWrappedService(PlanService planService) {
        _planService = planService;
    }
}
