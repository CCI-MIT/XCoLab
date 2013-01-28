package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTemplateService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTemplateService
 * @generated
 */
public class PlanTemplateServiceWrapper implements PlanTemplateService,
    ServiceWrapper<PlanTemplateService> {
    private PlanTemplateService _planTemplateService;

    public PlanTemplateServiceWrapper(PlanTemplateService planTemplateService) {
        _planTemplateService = planTemplateService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTemplateService getWrappedPlanTemplateService() {
        return _planTemplateService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTemplateService(
        PlanTemplateService planTemplateService) {
        _planTemplateService = planTemplateService;
    }

    public PlanTemplateService getWrappedService() {
        return _planTemplateService;
    }

    public void setWrappedService(PlanTemplateService planTemplateService) {
        _planTemplateService = planTemplateService;
    }
}
