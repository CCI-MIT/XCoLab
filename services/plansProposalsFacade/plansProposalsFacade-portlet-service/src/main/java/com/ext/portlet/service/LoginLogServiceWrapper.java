package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoginLogService}.
 *
 * @author Brian Wing Shun Chan
 * @see LoginLogService
 * @generated
 */
public class LoginLogServiceWrapper implements LoginLogService,
    ServiceWrapper<LoginLogService> {
    private LoginLogService _loginLogService;

    public LoginLogServiceWrapper(LoginLogService loginLogService) {
        _loginLogService = loginLogService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _loginLogService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _loginLogService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _loginLogService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public LoginLogService getWrappedLoginLogService() {
        return _loginLogService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedLoginLogService(LoginLogService loginLogService) {
        _loginLogService = loginLogService;
    }

    @Override
    public LoginLogService getWrappedService() {
        return _loginLogService;
    }

    @Override
    public void setWrappedService(LoginLogService loginLogService) {
        _loginLogService = loginLogService;
    }
}
