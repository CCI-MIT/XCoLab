package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestTeamMemberService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberService
 * @generated
 */
public class ContestTeamMemberServiceWrapper implements ContestTeamMemberService,
    ServiceWrapper<ContestTeamMemberService> {
    private ContestTeamMemberService _contestTeamMemberService;

    public ContestTeamMemberServiceWrapper(
        ContestTeamMemberService contestTeamMemberService) {
        _contestTeamMemberService = contestTeamMemberService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestTeamMemberService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestTeamMemberService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestTeamMemberService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestTeamMemberService getWrappedContestTeamMemberService() {
        return _contestTeamMemberService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestTeamMemberService(
        ContestTeamMemberService contestTeamMemberService) {
        _contestTeamMemberService = contestTeamMemberService;
    }

    @Override
    public ContestTeamMemberService getWrappedService() {
        return _contestTeamMemberService;
    }

    @Override
    public void setWrappedService(
        ContestTeamMemberService contestTeamMemberService) {
        _contestTeamMemberService = contestTeamMemberService;
    }
}
