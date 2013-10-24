package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalService
 * @generated
 */
public class ProposalServiceWrapper implements ProposalService,
    ServiceWrapper<ProposalService> {
    private ProposalService _proposalService;

    public ProposalServiceWrapper(ProposalService proposalService) {
        _proposalService = proposalService;
    }

    public com.liferay.portal.kernel.json.JSONObject getProposalVersions(
        long contestPhaseId, long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalService.getProposalVersions(contestPhaseId, proposalId,
            start, end);
    }

    public com.liferay.portal.kernel.json.JSONObject getProposalVersions(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalService.getProposalVersions(proposalId, start, end);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalService getWrappedProposalService() {
        return _proposalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalService(ProposalService proposalService) {
        _proposalService = proposalService;
    }

    public ProposalService getWrappedService() {
        return _proposalService;
    }

    public void setWrappedService(ProposalService proposalService) {
        _proposalService = proposalService;
    }
}
