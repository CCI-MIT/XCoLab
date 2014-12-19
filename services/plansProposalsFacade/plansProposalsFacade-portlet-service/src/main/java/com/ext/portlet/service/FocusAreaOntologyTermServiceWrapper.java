package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FocusAreaOntologyTermService}.
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _focusAreaOntologyTermService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _focusAreaOntologyTermService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _focusAreaOntologyTermService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public FocusAreaOntologyTermService getWrappedFocusAreaOntologyTermService() {
        return _focusAreaOntologyTermService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedFocusAreaOntologyTermService(
        FocusAreaOntologyTermService focusAreaOntologyTermService) {
        _focusAreaOntologyTermService = focusAreaOntologyTermService;
    }

    @Override
    public FocusAreaOntologyTermService getWrappedService() {
        return _focusAreaOntologyTermService;
    }

    @Override
    public void setWrappedService(
        FocusAreaOntologyTermService focusAreaOntologyTermService) {
        _focusAreaOntologyTermService = focusAreaOntologyTermService;
    }
}
