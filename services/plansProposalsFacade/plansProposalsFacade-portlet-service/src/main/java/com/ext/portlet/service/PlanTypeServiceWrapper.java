package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeService
 * @generated
 */
public class PlanTypeServiceWrapper implements PlanTypeService,
    ServiceWrapper<PlanTypeService> {
    private PlanTypeService _planTypeService;

    public PlanTypeServiceWrapper(PlanTypeService planTypeService) {
        _planTypeService = planTypeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planTypeService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanTypeService getWrappedPlanTypeService() {
        return _planTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanTypeService(PlanTypeService planTypeService) {
        _planTypeService = planTypeService;
    }

    @Override
    public PlanTypeService getWrappedService() {
        return _planTypeService;
    }

    @Override
    public void setWrappedService(PlanTypeService planTypeService) {
        _planTypeService = planTypeService;
    }
}
