package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItemService
 * @generated
 */
public class PlanItemServiceWrapper implements PlanItemService,
    ServiceWrapper<PlanItemService> {
    private PlanItemService _planItemService;

    public PlanItemServiceWrapper(PlanItemService planItemService) {
        _planItemService = planItemService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanItemService getWrappedPlanItemService() {
        return _planItemService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanItemService(PlanItemService planItemService) {
        _planItemService = planItemService;
    }

    public PlanItemService getWrappedService() {
        return _planItemService;
    }

    public void setWrappedService(PlanItemService planItemService) {
        _planItemService = planItemService;
    }
}
