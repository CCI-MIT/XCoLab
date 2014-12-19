package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalContestPhaseAttributeTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeTypeService
 * @generated
 */
public class ProposalContestPhaseAttributeTypeServiceWrapper
    implements ProposalContestPhaseAttributeTypeService,
        ServiceWrapper<ProposalContestPhaseAttributeTypeService> {
    private ProposalContestPhaseAttributeTypeService _proposalContestPhaseAttributeTypeService;

    public ProposalContestPhaseAttributeTypeServiceWrapper(
        ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService) {
        _proposalContestPhaseAttributeTypeService = proposalContestPhaseAttributeTypeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalContestPhaseAttributeTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalContestPhaseAttributeTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalContestPhaseAttributeTypeService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalContestPhaseAttributeTypeService getWrappedProposalContestPhaseAttributeTypeService() {
        return _proposalContestPhaseAttributeTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalContestPhaseAttributeTypeService(
        ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService) {
        _proposalContestPhaseAttributeTypeService = proposalContestPhaseAttributeTypeService;
    }

    @Override
    public ProposalContestPhaseAttributeTypeService getWrappedService() {
        return _proposalContestPhaseAttributeTypeService;
    }

    @Override
    public void setWrappedService(
        ProposalContestPhaseAttributeTypeService proposalContestPhaseAttributeTypeService) {
        _proposalContestPhaseAttributeTypeService = proposalContestPhaseAttributeTypeService;
    }
}
