package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestScheduleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestScheduleService
 * @generated
 */
public class ContestScheduleServiceWrapper implements ContestScheduleService,
    ServiceWrapper<ContestScheduleService> {
    private ContestScheduleService _contestScheduleService;

    public ContestScheduleServiceWrapper(
        ContestScheduleService contestScheduleService) {
        _contestScheduleService = contestScheduleService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestScheduleService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestScheduleService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestScheduleService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestScheduleService getWrappedContestScheduleService() {
        return _contestScheduleService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestScheduleService(
        ContestScheduleService contestScheduleService) {
        _contestScheduleService = contestScheduleService;
    }

    @Override
    public ContestScheduleService getWrappedService() {
        return _contestScheduleService;
    }

    @Override
    public void setWrappedService(ContestScheduleService contestScheduleService) {
        _contestScheduleService = contestScheduleService;
    }
}
