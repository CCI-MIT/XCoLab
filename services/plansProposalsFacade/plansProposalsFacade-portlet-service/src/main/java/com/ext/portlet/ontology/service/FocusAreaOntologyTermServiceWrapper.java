package com.ext.portlet.ontology.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FocusAreaOntologyTermService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FocusAreaOntologyTermService
 * @generated
 */
public class FocusAreaOntologyTermServiceWrapper
    implements FocusAreaOntologyTermService,
        ServiceWrapper<FocusAreaOntologyTermService> {
    private FocusAreaOntologyTermService _focusAreaOntologyTermService;

    public FocusAreaOntologyTermServiceWrapper(
        FocusAreaOntologyTermService focusAreaOntologyTermService) {
        _focusAreaOntologyTermService = focusAreaOntologyTermService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FocusAreaOntologyTermService getWrappedFocusAreaOntologyTermService() {
        return _focusAreaOntologyTermService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFocusAreaOntologyTermService(
        FocusAreaOntologyTermService focusAreaOntologyTermService) {
        _focusAreaOntologyTermService = focusAreaOntologyTermService;
    }

    public FocusAreaOntologyTermService getWrappedService() {
        return _focusAreaOntologyTermService;
    }

    public void setWrappedService(
        FocusAreaOntologyTermService focusAreaOntologyTermService) {
        _focusAreaOntologyTermService = focusAreaOntologyTermService;
    }
}
