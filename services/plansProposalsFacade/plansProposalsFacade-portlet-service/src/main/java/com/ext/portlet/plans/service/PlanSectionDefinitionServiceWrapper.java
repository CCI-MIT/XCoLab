package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionDefinitionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSectionDefinitionService
 * @generated
 */
public class PlanSectionDefinitionServiceWrapper
    implements PlanSectionDefinitionService,
        ServiceWrapper<PlanSectionDefinitionService> {
    private PlanSectionDefinitionService _planSectionDefinitionService;

    public PlanSectionDefinitionServiceWrapper(
        PlanSectionDefinitionService planSectionDefinitionService) {
        _planSectionDefinitionService = planSectionDefinitionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanSectionDefinitionService getWrappedPlanSectionDefinitionService() {
        return _planSectionDefinitionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanSectionDefinitionService(
        PlanSectionDefinitionService planSectionDefinitionService) {
        _planSectionDefinitionService = planSectionDefinitionService;
    }

    public PlanSectionDefinitionService getWrappedService() {
        return _planSectionDefinitionService;
    }

    public void setWrappedService(
        PlanSectionDefinitionService planSectionDefinitionService) {
        _planSectionDefinitionService = planSectionDefinitionService;
    }
}
