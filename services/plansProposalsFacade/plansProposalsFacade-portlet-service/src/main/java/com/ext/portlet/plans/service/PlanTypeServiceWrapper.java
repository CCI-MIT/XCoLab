package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeService
 * @generated
 */
public class PlanTypeServiceWrapper implements PlanTypeService,
    ServiceWrapper<PlanTypeService> {
    private PlanTypeService _planTypeService;

    public PlanTypeServiceWrapper(PlanTypeService planTypeService) {
        _planTypeService = planTypeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTypeService getWrappedPlanTypeService() {
        return _planTypeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTypeService(PlanTypeService planTypeService) {
        _planTypeService = planTypeService;
    }

    public PlanTypeService getWrappedService() {
        return _planTypeService;
    }

    public void setWrappedService(PlanTypeService planTypeService) {
        _planTypeService = planTypeService;
    }
}
