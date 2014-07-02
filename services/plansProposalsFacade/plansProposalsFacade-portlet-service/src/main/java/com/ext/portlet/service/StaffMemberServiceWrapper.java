package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StaffMemberService}.
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberService
 * @generated
 */
public class StaffMemberServiceWrapper implements StaffMemberService,
    ServiceWrapper<StaffMemberService> {
    private StaffMemberService _staffMemberService;

    public StaffMemberServiceWrapper(StaffMemberService staffMemberService) {
        _staffMemberService = staffMemberService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _staffMemberService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _staffMemberService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _staffMemberService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StaffMemberService getWrappedStaffMemberService() {
        return _staffMemberService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStaffMemberService(
        StaffMemberService staffMemberService) {
        _staffMemberService = staffMemberService;
    }

    @Override
    public StaffMemberService getWrappedService() {
        return _staffMemberService;
    }

    @Override
    public void setWrappedService(StaffMemberService staffMemberService) {
        _staffMemberService = staffMemberService;
    }
}
