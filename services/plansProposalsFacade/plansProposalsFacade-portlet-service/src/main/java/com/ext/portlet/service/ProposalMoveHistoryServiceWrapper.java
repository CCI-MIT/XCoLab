package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalMoveHistoryService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalMoveHistoryService
 * @generated
 */
public class ProposalMoveHistoryServiceWrapper
    implements ProposalMoveHistoryService,
        ServiceWrapper<ProposalMoveHistoryService> {
    private ProposalMoveHistoryService _proposalMoveHistoryService;

    public ProposalMoveHistoryServiceWrapper(
        ProposalMoveHistoryService proposalMoveHistoryService) {
        _proposalMoveHistoryService = proposalMoveHistoryService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalMoveHistoryService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalMoveHistoryService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalMoveHistoryService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalMoveHistoryService getWrappedProposalMoveHistoryService() {
        return _proposalMoveHistoryService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalMoveHistoryService(
        ProposalMoveHistoryService proposalMoveHistoryService) {
        _proposalMoveHistoryService = proposalMoveHistoryService;
    }

    @Override
    public ProposalMoveHistoryService getWrappedService() {
        return _proposalMoveHistoryService;
    }

    @Override
    public void setWrappedService(
        ProposalMoveHistoryService proposalMoveHistoryService) {
        _proposalMoveHistoryService = proposalMoveHistoryService;
    }
}
