package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTeamHistoryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTeamHistoryService
 * @generated
 */
public class PlanTeamHistoryServiceWrapper implements PlanTeamHistoryService,
    ServiceWrapper<PlanTeamHistoryService> {
    private PlanTeamHistoryService _planTeamHistoryService;

    public PlanTeamHistoryServiceWrapper(
        PlanTeamHistoryService planTeamHistoryService) {
        _planTeamHistoryService = planTeamHistoryService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTeamHistoryService getWrappedPlanTeamHistoryService() {
        return _planTeamHistoryService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTeamHistoryService(
        PlanTeamHistoryService planTeamHistoryService) {
        _planTeamHistoryService = planTeamHistoryService;
    }

    public PlanTeamHistoryService getWrappedService() {
        return _planTeamHistoryService;
    }

    public void setWrappedService(PlanTeamHistoryService planTeamHistoryService) {
        _planTeamHistoryService = planTeamHistoryService;
    }
}
