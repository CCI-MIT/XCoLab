package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalVersionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalVersionService
 * @generated
 */
public class ProposalVersionServiceWrapper implements ProposalVersionService,
    ServiceWrapper<ProposalVersionService> {
    private ProposalVersionService _proposalVersionService;

    public ProposalVersionServiceWrapper(
        ProposalVersionService proposalVersionService) {
        _proposalVersionService = proposalVersionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalVersionService getWrappedProposalVersionService() {
        return _proposalVersionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalVersionService(
        ProposalVersionService proposalVersionService) {
        _proposalVersionService = proposalVersionService;
    }

    public ProposalVersionService getWrappedService() {
        return _proposalVersionService;
    }

    public void setWrappedService(ProposalVersionService proposalVersionService) {
        _proposalVersionService = proposalVersionService;
    }
}
