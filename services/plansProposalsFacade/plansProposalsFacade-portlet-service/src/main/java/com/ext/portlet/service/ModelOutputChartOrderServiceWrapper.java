package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelOutputChartOrderService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputChartOrderService
 * @generated
 */
public class ModelOutputChartOrderServiceWrapper
    implements ModelOutputChartOrderService,
        ServiceWrapper<ModelOutputChartOrderService> {
    private ModelOutputChartOrderService _modelOutputChartOrderService;

    public ModelOutputChartOrderServiceWrapper(
        ModelOutputChartOrderService modelOutputChartOrderService) {
        _modelOutputChartOrderService = modelOutputChartOrderService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelOutputChartOrderService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelOutputChartOrderService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelOutputChartOrderService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelOutputChartOrderService getWrappedModelOutputChartOrderService() {
        return _modelOutputChartOrderService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelOutputChartOrderService(
        ModelOutputChartOrderService modelOutputChartOrderService) {
        _modelOutputChartOrderService = modelOutputChartOrderService;
    }

    @Override
    public ModelOutputChartOrderService getWrappedService() {
        return _modelOutputChartOrderService;
    }

    @Override
    public void setWrappedService(
        ModelOutputChartOrderService modelOutputChartOrderService) {
        _modelOutputChartOrderService = modelOutputChartOrderService;
    }
}
