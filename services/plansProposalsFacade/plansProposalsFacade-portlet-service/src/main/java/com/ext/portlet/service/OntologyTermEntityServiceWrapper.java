package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologyTermEntityService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologyTermEntityService
 * @generated
 */
public class OntologyTermEntityServiceWrapper
    implements OntologyTermEntityService,
        ServiceWrapper<OntologyTermEntityService> {
    private OntologyTermEntityService _ontologyTermEntityService;

    public OntologyTermEntityServiceWrapper(
        OntologyTermEntityService ontologyTermEntityService) {
        _ontologyTermEntityService = ontologyTermEntityService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public OntologyTermEntityService getWrappedOntologyTermEntityService() {
        return _ontologyTermEntityService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedOntologyTermEntityService(
        OntologyTermEntityService ontologyTermEntityService) {
        _ontologyTermEntityService = ontologyTermEntityService;
    }

    public OntologyTermEntityService getWrappedService() {
        return _ontologyTermEntityService;
    }

    public void setWrappedService(
        OntologyTermEntityService ontologyTermEntityService) {
        _ontologyTermEntityService = ontologyTermEntityService;
    }
}
