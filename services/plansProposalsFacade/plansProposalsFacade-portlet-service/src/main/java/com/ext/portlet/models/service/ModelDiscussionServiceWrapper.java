package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelDiscussionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelDiscussionService
 * @generated
 */
public class ModelDiscussionServiceWrapper implements ModelDiscussionService,
    ServiceWrapper<ModelDiscussionService> {
    private ModelDiscussionService _modelDiscussionService;

    public ModelDiscussionServiceWrapper(
        ModelDiscussionService modelDiscussionService) {
        _modelDiscussionService = modelDiscussionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelDiscussionService getWrappedModelDiscussionService() {
        return _modelDiscussionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelDiscussionService(
        ModelDiscussionService modelDiscussionService) {
        _modelDiscussionService = modelDiscussionService;
    }

    public ModelDiscussionService getWrappedService() {
        return _modelDiscussionService;
    }

    public void setWrappedService(ModelDiscussionService modelDiscussionService) {
        _modelDiscussionService = modelDiscussionService;
    }
}
