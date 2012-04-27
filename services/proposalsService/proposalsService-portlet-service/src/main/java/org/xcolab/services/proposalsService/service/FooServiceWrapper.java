package org.xcolab.services.proposalsService.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FooService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FooService
 * @generated
 */
public class FooServiceWrapper implements FooService,
    ServiceWrapper<FooService> {
    private FooService _fooService;

    public FooServiceWrapper(FooService fooService) {
        _fooService = fooService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FooService getWrappedFooService() {
        return _fooService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFooService(FooService fooService) {
        _fooService = fooService;
    }

    public FooService getWrappedService() {
        return _fooService;
    }

    public void setWrappedService(FooService fooService) {
        _fooService = fooService;
    }
}
