package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologySpaceService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologySpaceService
 * @generated
 */
public class OntologySpaceServiceWrapper implements OntologySpaceService,
    ServiceWrapper<OntologySpaceService> {
    private OntologySpaceService _ontologySpaceService;

    public OntologySpaceServiceWrapper(
        OntologySpaceService ontologySpaceService) {
        _ontologySpaceService = ontologySpaceService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public OntologySpaceService getWrappedOntologySpaceService() {
        return _ontologySpaceService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedOntologySpaceService(
        OntologySpaceService ontologySpaceService) {
        _ontologySpaceService = ontologySpaceService;
    }

    public OntologySpaceService getWrappedService() {
        return _ontologySpaceService;
    }

    public void setWrappedService(OntologySpaceService ontologySpaceService) {
        _ontologySpaceService = ontologySpaceService;
    }
}
