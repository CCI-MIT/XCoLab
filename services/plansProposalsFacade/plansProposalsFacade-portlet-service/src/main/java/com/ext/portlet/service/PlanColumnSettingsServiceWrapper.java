package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanColumnSettingsService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsService
 * @generated
 */
public class PlanColumnSettingsServiceWrapper
    implements PlanColumnSettingsService,
        ServiceWrapper<PlanColumnSettingsService> {
    private PlanColumnSettingsService _planColumnSettingsService;

    public PlanColumnSettingsServiceWrapper(
        PlanColumnSettingsService planColumnSettingsService) {
        _planColumnSettingsService = planColumnSettingsService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planColumnSettingsService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planColumnSettingsService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planColumnSettingsService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanColumnSettingsService getWrappedPlanColumnSettingsService() {
        return _planColumnSettingsService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanColumnSettingsService(
        PlanColumnSettingsService planColumnSettingsService) {
        _planColumnSettingsService = planColumnSettingsService;
    }

    @Override
    public PlanColumnSettingsService getWrappedService() {
        return _planColumnSettingsService;
    }

    @Override
    public void setWrappedService(
        PlanColumnSettingsService planColumnSettingsService) {
        _planColumnSettingsService = planColumnSettingsService;
    }
}
