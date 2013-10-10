package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelRunnerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelRunnerLocalService
 * @generated
 */
public class ModelRunnerLocalServiceWrapper implements ModelRunnerLocalService,
    ServiceWrapper<ModelRunnerLocalService> {
    private ModelRunnerLocalService _modelRunnerLocalService;

    public ModelRunnerLocalServiceWrapper(
        ModelRunnerLocalService modelRunnerLocalService) {
        _modelRunnerLocalService = modelRunnerLocalService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _modelRunnerLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelRunnerLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelRunnerLocalService getWrappedModelRunnerLocalService() {
        return _modelRunnerLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelRunnerLocalService(
        ModelRunnerLocalService modelRunnerLocalService) {
        _modelRunnerLocalService = modelRunnerLocalService;
    }

    public ModelRunnerLocalService getWrappedService() {
        return _modelRunnerLocalService;
    }

    public void setWrappedService(
        ModelRunnerLocalService modelRunnerLocalService) {
        _modelRunnerLocalService = modelRunnerLocalService;
    }
}
