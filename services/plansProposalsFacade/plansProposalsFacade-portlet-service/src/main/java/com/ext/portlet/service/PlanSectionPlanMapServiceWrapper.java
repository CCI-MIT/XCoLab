package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionPlanMapService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSectionPlanMapService
 * @generated
 */
public class PlanSectionPlanMapServiceWrapper
    implements PlanSectionPlanMapService,
        ServiceWrapper<PlanSectionPlanMapService> {
    private PlanSectionPlanMapService _planSectionPlanMapService;

    public PlanSectionPlanMapServiceWrapper(
        PlanSectionPlanMapService planSectionPlanMapService) {
        _planSectionPlanMapService = planSectionPlanMapService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanSectionPlanMapService getWrappedPlanSectionPlanMapService() {
        return _planSectionPlanMapService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanSectionPlanMapService(
        PlanSectionPlanMapService planSectionPlanMapService) {
        _planSectionPlanMapService = planSectionPlanMapService;
    }

    public PlanSectionPlanMapService getWrappedService() {
        return _planSectionPlanMapService;
    }

    public void setWrappedService(
        PlanSectionPlanMapService planSectionPlanMapService) {
        _planSectionPlanMapService = planSectionPlanMapService;
    }
}
