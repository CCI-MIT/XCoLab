package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalContestPhaseAttributeTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalContestPhaseAttributeTypeService
 * @generated
 */
public class ProposalContestPhaseAttributeTypeServiceWrapper
    implements ProposalContestPhaseAttributeTypeService,
        ServiceWrapper<ProposalContestPhaseAttributeTypeService> {
    private ProposalContestPhaseAttributeTypeService _proposalContestPhaseAttributeTypeService;

    public ProposalContestPhaseAttributeTypeServiceWrapper(
        ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService) {
        _proposalContestPhaseAttributeTypeService = proposalContestPhaseAttributeTypeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalContestPhaseAttributeTypeService getWrappedProposalContestPhaseAttributeTypeService() {
        return _proposalContestPhaseAttributeTypeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalContestPhaseAttributeTypeService(
        ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService) {
        _proposalContestPhaseAttributeTypeService = proposalContestPhaseAttributeTypeService;
    }

    public ProposalContestPhaseAttributeTypeService getWrappedService() {
        return _proposalContestPhaseAttributeTypeService;
    }

    public void setWrappedService(
        ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService) {
        _proposalContestPhaseAttributeTypeService = proposalContestPhaseAttributeTypeService;
    }
}
