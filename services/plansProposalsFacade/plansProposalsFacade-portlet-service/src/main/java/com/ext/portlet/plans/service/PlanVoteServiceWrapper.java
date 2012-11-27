package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanVoteService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanVoteService
 * @generated
 */
public class PlanVoteServiceWrapper implements PlanVoteService,
    ServiceWrapper<PlanVoteService> {
    private PlanVoteService _planVoteService;

    public PlanVoteServiceWrapper(PlanVoteService planVoteService) {
        _planVoteService = planVoteService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanVoteService getWrappedPlanVoteService() {
        return _planVoteService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanVoteService(PlanVoteService planVoteService) {
        _planVoteService = planVoteService;
    }

    public PlanVoteService getWrappedService() {
        return _planVoteService;
    }

    public void setWrappedService(PlanVoteService planVoteService) {
        _planVoteService = planVoteService;
    }
}
