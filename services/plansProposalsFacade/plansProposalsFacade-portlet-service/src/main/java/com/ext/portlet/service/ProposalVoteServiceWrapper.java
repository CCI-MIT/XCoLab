package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalVoteService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVoteService
 * @generated
 */
public class ProposalVoteServiceWrapper implements ProposalVoteService,
    ServiceWrapper<ProposalVoteService> {
    private ProposalVoteService _proposalVoteService;

    public ProposalVoteServiceWrapper(ProposalVoteService proposalVoteService) {
        _proposalVoteService = proposalVoteService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalVoteService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalVoteService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalVoteService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalVoteService getWrappedProposalVoteService() {
        return _proposalVoteService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalVoteService(
        ProposalVoteService proposalVoteService) {
        _proposalVoteService = proposalVoteService;
    }

    @Override
    public ProposalVoteService getWrappedService() {
        return _proposalVoteService;
    }

    @Override
    public void setWrappedService(ProposalVoteService proposalVoteService) {
        _proposalVoteService = proposalVoteService;
    }
}
