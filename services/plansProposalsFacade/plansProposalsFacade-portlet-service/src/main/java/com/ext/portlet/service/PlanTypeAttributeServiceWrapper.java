package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanTypeAttributeService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttributeService
 * @generated
 */
public class PlanTypeAttributeServiceWrapper implements PlanTypeAttributeService,
    ServiceWrapper<PlanTypeAttributeService> {
    private PlanTypeAttributeService _planTypeAttributeService;

    public PlanTypeAttributeServiceWrapper(
        PlanTypeAttributeService planTypeAttributeService) {
        _planTypeAttributeService = planTypeAttributeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planTypeAttributeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTypeAttributeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planTypeAttributeService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanTypeAttributeService getWrappedPlanTypeAttributeService() {
        return _planTypeAttributeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanTypeAttributeService(
        PlanTypeAttributeService planTypeAttributeService) {
        _planTypeAttributeService = planTypeAttributeService;
    }

    @Override
    public PlanTypeAttributeService getWrappedService() {
        return _planTypeAttributeService;
    }

    @Override
    public void setWrappedService(
        PlanTypeAttributeService planTypeAttributeService) {
        _planTypeAttributeService = planTypeAttributeService;
    }
}
