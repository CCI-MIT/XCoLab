package com.ext.portlet.ontology.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FocusAreaService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FocusAreaService
 * @generated
 */
public class FocusAreaServiceWrapper implements FocusAreaService,
    ServiceWrapper<FocusAreaService> {
    private FocusAreaService _focusAreaService;

    public FocusAreaServiceWrapper(FocusAreaService focusAreaService) {
        _focusAreaService = focusAreaService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FocusAreaService getWrappedFocusAreaService() {
        return _focusAreaService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFocusAreaService(FocusAreaService focusAreaService) {
        _focusAreaService = focusAreaService;
    }

    public FocusAreaService getWrappedService() {
        return _focusAreaService;
    }

    public void setWrappedService(FocusAreaService focusAreaService) {
        _focusAreaService = focusAreaService;
    }
}
