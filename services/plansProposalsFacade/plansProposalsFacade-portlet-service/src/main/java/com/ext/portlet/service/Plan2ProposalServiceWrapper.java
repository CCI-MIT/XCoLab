package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Plan2ProposalService}.
 *
 * @author Brian Wing Shun Chan
 * @see Plan2ProposalService
 * @generated
 */
public class Plan2ProposalServiceWrapper implements Plan2ProposalService,
    ServiceWrapper<Plan2ProposalService> {
    private Plan2ProposalService _plan2ProposalService;

    public Plan2ProposalServiceWrapper(
        Plan2ProposalService plan2ProposalService) {
        _plan2ProposalService = plan2ProposalService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _plan2ProposalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plan2ProposalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _plan2ProposalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Plan2ProposalService getWrappedPlan2ProposalService() {
        return _plan2ProposalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlan2ProposalService(
        Plan2ProposalService plan2ProposalService) {
        _plan2ProposalService = plan2ProposalService;
    }

    @Override
    public Plan2ProposalService getWrappedService() {
        return _plan2ProposalService;
    }

    @Override
    public void setWrappedService(Plan2ProposalService plan2ProposalService) {
        _plan2ProposalService = plan2ProposalService;
    }
}
