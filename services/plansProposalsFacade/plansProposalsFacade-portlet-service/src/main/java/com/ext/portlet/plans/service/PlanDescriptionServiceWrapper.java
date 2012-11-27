package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanDescriptionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanDescriptionService
 * @generated
 */
public class PlanDescriptionServiceWrapper implements PlanDescriptionService,
    ServiceWrapper<PlanDescriptionService> {
    private PlanDescriptionService _planDescriptionService;

    public PlanDescriptionServiceWrapper(
        PlanDescriptionService planDescriptionService) {
        _planDescriptionService = planDescriptionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanDescriptionService getWrappedPlanDescriptionService() {
        return _planDescriptionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanDescriptionService(
        PlanDescriptionService planDescriptionService) {
        _planDescriptionService = planDescriptionService;
    }

    public PlanDescriptionService getWrappedService() {
        return _planDescriptionService;
    }

    public void setWrappedService(PlanDescriptionService planDescriptionService) {
        _planDescriptionService = planDescriptionService;
    }
}
