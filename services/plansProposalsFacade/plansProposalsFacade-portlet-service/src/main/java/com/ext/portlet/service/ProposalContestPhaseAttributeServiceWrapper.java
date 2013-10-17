package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalContestPhaseAttributeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalContestPhaseAttributeService
 * @generated
 */
public class ProposalContestPhaseAttributeServiceWrapper
    implements ProposalContestPhaseAttributeService,
        ServiceWrapper<ProposalContestPhaseAttributeService> {
    private ProposalContestPhaseAttributeService _proposalContestPhaseAttributeService;

    public ProposalContestPhaseAttributeServiceWrapper(
        ProposalContestPhaseAttributeService proposalContestPhaseAttributeService) {
        _proposalContestPhaseAttributeService = proposalContestPhaseAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalContestPhaseAttributeService getWrappedProposalContestPhaseAttributeService() {
        return _proposalContestPhaseAttributeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalContestPhaseAttributeService(
        ProposalContestPhaseAttributeService proposalContestPhaseAttributeService) {
        _proposalContestPhaseAttributeService = proposalContestPhaseAttributeService;
    }

    public ProposalContestPhaseAttributeService getWrappedService() {
        return _proposalContestPhaseAttributeService;
    }

    public void setWrappedService(
        ProposalContestPhaseAttributeService proposalContestPhaseAttributeService) {
        _proposalContestPhaseAttributeService = proposalContestPhaseAttributeService;
    }
}
