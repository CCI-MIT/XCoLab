package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Users_RolesService}.
 *
 * @author Brian Wing Shun Chan
 * @see Users_RolesService
 * @generated
 */
public class Users_RolesServiceWrapper implements Users_RolesService,
    ServiceWrapper<Users_RolesService> {
    private Users_RolesService _users_RolesService;

    public Users_RolesServiceWrapper(Users_RolesService users_RolesService) {
        _users_RolesService = users_RolesService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _users_RolesService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _users_RolesService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _users_RolesService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Users_RolesService getWrappedUsers_RolesService() {
        return _users_RolesService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedUsers_RolesService(
        Users_RolesService users_RolesService) {
        _users_RolesService = users_RolesService;
    }

    @Override
    public Users_RolesService getWrappedService() {
        return _users_RolesService;
    }

    @Override
    public void setWrappedService(Users_RolesService users_RolesService) {
        _users_RolesService = users_RolesService;
    }
}
