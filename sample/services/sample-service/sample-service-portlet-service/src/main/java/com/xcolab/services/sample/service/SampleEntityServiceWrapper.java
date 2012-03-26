package com.xcolab.services.sample.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SampleEntityService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SampleEntityService
 * @generated
 */
public class SampleEntityServiceWrapper implements SampleEntityService,
    ServiceWrapper<SampleEntityService> {
    private SampleEntityService _sampleEntityService;

    public SampleEntityServiceWrapper(SampleEntityService sampleEntityService) {
        _sampleEntityService = sampleEntityService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public SampleEntityService getWrappedSampleEntityService() {
        return _sampleEntityService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedSampleEntityService(
        SampleEntityService sampleEntityService) {
        _sampleEntityService = sampleEntityService;
    }

    public SampleEntityService getWrappedService() {
        return _sampleEntityService;
    }

    public void setWrappedService(SampleEntityService sampleEntityService) {
        _sampleEntityService = sampleEntityService;
    }
}
