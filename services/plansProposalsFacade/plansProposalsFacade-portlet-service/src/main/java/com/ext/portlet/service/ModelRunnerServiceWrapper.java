package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelRunnerService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelRunnerService
 * @generated
 */
public class ModelRunnerServiceWrapper implements ModelRunnerService,
    ServiceWrapper<ModelRunnerService> {
    private ModelRunnerService _modelRunnerService;

    public ModelRunnerServiceWrapper(ModelRunnerService modelRunnerService) {
        _modelRunnerService = modelRunnerService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelRunnerService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelRunnerService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelRunnerService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject getScenario(
        long scenarioId) {
        return _modelRunnerService.getScenario(scenarioId);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject getModel(long modelId)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return _modelRunnerService.getModel(modelId);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject runModel(long modelId,
        java.lang.String inputs)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.json.JSONException,
            edu.mit.cci.roma.client.comm.ModelNotFoundException,
            edu.mit.cci.roma.client.comm.ScenarioNotFoundException,
            java.io.IOException {
        return _modelRunnerService.runModel(modelId, inputs);
    }

    @Override
    public void refreshModels()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        _modelRunnerService.refreshModels();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelRunnerService getWrappedModelRunnerService() {
        return _modelRunnerService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelRunnerService(
        ModelRunnerService modelRunnerService) {
        _modelRunnerService = modelRunnerService;
    }

    @Override
    public ModelRunnerService getWrappedService() {
        return _modelRunnerService;
    }

    @Override
    public void setWrappedService(ModelRunnerService modelRunnerService) {
        _modelRunnerService = modelRunnerService;
    }
}
