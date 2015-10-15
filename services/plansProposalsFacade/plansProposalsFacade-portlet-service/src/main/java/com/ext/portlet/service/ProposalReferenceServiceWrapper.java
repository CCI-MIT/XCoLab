package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalReferenceService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalReferenceService
 * @generated
 */
public class ProposalReferenceServiceWrapper implements ProposalReferenceService,
    ServiceWrapper<ProposalReferenceService> {
    private ProposalReferenceService _proposalReferenceService;

    public ProposalReferenceServiceWrapper(
        ProposalReferenceService proposalReferenceService) {
        _proposalReferenceService = proposalReferenceService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalReferenceService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalReferenceService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalReferenceService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalReferenceService getWrappedProposalReferenceService() {
        return _proposalReferenceService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalReferenceService(
        ProposalReferenceService proposalReferenceService) {
        _proposalReferenceService = proposalReferenceService;
    }

    @Override
    public ProposalReferenceService getWrappedService() {
        return _proposalReferenceService;
    }

    @Override
    public void setWrappedService(
        ProposalReferenceService proposalReferenceService) {
        _proposalReferenceService = proposalReferenceService;
    }
}
