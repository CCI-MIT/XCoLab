package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalVersionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersionService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalVersionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalVersionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalVersionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalVersionService getWrappedProposalVersionService() {
        return _proposalVersionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalVersionService(
        ProposalVersionService proposalVersionService) {
        _proposalVersionService = proposalVersionService;
    }

    @Override
    public ProposalVersionService getWrappedService() {
        return _proposalVersionService;
    }

    @Override
    public void setWrappedService(ProposalVersionService proposalVersionService) {
        _proposalVersionService = proposalVersionService;
    }
}
