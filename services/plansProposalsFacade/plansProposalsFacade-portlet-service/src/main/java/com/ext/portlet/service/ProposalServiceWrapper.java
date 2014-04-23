package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalService
 * @generated
 */
public class ProposalServiceWrapper implements ProposalService,
    ServiceWrapper<ProposalService> {
    private ProposalService _proposalService;

    public ProposalServiceWrapper(ProposalService proposalService) {
        _proposalService = proposalService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
    * This method returns the index of the latest version of a proposal within a given contestPhaseId
    *
    * @param contestPhaseId    The ID of the contest phase
    * @param proposalId        The ID of the proposal
    * @return The index of the latest version in a list of all versions of the proposal
    * @throws PortalException
    * @throws SystemException
    */
    @Override
    public com.liferay.portal.kernel.json.JSONObject getProposalVersionFirstIndex(
        long contestPhaseId, long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalService.getProposalVersionFirstIndex(contestPhaseId,
            proposalId);
    }

    /**
    * This method returns the index of the passed version of a proposal
    *
    * @param version           The proposal version
    * @param proposalId        The ID of the proposal
    * @return The index of the latest version in a list of all versions of the proposal
    * @throws PortalException
    * @throws SystemException
    */
    @Override
    public com.liferay.portal.kernel.json.JSONObject getProposalVersionIndex(
        long version, long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalService.getProposalVersionIndex(version, proposalId);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject getProposalVersions(
        long contestPhaseId, long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalService.getProposalVersions(contestPhaseId, proposalId,
            start, end);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject getProposalVersions(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalService.getProposalVersions(proposalId, start, end);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONArray getProposalContestSections(
        long proposalId, int version, long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalService.getProposalContestSections(proposalId, version,
            contestId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalService getWrappedProposalService() {
        return _proposalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalService(ProposalService proposalService) {
        _proposalService = proposalService;
    }

    @Override
    public ProposalService getWrappedService() {
        return _proposalService;
    }

    @Override
    public void setWrappedService(ProposalService proposalService) {
        _proposalService = proposalService;
    }
}
