package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanModelRunService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanModelRunService
 * @generated
 */
public class PlanModelRunServiceWrapper implements PlanModelRunService,
    ServiceWrapper<PlanModelRunService> {
    private PlanModelRunService _planModelRunService;

    public PlanModelRunServiceWrapper(PlanModelRunService planModelRunService) {
        _planModelRunService = planModelRunService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanModelRunService getWrappedPlanModelRunService() {
        return _planModelRunService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanModelRunService(
        PlanModelRunService planModelRunService) {
        _planModelRunService = planModelRunService;
    }

    public PlanModelRunService getWrappedService() {
        return _planModelRunService;
    }

    public void setWrappedService(PlanModelRunService planModelRunService) {
        _planModelRunService = planModelRunService;
    }
}
