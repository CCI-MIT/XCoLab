package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RolesCategoryService}.
 *
 * @author Brian Wing Shun Chan
 * @see RolesCategoryService
 * @generated
 */
public class RolesCategoryServiceWrapper implements RolesCategoryService,
    ServiceWrapper<RolesCategoryService> {
    private RolesCategoryService _rolesCategoryService;

    public RolesCategoryServiceWrapper(
        RolesCategoryService rolesCategoryService) {
        _rolesCategoryService = rolesCategoryService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _rolesCategoryService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _rolesCategoryService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _rolesCategoryService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RolesCategoryService getWrappedRolesCategoryService() {
        return _rolesCategoryService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRolesCategoryService(
        RolesCategoryService rolesCategoryService) {
        _rolesCategoryService = rolesCategoryService;
    }

    @Override
    public RolesCategoryService getWrappedService() {
        return _rolesCategoryService;
    }

    @Override
    public void setWrappedService(RolesCategoryService rolesCategoryService) {
        _rolesCategoryService = rolesCategoryService;
    }
}
