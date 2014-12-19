package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelDiscussionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussionService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelDiscussionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelDiscussionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelDiscussionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelDiscussionService getWrappedModelDiscussionService() {
        return _modelDiscussionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelDiscussionService(
        ModelDiscussionService modelDiscussionService) {
        _modelDiscussionService = modelDiscussionService;
    }

    @Override
    public ModelDiscussionService getWrappedService() {
        return _modelDiscussionService;
    }

    @Override
    public void setWrappedService(ModelDiscussionService modelDiscussionService) {
        _modelDiscussionService = modelDiscussionService;
    }
}
