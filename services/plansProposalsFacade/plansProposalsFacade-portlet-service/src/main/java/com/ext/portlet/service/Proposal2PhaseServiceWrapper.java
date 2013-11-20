package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Proposal2PhaseService}.
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2PhaseService
 * @generated
 */
public class Proposal2PhaseServiceWrapper implements Proposal2PhaseService,
    ServiceWrapper<Proposal2PhaseService> {
    private Proposal2PhaseService _proposal2PhaseService;

    public Proposal2PhaseServiceWrapper(
        Proposal2PhaseService proposal2PhaseService) {
        _proposal2PhaseService = proposal2PhaseService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposal2PhaseService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposal2PhaseService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposal2PhaseService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Proposal2PhaseService getWrappedProposal2PhaseService() {
        return _proposal2PhaseService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposal2PhaseService(
        Proposal2PhaseService proposal2PhaseService) {
        _proposal2PhaseService = proposal2PhaseService;
    }

    @Override
    public Proposal2PhaseService getWrappedService() {
        return _proposal2PhaseService;
    }

    @Override
    public void setWrappedService(Proposal2PhaseService proposal2PhaseService) {
        _proposal2PhaseService = proposal2PhaseService;
    }
}
