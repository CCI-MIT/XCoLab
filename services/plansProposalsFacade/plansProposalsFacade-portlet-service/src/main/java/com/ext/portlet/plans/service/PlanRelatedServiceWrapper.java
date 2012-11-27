package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanRelatedService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanRelatedService
 * @generated
 */
public class PlanRelatedServiceWrapper implements PlanRelatedService,
    ServiceWrapper<PlanRelatedService> {
    private PlanRelatedService _planRelatedService;

    public PlanRelatedServiceWrapper(PlanRelatedService planRelatedService) {
        _planRelatedService = planRelatedService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanRelatedService getWrappedPlanRelatedService() {
        return _planRelatedService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanRelatedService(
        PlanRelatedService planRelatedService) {
        _planRelatedService = planRelatedService;
    }

    public PlanRelatedService getWrappedService() {
        return _planRelatedService;
    }

    public void setWrappedService(PlanRelatedService planRelatedService) {
        _planRelatedService = planRelatedService;
    }
}
