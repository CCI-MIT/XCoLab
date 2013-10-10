package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelRunnerService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelRunnerService
 * @generated
 */
public class ModelRunnerServiceWrapper implements ModelRunnerService,
    ServiceWrapper<ModelRunnerService> {
    private ModelRunnerService _modelRunnerService;

    public ModelRunnerServiceWrapper(ModelRunnerService modelRunnerService) {
        _modelRunnerService = modelRunnerService;
    }

    public com.liferay.portal.kernel.json.JSONObject getScenario(
        long scenarioId) {
        return _modelRunnerService.getScenario(scenarioId);
    }

    public com.liferay.portal.kernel.json.JSONObject getModel(long modelId) {
        return _modelRunnerService.getModel(modelId);
    }

    public com.liferay.portal.kernel.json.JSONObject runModel(long modelId,
        java.lang.String inputs) {
        return _modelRunnerService.runModel(modelId, inputs);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelRunnerService getWrappedModelRunnerService() {
        return _modelRunnerService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelRunnerService(
        ModelRunnerService modelRunnerService) {
        _modelRunnerService = modelRunnerService;
    }

    public ModelRunnerService getWrappedService() {
        return _modelRunnerService;
    }

    public void setWrappedService(ModelRunnerService modelRunnerService) {
        _modelRunnerService = modelRunnerService;
    }
}
