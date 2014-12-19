package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalRatingTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingTypeService
 * @generated
 */
public class ProposalRatingTypeServiceWrapper
    implements ProposalRatingTypeService,
        ServiceWrapper<ProposalRatingTypeService> {
    private ProposalRatingTypeService _proposalRatingTypeService;

    public ProposalRatingTypeServiceWrapper(
        ProposalRatingTypeService proposalRatingTypeService) {
        _proposalRatingTypeService = proposalRatingTypeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalRatingTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalRatingTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalRatingTypeService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalRatingTypeService getWrappedProposalRatingTypeService() {
        return _proposalRatingTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalRatingTypeService(
        ProposalRatingTypeService proposalRatingTypeService) {
        _proposalRatingTypeService = proposalRatingTypeService;
    }

    @Override
    public ProposalRatingTypeService getWrappedService() {
        return _proposalRatingTypeService;
    }

    @Override
    public void setWrappedService(
        ProposalRatingTypeService proposalRatingTypeService) {
        _proposalRatingTypeService = proposalRatingTypeService;
    }
}
