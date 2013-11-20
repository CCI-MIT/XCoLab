package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelInputGroupService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroupService
 * @generated
 */
public class ModelInputGroupServiceWrapper implements ModelInputGroupService,
    ServiceWrapper<ModelInputGroupService> {
    private ModelInputGroupService _modelInputGroupService;

    public ModelInputGroupServiceWrapper(
        ModelInputGroupService modelInputGroupService) {
        _modelInputGroupService = modelInputGroupService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelInputGroupService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelInputGroupService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelInputGroupService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelInputGroupService getWrappedModelInputGroupService() {
        return _modelInputGroupService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelInputGroupService(
        ModelInputGroupService modelInputGroupService) {
        _modelInputGroupService = modelInputGroupService;
    }

    @Override
    public ModelInputGroupService getWrappedService() {
        return _modelInputGroupService;
    }

    @Override
    public void setWrappedService(ModelInputGroupService modelInputGroupService) {
        _modelInputGroupService = modelInputGroupService;
    }
}
