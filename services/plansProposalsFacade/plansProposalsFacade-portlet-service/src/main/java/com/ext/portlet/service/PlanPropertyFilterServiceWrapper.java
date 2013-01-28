package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPropertyFilterService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPropertyFilterService
 * @generated
 */
public class PlanPropertyFilterServiceWrapper
    implements PlanPropertyFilterService,
        ServiceWrapper<PlanPropertyFilterService> {
    private PlanPropertyFilterService _planPropertyFilterService;

    public PlanPropertyFilterServiceWrapper(
        PlanPropertyFilterService planPropertyFilterService) {
        _planPropertyFilterService = planPropertyFilterService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanPropertyFilterService getWrappedPlanPropertyFilterService() {
        return _planPropertyFilterService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanPropertyFilterService(
        PlanPropertyFilterService planPropertyFilterService) {
        _planPropertyFilterService = planPropertyFilterService;
    }

    public PlanPropertyFilterService getWrappedService() {
        return _planPropertyFilterService;
    }

    public void setWrappedService(
        PlanPropertyFilterService planPropertyFilterService) {
        _planPropertyFilterService = planPropertyFilterService;
    }
}
