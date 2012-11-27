package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelOutputChartOrderService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelOutputChartOrderService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelOutputChartOrderService getWrappedModelOutputChartOrderService() {
        return _modelOutputChartOrderService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelOutputChartOrderService(
        ModelOutputChartOrderService modelOutputChartOrderService) {
        _modelOutputChartOrderService = modelOutputChartOrderService;
    }

    public ModelOutputChartOrderService getWrappedService() {
        return _modelOutputChartOrderService;
    }

    public void setWrappedService(
        ModelOutputChartOrderService modelOutputChartOrderService) {
        _modelOutputChartOrderService = modelOutputChartOrderService;
    }
}
