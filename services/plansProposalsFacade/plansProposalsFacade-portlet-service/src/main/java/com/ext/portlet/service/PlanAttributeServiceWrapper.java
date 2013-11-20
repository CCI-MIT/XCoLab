package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanAttributeService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeService
 * @generated
 */
public class PlanAttributeServiceWrapper implements PlanAttributeService,
    ServiceWrapper<PlanAttributeService> {
    private PlanAttributeService _planAttributeService;

    public PlanAttributeServiceWrapper(
        PlanAttributeService planAttributeService) {
        _planAttributeService = planAttributeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planAttributeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planAttributeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planAttributeService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanAttributeService getWrappedPlanAttributeService() {
        return _planAttributeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanAttributeService(
        PlanAttributeService planAttributeService) {
        _planAttributeService = planAttributeService;
    }

    @Override
    public PlanAttributeService getWrappedService() {
        return _planAttributeService;
    }

    @Override
    public void setWrappedService(PlanAttributeService planAttributeService) {
        _planAttributeService = planAttributeService;
    }
}
