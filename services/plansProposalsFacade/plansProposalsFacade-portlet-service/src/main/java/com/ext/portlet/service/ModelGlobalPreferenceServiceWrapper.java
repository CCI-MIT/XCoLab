package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelGlobalPreferenceService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreferenceService
 * @generated
 */
public class ModelGlobalPreferenceServiceWrapper
    implements ModelGlobalPreferenceService,
        ServiceWrapper<ModelGlobalPreferenceService> {
    private ModelGlobalPreferenceService _modelGlobalPreferenceService;

    public ModelGlobalPreferenceServiceWrapper(
        ModelGlobalPreferenceService modelGlobalPreferenceService) {
        _modelGlobalPreferenceService = modelGlobalPreferenceService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelGlobalPreferenceService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelGlobalPreferenceService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelGlobalPreferenceService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelGlobalPreferenceService getWrappedModelGlobalPreferenceService() {
        return _modelGlobalPreferenceService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelGlobalPreferenceService(
        ModelGlobalPreferenceService modelGlobalPreferenceService) {
        _modelGlobalPreferenceService = modelGlobalPreferenceService;
    }

    @Override
    public ModelGlobalPreferenceService getWrappedService() {
        return _modelGlobalPreferenceService;
    }

    @Override
    public void setWrappedService(
        ModelGlobalPreferenceService modelGlobalPreferenceService) {
        _modelGlobalPreferenceService = modelGlobalPreferenceService;
    }
}
