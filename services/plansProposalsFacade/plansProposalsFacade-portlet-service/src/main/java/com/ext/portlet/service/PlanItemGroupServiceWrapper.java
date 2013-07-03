package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemGroupService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItemGroupService
 * @generated
 */
public class PlanItemGroupServiceWrapper implements PlanItemGroupService,
    ServiceWrapper<PlanItemGroupService> {
    private PlanItemGroupService _planItemGroupService;

    public PlanItemGroupServiceWrapper(
        PlanItemGroupService planItemGroupService) {
        _planItemGroupService = planItemGroupService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanItemGroupService getWrappedPlanItemGroupService() {
        return _planItemGroupService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanItemGroupService(
        PlanItemGroupService planItemGroupService) {
        _planItemGroupService = planItemGroupService;
    }

    public PlanItemGroupService getWrappedService() {
        return _planItemGroupService;
    }

    public void setWrappedService(PlanItemGroupService planItemGroupService) {
        _planItemGroupService = planItemGroupService;
    }
}
