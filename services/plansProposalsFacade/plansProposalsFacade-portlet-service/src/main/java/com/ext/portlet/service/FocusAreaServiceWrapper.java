package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FocusAreaService}.
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaService
 * @generated
 */
public class FocusAreaServiceWrapper implements FocusAreaService,
    ServiceWrapper<FocusAreaService> {
    private FocusAreaService _focusAreaService;

    public FocusAreaServiceWrapper(FocusAreaService focusAreaService) {
        _focusAreaService = focusAreaService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _focusAreaService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _focusAreaService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _focusAreaService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public FocusAreaService getWrappedFocusAreaService() {
        return _focusAreaService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedFocusAreaService(FocusAreaService focusAreaService) {
        _focusAreaService = focusAreaService;
    }

    @Override
    public FocusAreaService getWrappedService() {
        return _focusAreaService;
    }

    @Override
    public void setWrappedService(FocusAreaService focusAreaService) {
        _focusAreaService = focusAreaService;
    }
}
