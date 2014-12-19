package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlansUserSettingsService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettingsService
 * @generated
 */
public class PlansUserSettingsServiceWrapper implements PlansUserSettingsService,
    ServiceWrapper<PlansUserSettingsService> {
    private PlansUserSettingsService _plansUserSettingsService;

    public PlansUserSettingsServiceWrapper(
        PlansUserSettingsService plansUserSettingsService) {
        _plansUserSettingsService = plansUserSettingsService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _plansUserSettingsService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansUserSettingsService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _plansUserSettingsService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlansUserSettingsService getWrappedPlansUserSettingsService() {
        return _plansUserSettingsService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlansUserSettingsService(
        PlansUserSettingsService plansUserSettingsService) {
        _plansUserSettingsService = plansUserSettingsService;
    }

    @Override
    public PlansUserSettingsService getWrappedService() {
        return _plansUserSettingsService;
    }

    @Override
    public void setWrappedService(
        PlansUserSettingsService plansUserSettingsService) {
        _plansUserSettingsService = plansUserSettingsService;
    }
}
