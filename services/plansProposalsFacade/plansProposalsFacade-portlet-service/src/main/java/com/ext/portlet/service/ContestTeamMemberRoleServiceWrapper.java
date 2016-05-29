package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestTeamMemberRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberRoleService
 * @generated
 */
public class ContestTeamMemberRoleServiceWrapper
    implements ContestTeamMemberRoleService,
        ServiceWrapper<ContestTeamMemberRoleService> {
    private ContestTeamMemberRoleService _contestTeamMemberRoleService;

    public ContestTeamMemberRoleServiceWrapper(
        ContestTeamMemberRoleService contestTeamMemberRoleService) {
        _contestTeamMemberRoleService = contestTeamMemberRoleService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestTeamMemberRoleService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestTeamMemberRoleService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestTeamMemberRoleService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestTeamMemberRoleService getWrappedContestTeamMemberRoleService() {
        return _contestTeamMemberRoleService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestTeamMemberRoleService(
        ContestTeamMemberRoleService contestTeamMemberRoleService) {
        _contestTeamMemberRoleService = contestTeamMemberRoleService;
    }

    @Override
    public ContestTeamMemberRoleService getWrappedService() {
        return _contestTeamMemberRoleService;
    }

    @Override
    public void setWrappedService(
        ContestTeamMemberRoleService contestTeamMemberRoleService) {
        _contestTeamMemberRoleService = contestTeamMemberRoleService;
    }
}
