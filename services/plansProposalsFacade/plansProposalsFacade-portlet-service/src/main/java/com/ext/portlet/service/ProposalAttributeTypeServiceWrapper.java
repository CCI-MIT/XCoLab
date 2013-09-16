package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalAttributeTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalAttributeTypeService
 * @generated
 */
public class ProposalAttributeTypeServiceWrapper
    implements ProposalAttributeTypeService,
        ServiceWrapper<ProposalAttributeTypeService> {
    private ProposalAttributeTypeService _proposalAttributeTypeService;

    public ProposalAttributeTypeServiceWrapper(
        ProposalAttributeTypeService proposalAttributeTypeService) {
        _proposalAttributeTypeService = proposalAttributeTypeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalAttributeTypeService getWrappedProposalAttributeTypeService() {
        return _proposalAttributeTypeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalAttributeTypeService(
        ProposalAttributeTypeService proposalAttributeTypeService) {
        _proposalAttributeTypeService = proposalAttributeTypeService;
    }

    public ProposalAttributeTypeService getWrappedService() {
        return _proposalAttributeTypeService;
    }

    public void setWrappedService(
        ProposalAttributeTypeService proposalAttributeTypeService) {
        _proposalAttributeTypeService = proposalAttributeTypeService;
    }
}
