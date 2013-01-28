package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelOutputItemService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelOutputItemService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelOutputItemService getWrappedModelOutputItemService() {
        return _modelOutputItemService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelOutputItemService(
        ModelOutputItemService modelOutputItemService) {
        _modelOutputItemService = modelOutputItemService;
    }

    public ModelOutputItemService getWrappedService() {
        return _modelOutputItemService;
    }

    public void setWrappedService(ModelOutputItemService modelOutputItemService) {
        _modelOutputItemService = modelOutputItemService;
    }
}
