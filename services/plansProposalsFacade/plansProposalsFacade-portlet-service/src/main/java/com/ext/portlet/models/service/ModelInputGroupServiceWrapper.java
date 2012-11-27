package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputGroupService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelInputGroupService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelInputGroupService getWrappedModelInputGroupService() {
        return _modelInputGroupService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelInputGroupService(
        ModelInputGroupService modelInputGroupService) {
        _modelInputGroupService = modelInputGroupService;
    }

    public ModelInputGroupService getWrappedService() {
        return _modelInputGroupService;
    }

    public void setWrappedService(ModelInputGroupService modelInputGroupService) {
        _modelInputGroupService = modelInputGroupService;
    }
}
