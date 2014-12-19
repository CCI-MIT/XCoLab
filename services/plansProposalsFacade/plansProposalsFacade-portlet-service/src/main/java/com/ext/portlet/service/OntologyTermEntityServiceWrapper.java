package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OntologyTermEntityService}.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntityService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ontologyTermEntityService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ontologyTermEntityService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ontologyTermEntityService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public OntologyTermEntityService getWrappedOntologyTermEntityService() {
        return _ontologyTermEntityService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedOntologyTermEntityService(
        OntologyTermEntityService ontologyTermEntityService) {
        _ontologyTermEntityService = ontologyTermEntityService;
    }

    @Override
    public OntologyTermEntityService getWrappedService() {
        return _ontologyTermEntityService;
    }

    @Override
    public void setWrappedService(
        OntologyTermEntityService ontologyTermEntityService) {
        _ontologyTermEntityService = ontologyTermEntityService;
    }
}
