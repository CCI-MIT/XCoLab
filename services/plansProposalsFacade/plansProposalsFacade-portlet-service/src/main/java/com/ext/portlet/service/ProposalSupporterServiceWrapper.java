package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalSupporterService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalSupporterService
 * @generated
 */
public class ProposalSupporterServiceWrapper implements ProposalSupporterService,
    ServiceWrapper<ProposalSupporterService> {
    private ProposalSupporterService _proposalSupporterService;

    public ProposalSupporterServiceWrapper(
        ProposalSupporterService proposalSupporterService) {
        _proposalSupporterService = proposalSupporterService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalSupporterService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalSupporterService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalSupporterService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalSupporterService getWrappedProposalSupporterService() {
        return _proposalSupporterService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalSupporterService(
        ProposalSupporterService proposalSupporterService) {
        _proposalSupporterService = proposalSupporterService;
    }

    @Override
    public ProposalSupporterService getWrappedService() {
        return _proposalSupporterService;
    }

    @Override
    public void setWrappedService(
        ProposalSupporterService proposalSupporterService) {
        _proposalSupporterService = proposalSupporterService;
    }
}
