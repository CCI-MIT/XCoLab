package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalAttributeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalAttributeService
 * @generated
 */
public class ProposalAttributeServiceWrapper implements ProposalAttributeService,
    ServiceWrapper<ProposalAttributeService> {
    private ProposalAttributeService _proposalAttributeService;

    public ProposalAttributeServiceWrapper(
        ProposalAttributeService proposalAttributeService) {
        _proposalAttributeService = proposalAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalAttributeService getWrappedProposalAttributeService() {
        return _proposalAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalAttributeService(
        ProposalAttributeService proposalAttributeService) {
        _proposalAttributeService = proposalAttributeService;
    }

    public ProposalAttributeService getWrappedService() {
        return _proposalAttributeService;
    }

    public void setWrappedService(
        ProposalAttributeService proposalAttributeService) {
        _proposalAttributeService = proposalAttributeService;
    }
}
