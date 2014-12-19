package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OntologyTermService}.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermService
 * @generated
 */
public class OntologyTermServiceWrapper implements OntologyTermService,
    ServiceWrapper<OntologyTermService> {
    private OntologyTermService _ontologyTermService;

    public OntologyTermServiceWrapper(OntologyTermService ontologyTermService) {
        _ontologyTermService = ontologyTermService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ontologyTermService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ontologyTermService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ontologyTermService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public OntologyTermService getWrappedOntologyTermService() {
        return _ontologyTermService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedOntologyTermService(
        OntologyTermService ontologyTermService) {
        _ontologyTermService = ontologyTermService;
    }

    @Override
    public OntologyTermService getWrappedService() {
        return _ontologyTermService;
    }

    @Override
    public void setWrappedService(OntologyTermService ontologyTermService) {
        _ontologyTermService = ontologyTermService;
    }
}
