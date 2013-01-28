package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputItemService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelInputItemService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelInputItemService getWrappedModelInputItemService() {
        return _modelInputItemService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelInputItemService(
        ModelInputItemService modelInputItemService) {
        _modelInputItemService = modelInputItemService;
    }

    public ModelInputItemService getWrappedService() {
        return _modelInputItemService;
    }

    public void setWrappedService(ModelInputItemService modelInputItemService) {
        _modelInputItemService = modelInputItemService;
    }
}
