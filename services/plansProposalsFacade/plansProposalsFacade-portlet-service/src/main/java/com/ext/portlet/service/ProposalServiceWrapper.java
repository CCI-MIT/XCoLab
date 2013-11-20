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
