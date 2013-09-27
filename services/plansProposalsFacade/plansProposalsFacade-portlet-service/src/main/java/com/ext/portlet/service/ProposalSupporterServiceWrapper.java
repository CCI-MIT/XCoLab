package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalSupporterService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalSupporterService
 * @generated
 */
public class ProposalSupporterServiceWrapper implements ProposalSupporterService,
    ServiceWrapper<ProposalSupporterService> {
    private ProposalSupporterService _proposalSupporterService;

    public ProposalSupporterServiceWrapper(
        ProposalSupporterService proposalSupporterService) {
        _proposalSupporterService = proposalSupporterService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalSupporterService getWrappedProposalSupporterService() {
        return _proposalSupporterService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalSupporterService(
        ProposalSupporterService proposalSupporterService) {
        _proposalSupporterService = proposalSupporterService;
    }

    public ProposalSupporterService getWrappedService() {
        return _proposalSupporterService;
    }

    public void setWrappedService(
        ProposalSupporterService proposalSupporterService) {
        _proposalSupporterService = proposalSupporterService;
    }
}
