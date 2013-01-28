package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelGlobalPreferenceService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelGlobalPreferenceService
 * @generated
 */
public class ModelGlobalPreferenceServiceWrapper
    implements ModelGlobalPreferenceService,
        ServiceWrapper<ModelGlobalPreferenceService> {
    private ModelGlobalPreferenceService _modelGlobalPreferenceService;

    public ModelGlobalPreferenceServiceWrapper(
        ModelGlobalPreferenceService modelGlobalPreferenceService) {
        _modelGlobalPreferenceService = modelGlobalPreferenceService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelGlobalPreferenceService getWrappedModelGlobalPreferenceService() {
        return _modelGlobalPreferenceService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelGlobalPreferenceService(
        ModelGlobalPreferenceService modelGlobalPreferenceService) {
        _modelGlobalPreferenceService = modelGlobalPreferenceService;
    }

    public ModelGlobalPreferenceService getWrappedService() {
        return _modelGlobalPreferenceService;
    }

    public void setWrappedService(
        ModelGlobalPreferenceService modelGlobalPreferenceService) {
        _modelGlobalPreferenceService = modelGlobalPreferenceService;
    }
}
