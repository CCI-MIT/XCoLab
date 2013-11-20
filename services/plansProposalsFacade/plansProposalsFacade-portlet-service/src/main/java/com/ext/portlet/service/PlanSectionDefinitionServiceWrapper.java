package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanSectionDefinitionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionService
 * @generated
 */
public class PlanSectionDefinitionServiceWrapper
    implements PlanSectionDefinitionService,
        ServiceWrapper<PlanSectionDefinitionService> {
    private PlanSectionDefinitionService _planSectionDefinitionService;

    public PlanSectionDefinitionServiceWrapper(
        PlanSectionDefinitionService planSectionDefinitionService) {
        _planSectionDefinitionService = planSectionDefinitionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planSectionDefinitionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planSectionDefinitionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planSectionDefinitionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanSectionDefinitionService getWrappedPlanSectionDefinitionService() {
        return _planSectionDefinitionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanSectionDefinitionService(
        PlanSectionDefinitionService planSectionDefinitionService) {
        _planSectionDefinitionService = planSectionDefinitionService;
    }

    @Override
    public PlanSectionDefinitionService getWrappedService() {
        return _planSectionDefinitionService;
    }

    @Override
    public void setWrappedService(
        PlanSectionDefinitionService planSectionDefinitionService) {
        _planSectionDefinitionService = planSectionDefinitionService;
    }
}
