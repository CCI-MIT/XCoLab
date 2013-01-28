package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestTeamMemberService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestTeamMemberService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestTeamMemberService getWrappedContestTeamMemberService() {
        return _contestTeamMemberService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestTeamMemberService(
        ContestTeamMemberService contestTeamMemberService) {
        _contestTeamMemberService = contestTeamMemberService;
    }

    public ContestTeamMemberService getWrappedService() {
        return _contestTeamMemberService;
    }

    public void setWrappedService(
        ContestTeamMemberService contestTeamMemberService) {
        _contestTeamMemberService = contestTeamMemberService;
    }
}
