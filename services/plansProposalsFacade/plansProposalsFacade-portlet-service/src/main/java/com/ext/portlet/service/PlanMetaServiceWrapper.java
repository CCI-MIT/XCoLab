package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanMetaService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanMetaService
 * @generated
 */
public class PlanMetaServiceWrapper implements PlanMetaService,
    ServiceWrapper<PlanMetaService> {
    private PlanMetaService _planMetaService;

    public PlanMetaServiceWrapper(PlanMetaService planMetaService) {
        _planMetaService = planMetaService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanMetaService getWrappedPlanMetaService() {
        return _planMetaService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanMetaService(PlanMetaService planMetaService) {
        _planMetaService = planMetaService;
    }

    public PlanMetaService getWrappedService() {
        return _planMetaService;
    }

    public void setWrappedService(PlanMetaService planMetaService) {
        _planMetaService = planMetaService;
    }
}
