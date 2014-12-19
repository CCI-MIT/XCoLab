package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalContestPhaseAttributeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeService
 * @generated
 */
public class ProposalContestPhaseAttributeServiceWrapper
    implements ProposalContestPhaseAttributeService,
        ServiceWrapper<ProposalContestPhaseAttributeService> {
    private ProposalContestPhaseAttributeService _proposalContestPhaseAttributeService;

    public ProposalContestPhaseAttributeServiceWrapper(
        ProposalContestPhaseAttributeService proposalContestPhaseAttributeService) {
        _proposalContestPhaseAttributeService = proposalContestPhaseAttributeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalContestPhaseAttributeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalContestPhaseAttributeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalContestPhaseAttributeService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalContestPhaseAttributeService getWrappedProposalContestPhaseAttributeService() {
        return _proposalContestPhaseAttributeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalContestPhaseAttributeService(
        ProposalContestPhaseAttributeService proposalContestPhaseAttributeService) {
        _proposalContestPhaseAttributeService = proposalContestPhaseAttributeService;
    }

    @Override
    public ProposalContestPhaseAttributeService getWrappedService() {
        return _proposalContestPhaseAttributeService;
    }

    @Override
    public void setWrappedService(
        ProposalContestPhaseAttributeService proposalContestPhaseAttributeService) {
        _proposalContestPhaseAttributeService = proposalContestPhaseAttributeService;
    }
}
