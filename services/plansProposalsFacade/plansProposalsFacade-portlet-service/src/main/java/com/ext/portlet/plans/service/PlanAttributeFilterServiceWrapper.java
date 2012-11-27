package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanAttributeFilterService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanAttributeFilterService
 * @generated
 */
public class PlanAttributeFilterServiceWrapper
    implements PlanAttributeFilterService,
        ServiceWrapper<PlanAttributeFilterService> {
    private PlanAttributeFilterService _planAttributeFilterService;

    public PlanAttributeFilterServiceWrapper(
        PlanAttributeFilterService planAttributeFilterService) {
        _planAttributeFilterService = planAttributeFilterService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanAttributeFilterService getWrappedPlanAttributeFilterService() {
        return _planAttributeFilterService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanAttributeFilterService(
        PlanAttributeFilterService planAttributeFilterService) {
        _planAttributeFilterService = planAttributeFilterService;
    }

    public PlanAttributeFilterService getWrappedService() {
        return _planAttributeFilterService;
    }

    public void setWrappedService(
        PlanAttributeFilterService planAttributeFilterService) {
        _planAttributeFilterService = planAttributeFilterService;
    }
}
