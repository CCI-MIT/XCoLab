package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanPropertyFilterService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilterService
 * @generated
 */
public class PlanPropertyFilterServiceWrapper
    implements PlanPropertyFilterService,
        ServiceWrapper<PlanPropertyFilterService> {
    private PlanPropertyFilterService _planPropertyFilterService;

    public PlanPropertyFilterServiceWrapper(
        PlanPropertyFilterService planPropertyFilterService) {
        _planPropertyFilterService = planPropertyFilterService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planPropertyFilterService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPropertyFilterService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planPropertyFilterService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanPropertyFilterService getWrappedPlanPropertyFilterService() {
        return _planPropertyFilterService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanPropertyFilterService(
        PlanPropertyFilterService planPropertyFilterService) {
        _planPropertyFilterService = planPropertyFilterService;
    }

    @Override
    public PlanPropertyFilterService getWrappedService() {
        return _planPropertyFilterService;
    }

    @Override
    public void setWrappedService(
        PlanPropertyFilterService planPropertyFilterService) {
        _planPropertyFilterService = planPropertyFilterService;
    }
}
