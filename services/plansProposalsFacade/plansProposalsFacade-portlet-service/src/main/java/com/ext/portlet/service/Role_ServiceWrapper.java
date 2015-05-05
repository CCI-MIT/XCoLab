package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Role_Service}.
 *
 * @author Brian Wing Shun Chan
 * @see Role_Service
 * @generated
 */
public class Role_ServiceWrapper implements Role_Service,
    ServiceWrapper<Role_Service> {
    private Role_Service _role_Service;

    public Role_ServiceWrapper(Role_Service role_Service) {
        _role_Service = role_Service;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _role_Service.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _role_Service.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _role_Service.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Role_Service getWrappedRole_Service() {
        return _role_Service;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRole_Service(Role_Service role_Service) {
        _role_Service = role_Service;
    }

    @Override
    public Role_Service getWrappedService() {
        return _role_Service;
    }

    @Override
    public void setWrappedService(Role_Service role_Service) {
        _role_Service = role_Service;
    }
}
