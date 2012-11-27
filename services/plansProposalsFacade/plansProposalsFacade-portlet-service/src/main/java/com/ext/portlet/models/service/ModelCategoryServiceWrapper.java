package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelCategoryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelCategoryService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelCategoryService getWrappedModelCategoryService() {
        return _modelCategoryService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelCategoryService(
        ModelCategoryService modelCategoryService) {
        _modelCategoryService = modelCategoryService;
    }

    public ModelCategoryService getWrappedService() {
        return _modelCategoryService;
    }

    public void setWrappedService(ModelCategoryService modelCategoryService) {
        _modelCategoryService = modelCategoryService;
    }
}
