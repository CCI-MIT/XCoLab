package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestDebateService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestDebateService
 * @generated
 */
public class ContestDebateServiceWrapper implements ContestDebateService,
    ServiceWrapper<ContestDebateService> {
    private ContestDebateService _contestDebateService;

    public ContestDebateServiceWrapper(
        ContestDebateService contestDebateService) {
        _contestDebateService = contestDebateService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestDebateService getWrappedContestDebateService() {
        return _contestDebateService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestDebateService(
        ContestDebateService contestDebateService) {
        _contestDebateService = contestDebateService;
    }

    public ContestDebateService getWrappedService() {
        return _contestDebateService;
    }

    public void setWrappedService(ContestDebateService contestDebateService) {
        _contestDebateService = contestDebateService;
    }
}
