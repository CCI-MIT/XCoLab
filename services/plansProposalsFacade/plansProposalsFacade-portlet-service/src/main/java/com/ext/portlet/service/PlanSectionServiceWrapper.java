package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanSectionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionService
 * @generated
 */
public class PlanSectionServiceWrapper implements PlanSectionService,
    ServiceWrapper<PlanSectionService> {
    private PlanSectionService _planSectionService;

    public PlanSectionServiceWrapper(PlanSectionService planSectionService) {
        _planSectionService = planSectionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planSectionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planSectionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planSectionService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanSectionService getWrappedPlanSectionService() {
        return _planSectionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanSectionService(
        PlanSectionService planSectionService) {
        _planSectionService = planSectionService;
    }

    @Override
    public PlanSectionService getWrappedService() {
        return _planSectionService;
    }

    @Override
    public void setWrappedService(PlanSectionService planSectionService) {
        _planSectionService = planSectionService;
    }
}
