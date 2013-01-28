package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositionItemService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPositionItemService
 * @generated
 */
public class PlanPositionItemServiceWrapper implements PlanPositionItemService,
    ServiceWrapper<PlanPositionItemService> {
    private PlanPositionItemService _planPositionItemService;

    public PlanPositionItemServiceWrapper(
        PlanPositionItemService planPositionItemService) {
        _planPositionItemService = planPositionItemService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanPositionItemService getWrappedPlanPositionItemService() {
        return _planPositionItemService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanPositionItemService(
        PlanPositionItemService planPositionItemService) {
        _planPositionItemService = planPositionItemService;
    }

    public PlanPositionItemService getWrappedService() {
        return _planPositionItemService;
    }

    public void setWrappedService(
        PlanPositionItemService planPositionItemService) {
        _planPositionItemService = planPositionItemService;
    }
}
