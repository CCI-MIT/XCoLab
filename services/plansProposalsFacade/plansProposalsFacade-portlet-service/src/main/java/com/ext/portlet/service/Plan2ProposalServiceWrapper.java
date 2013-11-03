package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Plan2ProposalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Plan2ProposalService
 * @generated
 */
public class Plan2ProposalServiceWrapper implements Plan2ProposalService,
    ServiceWrapper<Plan2ProposalService> {
    private Plan2ProposalService _plan2ProposalService;

    public Plan2ProposalServiceWrapper(
        Plan2ProposalService plan2ProposalService) {
        _plan2ProposalService = plan2ProposalService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public Plan2ProposalService getWrappedPlan2ProposalService() {
        return _plan2ProposalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlan2ProposalService(
        Plan2ProposalService plan2ProposalService) {
        _plan2ProposalService = plan2ProposalService;
    }

    public Plan2ProposalService getWrappedService() {
        return _plan2ProposalService;
    }

    public void setWrappedService(Plan2ProposalService plan2ProposalService) {
        _plan2ProposalService = plan2ProposalService;
    }
}
