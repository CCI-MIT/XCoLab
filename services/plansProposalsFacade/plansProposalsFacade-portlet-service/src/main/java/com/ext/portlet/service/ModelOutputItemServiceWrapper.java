package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelOutputItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItemService
 * @generated
 */
public class ModelOutputItemServiceWrapper implements ModelOutputItemService,
    ServiceWrapper<ModelOutputItemService> {
    private ModelOutputItemService _modelOutputItemService;

    public ModelOutputItemServiceWrapper(
        ModelOutputItemService modelOutputItemService) {
        _modelOutputItemService = modelOutputItemService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelOutputItemService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelOutputItemService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelOutputItemService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelOutputItemService getWrappedModelOutputItemService() {
        return _modelOutputItemService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelOutputItemService(
        ModelOutputItemService modelOutputItemService) {
        _modelOutputItemService = modelOutputItemService;
    }

    @Override
    public ModelOutputItemService getWrappedService() {
        return _modelOutputItemService;
    }

    @Override
    public void setWrappedService(ModelOutputItemService modelOutputItemService) {
        _modelOutputItemService = modelOutputItemService;
    }
}
