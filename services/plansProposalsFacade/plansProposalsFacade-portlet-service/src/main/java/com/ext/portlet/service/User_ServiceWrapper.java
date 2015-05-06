package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link User_Service}.
 *
 * @author Brian Wing Shun Chan
 * @see User_Service
 * @generated
 */
public class User_ServiceWrapper implements User_Service,
    ServiceWrapper<User_Service> {
    private User_Service _user_Service;

    public User_ServiceWrapper(User_Service user_Service) {
        _user_Service = user_Service;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _user_Service.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _user_Service.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _user_Service.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public User_Service getWrappedUser_Service() {
        return _user_Service;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedUser_Service(User_Service user_Service) {
        _user_Service = user_Service;
    }

    @Override
    public User_Service getWrappedService() {
        return _user_Service;
    }

    @Override
    public void setWrappedService(User_Service user_Service) {
        _user_Service = user_Service;
    }
}
