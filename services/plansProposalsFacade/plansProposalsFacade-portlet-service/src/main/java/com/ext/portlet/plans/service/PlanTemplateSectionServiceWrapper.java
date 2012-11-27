package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTemplateSectionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTemplateSectionService
 * @generated
 */
public class PlanTemplateSectionServiceWrapper
    implements PlanTemplateSectionService,
        ServiceWrapper<PlanTemplateSectionService> {
    private PlanTemplateSectionService _planTemplateSectionService;

    public PlanTemplateSectionServiceWrapper(
        PlanTemplateSectionService planTemplateSectionService) {
        _planTemplateSectionService = planTemplateSectionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTemplateSectionService getWrappedPlanTemplateSectionService() {
        return _planTemplateSectionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTemplateSectionService(
        PlanTemplateSectionService planTemplateSectionService) {
        _planTemplateSectionService = planTemplateSectionService;
    }

    public PlanTemplateSectionService getWrappedService() {
        return _planTemplateSectionService;
    }

    public void setWrappedService(
        PlanTemplateSectionService planTemplateSectionService) {
        _planTemplateSectionService = planTemplateSectionService;
    }
}
