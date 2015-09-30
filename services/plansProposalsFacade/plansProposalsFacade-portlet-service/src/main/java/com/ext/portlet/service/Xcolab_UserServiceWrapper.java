package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Xcolab_UserService}.
 *
 * @author Brian Wing Shun Chan
 * @see Xcolab_UserService
 * @generated
 */
public class Xcolab_UserServiceWrapper implements Xcolab_UserService,
    ServiceWrapper<Xcolab_UserService> {
    private Xcolab_UserService _xcolab_UserService;

    public Xcolab_UserServiceWrapper(Xcolab_UserService xcolab_UserService) {
        _xcolab_UserService = xcolab_UserService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _xcolab_UserService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _xcolab_UserService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _xcolab_UserService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Xcolab_UserService getWrappedXcolab_UserService() {
        return _xcolab_UserService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedXcolab_UserService(
        Xcolab_UserService xcolab_UserService) {
        _xcolab_UserService = xcolab_UserService;
    }

    @Override
    public Xcolab_UserService getWrappedService() {
        return _xcolab_UserService;
    }

    @Override
    public void setWrappedService(Xcolab_UserService xcolab_UserService) {
        _xcolab_UserService = xcolab_UserService;
    }
}
