package com.ext.portlet.contests.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestService
 * @generated
 */
public class ContestServiceWrapper implements ContestService,
    ServiceWrapper<ContestService> {
    private ContestService _contestService;

    public ContestServiceWrapper(ContestService contestService) {
        _contestService = contestService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestService getWrappedContestService() {
        return _contestService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestService(ContestService contestService) {
        _contestService = contestService;
    }

    public ContestService getWrappedService() {
        return _contestService;
    }

    public void setWrappedService(ContestService contestService) {
        _contestService = contestService;
    }
}
