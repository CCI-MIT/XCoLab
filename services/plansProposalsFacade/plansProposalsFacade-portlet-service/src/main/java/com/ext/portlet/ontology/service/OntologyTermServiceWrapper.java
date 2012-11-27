package com.ext.portlet.ontology.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologyTermService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologyTermService
 * @generated
 */
public class OntologyTermServiceWrapper implements OntologyTermService,
    ServiceWrapper<OntologyTermService> {
    private OntologyTermService _ontologyTermService;

    public OntologyTermServiceWrapper(OntologyTermService ontologyTermService) {
        _ontologyTermService = ontologyTermService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public OntologyTermService getWrappedOntologyTermService() {
        return _ontologyTermService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedOntologyTermService(
        OntologyTermService ontologyTermService) {
        _ontologyTermService = ontologyTermService;
    }

    public OntologyTermService getWrappedService() {
        return _ontologyTermService;
    }

    public void setWrappedService(OntologyTermService ontologyTermService) {
        _ontologyTermService = ontologyTermService;
    }
}
