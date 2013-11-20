package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestPhaseService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseService
 * @generated
 */
public class ContestPhaseServiceWrapper implements ContestPhaseService,
    ServiceWrapper<ContestPhaseService> {
    private ContestPhaseService _contestPhaseService;

    public ContestPhaseServiceWrapper(ContestPhaseService contestPhaseService) {
        _contestPhaseService = contestPhaseService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestPhaseService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestPhaseService getWrappedContestPhaseService() {
        return _contestPhaseService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestPhaseService(
        ContestPhaseService contestPhaseService) {
        _contestPhaseService = contestPhaseService;
    }

    @Override
    public ContestPhaseService getWrappedService() {
        return _contestPhaseService;
    }

    @Override
    public void setWrappedService(ContestPhaseService contestPhaseService) {
        _contestPhaseService = contestPhaseService;
    }
}
