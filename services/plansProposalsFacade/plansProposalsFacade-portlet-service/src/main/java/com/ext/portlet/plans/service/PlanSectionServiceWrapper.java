package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSectionService
 * @generated
 */
public class PlanSectionServiceWrapper implements PlanSectionService,
    ServiceWrapper<PlanSectionService> {
    private PlanSectionService _planSectionService;

    public PlanSectionServiceWrapper(PlanSectionService planSectionService) {
        _planSectionService = planSectionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanSectionService getWrappedPlanSectionService() {
        return _planSectionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanSectionService(
        PlanSectionService planSectionService) {
        _planSectionService = planSectionService;
    }

    public PlanSectionService getWrappedService() {
        return _planSectionService;
    }

    public void setWrappedService(PlanSectionService planSectionService) {
        _planSectionService = planSectionService;
    }
}
