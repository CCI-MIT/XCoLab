package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OntologySpaceService}.
 *
 * @author Brian Wing Shun Chan
 * @see OntologySpaceService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ontologySpaceService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ontologySpaceService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ontologySpaceService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public OntologySpaceService getWrappedOntologySpaceService() {
        return _ontologySpaceService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedOntologySpaceService(
        OntologySpaceService ontologySpaceService) {
        _ontologySpaceService = ontologySpaceService;
    }

    @Override
    public OntologySpaceService getWrappedService() {
        return _ontologySpaceService;
    }

    @Override
    public void setWrappedService(OntologySpaceService ontologySpaceService) {
        _ontologySpaceService = ontologySpaceService;
    }
}
