package com.ext.portlet.contests.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseColumnService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseColumnService
 * @generated
 */
public class ContestPhaseColumnServiceWrapper
    implements ContestPhaseColumnService,
        ServiceWrapper<ContestPhaseColumnService> {
    private ContestPhaseColumnService _contestPhaseColumnService;

    public ContestPhaseColumnServiceWrapper(
        ContestPhaseColumnService contestPhaseColumnService) {
        _contestPhaseColumnService = contestPhaseColumnService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestPhaseColumnService getWrappedContestPhaseColumnService() {
        return _contestPhaseColumnService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestPhaseColumnService(
        ContestPhaseColumnService contestPhaseColumnService) {
        _contestPhaseColumnService = contestPhaseColumnService;
    }

    public ContestPhaseColumnService getWrappedService() {
        return _contestPhaseColumnService;
    }

    public void setWrappedService(
        ContestPhaseColumnService contestPhaseColumnService) {
        _contestPhaseColumnService = contestPhaseColumnService;
    }
}
