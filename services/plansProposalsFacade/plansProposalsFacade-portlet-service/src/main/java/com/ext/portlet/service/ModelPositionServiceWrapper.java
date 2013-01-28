package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelPositionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelPositionService
 * @generated
 */
public class ModelPositionServiceWrapper implements ModelPositionService,
    ServiceWrapper<ModelPositionService> {
    private ModelPositionService _modelPositionService;

    public ModelPositionServiceWrapper(
        ModelPositionService modelPositionService) {
        _modelPositionService = modelPositionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelPositionService getWrappedModelPositionService() {
        return _modelPositionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelPositionService(
        ModelPositionService modelPositionService) {
        _modelPositionService = modelPositionService;
    }

    public ModelPositionService getWrappedService() {
        return _modelPositionService;
    }

    public void setWrappedService(ModelPositionService modelPositionService) {
        _modelPositionService = modelPositionService;
    }
}
