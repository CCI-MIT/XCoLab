package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestTypeService
 * @generated
 */
public class ContestTypeServiceWrapper implements ContestTypeService,
    ServiceWrapper<ContestTypeService> {
    private ContestTypeService _contestTypeService;

    public ContestTypeServiceWrapper(ContestTypeService contestTypeService) {
        _contestTypeService = contestTypeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestTypeService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestTypeService getWrappedContestTypeService() {
        return _contestTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestTypeService(
        ContestTypeService contestTypeService) {
        _contestTypeService = contestTypeService;
    }

    @Override
    public ContestTypeService getWrappedService() {
        return _contestTypeService;
    }

    @Override
    public void setWrappedService(ContestTypeService contestTypeService) {
        _contestTypeService = contestTypeService;
    }
}
