package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalVoteService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalVoteService
 * @generated
 */
public class ProposalVoteServiceWrapper implements ProposalVoteService,
    ServiceWrapper<ProposalVoteService> {
    private ProposalVoteService _proposalVoteService;

    public ProposalVoteServiceWrapper(ProposalVoteService proposalVoteService) {
        _proposalVoteService = proposalVoteService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalVoteService getWrappedProposalVoteService() {
        return _proposalVoteService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalVoteService(
        ProposalVoteService proposalVoteService) {
        _proposalVoteService = proposalVoteService;
    }

    public ProposalVoteService getWrappedService() {
        return _proposalVoteService;
    }

    public void setWrappedService(ProposalVoteService proposalVoteService) {
        _proposalVoteService = proposalVoteService;
    }
}
