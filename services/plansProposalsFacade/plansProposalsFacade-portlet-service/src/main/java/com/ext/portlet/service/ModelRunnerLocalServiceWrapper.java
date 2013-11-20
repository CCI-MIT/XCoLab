package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelRunnerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelRunnerLocalService
 * @generated
 */
public class ModelRunnerLocalServiceWrapper implements ModelRunnerLocalService,
    ServiceWrapper<ModelRunnerLocalService> {
    private ModelRunnerLocalService _modelRunnerLocalService;

    public ModelRunnerLocalServiceWrapper(
        ModelRunnerLocalService modelRunnerLocalService) {
        _modelRunnerLocalService = modelRunnerLocalService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelRunnerLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelRunnerLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelRunnerLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelRunnerLocalService getWrappedModelRunnerLocalService() {
        return _modelRunnerLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelRunnerLocalService(
        ModelRunnerLocalService modelRunnerLocalService) {
        _modelRunnerLocalService = modelRunnerLocalService;
    }

    @Override
    public ModelRunnerLocalService getWrappedService() {
        return _modelRunnerLocalService;
    }

    @Override
    public void setWrappedService(
        ModelRunnerLocalService modelRunnerLocalService) {
        _modelRunnerLocalService = modelRunnerLocalService;
    }
}
