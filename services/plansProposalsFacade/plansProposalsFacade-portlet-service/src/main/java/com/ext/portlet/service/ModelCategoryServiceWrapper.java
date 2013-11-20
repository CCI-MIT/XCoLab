package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelCategoryService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelCategoryService
 * @generated
 */
public class ModelCategoryServiceWrapper implements ModelCategoryService,
    ServiceWrapper<ModelCategoryService> {
    private ModelCategoryService _modelCategoryService;

    public ModelCategoryServiceWrapper(
        ModelCategoryService modelCategoryService) {
        _modelCategoryService = modelCategoryService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelCategoryService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelCategoryService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelCategoryService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelCategoryService getWrappedModelCategoryService() {
        return _modelCategoryService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelCategoryService(
        ModelCategoryService modelCategoryService) {
        _modelCategoryService = modelCategoryService;
    }

    @Override
    public ModelCategoryService getWrappedService() {
        return _modelCategoryService;
    }

    @Override
    public void setWrappedService(ModelCategoryService modelCategoryService) {
        _modelCategoryService = modelCategoryService;
    }
}
