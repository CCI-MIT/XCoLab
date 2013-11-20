package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanTypeColumnService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumnService
 * @generated
 */
public class PlanTypeColumnServiceWrapper implements PlanTypeColumnService,
    ServiceWrapper<PlanTypeColumnService> {
    private PlanTypeColumnService _planTypeColumnService;

    public PlanTypeColumnServiceWrapper(
        PlanTypeColumnService planTypeColumnService) {
        _planTypeColumnService = planTypeColumnService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planTypeColumnService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTypeColumnService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planTypeColumnService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanTypeColumnService getWrappedPlanTypeColumnService() {
        return _planTypeColumnService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanTypeColumnService(
        PlanTypeColumnService planTypeColumnService) {
        _planTypeColumnService = planTypeColumnService;
    }

    @Override
    public PlanTypeColumnService getWrappedService() {
        return _planTypeColumnService;
    }

    @Override
    public void setWrappedService(PlanTypeColumnService planTypeColumnService) {
        _planTypeColumnService = planTypeColumnService;
    }
}
