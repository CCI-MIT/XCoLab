package com.ext.portlet.contests.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseService
 * @generated
 */
public class ContestPhaseServiceWrapper implements ContestPhaseService,
    ServiceWrapper<ContestPhaseService> {
    private ContestPhaseService _contestPhaseService;

    public ContestPhaseServiceWrapper(ContestPhaseService contestPhaseService) {
        _contestPhaseService = contestPhaseService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestPhaseService getWrappedContestPhaseService() {
        return _contestPhaseService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestPhaseService(
        ContestPhaseService contestPhaseService) {
        _contestPhaseService = contestPhaseService;
    }

    public ContestPhaseService getWrappedService() {
        return _contestPhaseService;
    }

    public void setWrappedService(ContestPhaseService contestPhaseService) {
        _contestPhaseService = contestPhaseService;
    }
}
