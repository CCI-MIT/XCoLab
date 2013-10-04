package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseRibbonTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseRibbonTypeService
 * @generated
 */
public class ContestPhaseRibbonTypeServiceWrapper
    implements ContestPhaseRibbonTypeService,
        ServiceWrapper<ContestPhaseRibbonTypeService> {
    private ContestPhaseRibbonTypeService _contestPhaseRibbonTypeService;

    public ContestPhaseRibbonTypeServiceWrapper(
        ContestPhaseRibbonTypeService contestPhaseRibbonTypeService) {
        _contestPhaseRibbonTypeService = contestPhaseRibbonTypeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestPhaseRibbonTypeService getWrappedContestPhaseRibbonTypeService() {
        return _contestPhaseRibbonTypeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestPhaseRibbonTypeService(
        ContestPhaseRibbonTypeService contestPhaseRibbonTypeService) {
        _contestPhaseRibbonTypeService = contestPhaseRibbonTypeService;
    }

    public ContestPhaseRibbonTypeService getWrappedService() {
        return _contestPhaseRibbonTypeService;
    }

    public void setWrappedService(
        ContestPhaseRibbonTypeService contestPhaseRibbonTypeService) {
        _contestPhaseRibbonTypeService = contestPhaseRibbonTypeService;
    }
}
