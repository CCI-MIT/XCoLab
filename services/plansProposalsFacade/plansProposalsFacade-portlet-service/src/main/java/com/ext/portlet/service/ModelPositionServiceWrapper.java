package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelPositionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelPositionService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelPositionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelPositionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelPositionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelPositionService getWrappedModelPositionService() {
        return _modelPositionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelPositionService(
        ModelPositionService modelPositionService) {
        _modelPositionService = modelPositionService;
    }

    @Override
    public ModelPositionService getWrappedService() {
        return _modelPositionService;
    }

    @Override
    public void setWrappedService(ModelPositionService modelPositionService) {
        _modelPositionService = modelPositionService;
    }
}
