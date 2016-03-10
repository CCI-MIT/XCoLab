package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ConfigurationAttributeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ConfigurationAttributeService
 * @generated
 */
public class ConfigurationAttributeServiceWrapper
    implements ConfigurationAttributeService,
        ServiceWrapper<ConfigurationAttributeService> {
    private ConfigurationAttributeService _configurationAttributeService;

    public ConfigurationAttributeServiceWrapper(
        ConfigurationAttributeService configurationAttributeService) {
        _configurationAttributeService = configurationAttributeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _configurationAttributeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _configurationAttributeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _configurationAttributeService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ConfigurationAttributeService getWrappedConfigurationAttributeService() {
        return _configurationAttributeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedConfigurationAttributeService(
        ConfigurationAttributeService configurationAttributeService) {
        _configurationAttributeService = configurationAttributeService;
    }

    @Override
    public ConfigurationAttributeService getWrappedService() {
        return _configurationAttributeService;
    }

    @Override
    public void setWrappedService(
        ConfigurationAttributeService configurationAttributeService) {
        _configurationAttributeService = configurationAttributeService;
    }
}
