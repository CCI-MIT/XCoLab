package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanTemplateService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateService
 * @generated
 */
public class PlanTemplateServiceWrapper implements PlanTemplateService,
    ServiceWrapper<PlanTemplateService> {
    private PlanTemplateService _planTemplateService;

    public PlanTemplateServiceWrapper(PlanTemplateService planTemplateService) {
        _planTemplateService = planTemplateService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planTemplateService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTemplateService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planTemplateService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanTemplateService getWrappedPlanTemplateService() {
        return _planTemplateService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanTemplateService(
        PlanTemplateService planTemplateService) {
        _planTemplateService = planTemplateService;
    }

    @Override
    public PlanTemplateService getWrappedService() {
        return _planTemplateService;
    }

    @Override
    public void setWrappedService(PlanTemplateService planTemplateService) {
        _planTemplateService = planTemplateService;
    }
}
