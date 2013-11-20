package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestPhaseTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseTypeService
 * @generated
 */
public class ContestPhaseTypeServiceWrapper implements ContestPhaseTypeService,
    ServiceWrapper<ContestPhaseTypeService> {
    private ContestPhaseTypeService _contestPhaseTypeService;

    public ContestPhaseTypeServiceWrapper(
        ContestPhaseTypeService contestPhaseTypeService) {
        _contestPhaseTypeService = contestPhaseTypeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestPhaseTypeService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestPhaseTypeService getWrappedContestPhaseTypeService() {
        return _contestPhaseTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestPhaseTypeService(
        ContestPhaseTypeService contestPhaseTypeService) {
        _contestPhaseTypeService = contestPhaseTypeService;
    }

    @Override
    public ContestPhaseTypeService getWrappedService() {
        return _contestPhaseTypeService;
    }

    @Override
    public void setWrappedService(
        ContestPhaseTypeService contestPhaseTypeService) {
        _contestPhaseTypeService = contestPhaseTypeService;
    }
}
