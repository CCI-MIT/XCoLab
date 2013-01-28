package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanAttributeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanAttributeService
 * @generated
 */
public class PlanAttributeServiceWrapper implements PlanAttributeService,
    ServiceWrapper<PlanAttributeService> {
    private PlanAttributeService _planAttributeService;

    public PlanAttributeServiceWrapper(
        PlanAttributeService planAttributeService) {
        _planAttributeService = planAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanAttributeService getWrappedPlanAttributeService() {
        return _planAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanAttributeService(
        PlanAttributeService planAttributeService) {
        _planAttributeService = planAttributeService;
    }

    public PlanAttributeService getWrappedService() {
        return _planAttributeService;
    }

    public void setWrappedService(PlanAttributeService planAttributeService) {
        _planAttributeService = planAttributeService;
    }
}
