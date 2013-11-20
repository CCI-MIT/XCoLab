package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalAttributeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalAttributeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalAttributeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalAttributeService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalAttributeService getWrappedProposalAttributeService() {
        return _proposalAttributeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalAttributeService(
        ProposalAttributeService proposalAttributeService) {
        _proposalAttributeService = proposalAttributeService;
    }

    @Override
    public ProposalAttributeService getWrappedService() {
        return _proposalAttributeService;
    }

    @Override
    public void setWrappedService(
        ProposalAttributeService proposalAttributeService) {
        _proposalAttributeService = proposalAttributeService;
    }
}
