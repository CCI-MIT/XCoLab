package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestService
 * @generated
 */
public class ContestServiceWrapper implements ContestService,
    ServiceWrapper<ContestService> {
    private ContestService _contestService;

    public ContestServiceWrapper(ContestService contestService) {
        _contestService = contestService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestService getWrappedContestService() {
        return _contestService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestService(ContestService contestService) {
        _contestService = contestService;
    }

    @Override
    public ContestService getWrappedService() {
        return _contestService;
    }

    @Override
    public void setWrappedService(ContestService contestService) {
        _contestService = contestService;
    }
}
