package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalRatingValueService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValueService
 * @generated
 */
public class ProposalRatingValueServiceWrapper
    implements ProposalRatingValueService,
        ServiceWrapper<ProposalRatingValueService> {
    private ProposalRatingValueService _proposalRatingValueService;

    public ProposalRatingValueServiceWrapper(
        ProposalRatingValueService proposalRatingValueService) {
        _proposalRatingValueService = proposalRatingValueService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalRatingValueService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalRatingValueService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalRatingValueService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalRatingValueService getWrappedProposalRatingValueService() {
        return _proposalRatingValueService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalRatingValueService(
        ProposalRatingValueService proposalRatingValueService) {
        _proposalRatingValueService = proposalRatingValueService;
    }

    @Override
    public ProposalRatingValueService getWrappedService() {
        return _proposalRatingValueService;
    }

    @Override
    public void setWrappedService(
        ProposalRatingValueService proposalRatingValueService) {
        _proposalRatingValueService = proposalRatingValueService;
    }
}
