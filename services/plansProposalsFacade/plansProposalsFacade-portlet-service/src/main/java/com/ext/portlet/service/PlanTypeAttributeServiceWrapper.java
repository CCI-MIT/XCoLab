package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeAttributeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeAttributeService
 * @generated
 */
public class PlanTypeAttributeServiceWrapper implements PlanTypeAttributeService,
    ServiceWrapper<PlanTypeAttributeService> {
    private PlanTypeAttributeService _planTypeAttributeService;

    public PlanTypeAttributeServiceWrapper(
        PlanTypeAttributeService planTypeAttributeService) {
        _planTypeAttributeService = planTypeAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTypeAttributeService getWrappedPlanTypeAttributeService() {
        return _planTypeAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTypeAttributeService(
        PlanTypeAttributeService planTypeAttributeService) {
        _planTypeAttributeService = planTypeAttributeService;
    }

    public PlanTypeAttributeService getWrappedService() {
        return _planTypeAttributeService;
    }

    public void setWrappedService(
        PlanTypeAttributeService planTypeAttributeService) {
        _planTypeAttributeService = planTypeAttributeService;
    }
}
