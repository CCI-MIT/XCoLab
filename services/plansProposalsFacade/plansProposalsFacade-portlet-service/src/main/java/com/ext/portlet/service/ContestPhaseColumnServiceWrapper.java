package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestPhaseColumnService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumnService
 * @generated
 */
public class ContestPhaseColumnServiceWrapper
    implements ContestPhaseColumnService,
        ServiceWrapper<ContestPhaseColumnService> {
    private ContestPhaseColumnService _contestPhaseColumnService;

    public ContestPhaseColumnServiceWrapper(
        ContestPhaseColumnService contestPhaseColumnService) {
        _contestPhaseColumnService = contestPhaseColumnService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseColumnService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseColumnService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestPhaseColumnService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestPhaseColumnService getWrappedContestPhaseColumnService() {
        return _contestPhaseColumnService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestPhaseColumnService(
        ContestPhaseColumnService contestPhaseColumnService) {
        _contestPhaseColumnService = contestPhaseColumnService;
    }

    @Override
    public ContestPhaseColumnService getWrappedService() {
        return _contestPhaseColumnService;
    }

    @Override
    public void setWrappedService(
        ContestPhaseColumnService contestPhaseColumnService) {
        _contestPhaseColumnService = contestPhaseColumnService;
    }
}
