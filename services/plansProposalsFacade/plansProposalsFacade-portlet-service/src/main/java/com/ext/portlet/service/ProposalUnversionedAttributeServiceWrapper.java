package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalUnversionedAttributeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalUnversionedAttributeService
 * @generated
 */
public class ProposalUnversionedAttributeServiceWrapper
    implements ProposalUnversionedAttributeService,
        ServiceWrapper<ProposalUnversionedAttributeService> {
    private ProposalUnversionedAttributeService _proposalUnversionedAttributeService;

    public ProposalUnversionedAttributeServiceWrapper(
        ProposalUnversionedAttributeService proposalUnversionedAttributeService) {
        _proposalUnversionedAttributeService = proposalUnversionedAttributeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalUnversionedAttributeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalUnversionedAttributeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalUnversionedAttributeService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalUnversionedAttributeService getWrappedProposalUnversionedAttributeService() {
        return _proposalUnversionedAttributeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalUnversionedAttributeService(
        ProposalUnversionedAttributeService proposalUnversionedAttributeService) {
        _proposalUnversionedAttributeService = proposalUnversionedAttributeService;
    }

    @Override
    public ProposalUnversionedAttributeService getWrappedService() {
        return _proposalUnversionedAttributeService;
    }

    @Override
    public void setWrappedService(
        ProposalUnversionedAttributeService proposalUnversionedAttributeService) {
        _proposalUnversionedAttributeService = proposalUnversionedAttributeService;
    }
}
