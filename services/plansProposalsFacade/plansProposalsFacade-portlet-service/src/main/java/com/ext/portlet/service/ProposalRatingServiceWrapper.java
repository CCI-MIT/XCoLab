package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalRatingService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingService
 * @generated
 */
public class ProposalRatingServiceWrapper implements ProposalRatingService,
    ServiceWrapper<ProposalRatingService> {
    private ProposalRatingService _proposalRatingService;

    public ProposalRatingServiceWrapper(
        ProposalRatingService proposalRatingService) {
        _proposalRatingService = proposalRatingService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalRatingService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalRatingService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalRatingService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalRatingService getWrappedProposalRatingService() {
        return _proposalRatingService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalRatingService(
        ProposalRatingService proposalRatingService) {
        _proposalRatingService = proposalRatingService;
    }

    @Override
    public ProposalRatingService getWrappedService() {
        return _proposalRatingService;
    }

    @Override
    public void setWrappedService(ProposalRatingService proposalRatingService) {
        _proposalRatingService = proposalRatingService;
    }
}
