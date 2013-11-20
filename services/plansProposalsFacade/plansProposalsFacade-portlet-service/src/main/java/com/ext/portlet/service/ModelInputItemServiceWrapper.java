package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelInputItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemService
 * @generated
 */
public class ModelInputItemServiceWrapper implements ModelInputItemService,
    ServiceWrapper<ModelInputItemService> {
    private ModelInputItemService _modelInputItemService;

    public ModelInputItemServiceWrapper(
        ModelInputItemService modelInputItemService) {
        _modelInputItemService = modelInputItemService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelInputItemService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelInputItemService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelInputItemService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelInputItemService getWrappedModelInputItemService() {
        return _modelInputItemService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelInputItemService(
        ModelInputItemService modelInputItemService) {
        _modelInputItemService = modelInputItemService;
    }

    @Override
    public ModelInputItemService getWrappedService() {
        return _modelInputItemService;
    }

    @Override
    public void setWrappedService(ModelInputItemService modelInputItemService) {
        _modelInputItemService = modelInputItemService;
    }
}
