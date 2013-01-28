package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseTypeService
 * @generated
 */
public class ContestPhaseTypeServiceWrapper implements ContestPhaseTypeService,
    ServiceWrapper<ContestPhaseTypeService> {
    private ContestPhaseTypeService _contestPhaseTypeService;

    public ContestPhaseTypeServiceWrapper(
        ContestPhaseTypeService contestPhaseTypeService) {
        _contestPhaseTypeService = contestPhaseTypeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestPhaseTypeService getWrappedContestPhaseTypeService() {
        return _contestPhaseTypeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestPhaseTypeService(
        ContestPhaseTypeService contestPhaseTypeService) {
        _contestPhaseTypeService = contestPhaseTypeService;
    }

    public ContestPhaseTypeService getWrappedService() {
        return _contestPhaseTypeService;
    }

    public void setWrappedService(
        ContestPhaseTypeService contestPhaseTypeService) {
        _contestPhaseTypeService = contestPhaseTypeService;
    }
}
