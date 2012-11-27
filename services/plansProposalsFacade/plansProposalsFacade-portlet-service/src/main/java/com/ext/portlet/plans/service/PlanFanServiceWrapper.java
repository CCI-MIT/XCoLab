package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanFanService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanFanService
 * @generated
 */
public class PlanFanServiceWrapper implements PlanFanService,
    ServiceWrapper<PlanFanService> {
    private PlanFanService _planFanService;

    public PlanFanServiceWrapper(PlanFanService planFanService) {
        _planFanService = planFanService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanFanService getWrappedPlanFanService() {
        return _planFanService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanFanService(PlanFanService planFanService) {
        _planFanService = planFanService;
    }

    public PlanFanService getWrappedService() {
        return _planFanService;
    }

    public void setWrappedService(PlanFanService planFanService) {
        _planFanService = planFanService;
    }
}
