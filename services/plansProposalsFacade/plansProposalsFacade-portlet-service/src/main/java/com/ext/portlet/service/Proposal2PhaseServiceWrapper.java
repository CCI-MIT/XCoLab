package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Proposal2PhaseService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Proposal2PhaseService
 * @generated
 */
public class Proposal2PhaseServiceWrapper implements Proposal2PhaseService,
    ServiceWrapper<Proposal2PhaseService> {
    private Proposal2PhaseService _proposal2PhaseService;

    public Proposal2PhaseServiceWrapper(
        Proposal2PhaseService proposal2PhaseService) {
        _proposal2PhaseService = proposal2PhaseService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public Proposal2PhaseService getWrappedProposal2PhaseService() {
        return _proposal2PhaseService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposal2PhaseService(
        Proposal2PhaseService proposal2PhaseService) {
        _proposal2PhaseService = proposal2PhaseService;
    }

    public Proposal2PhaseService getWrappedService() {
        return _proposal2PhaseService;
    }

    public void setWrappedService(Proposal2PhaseService proposal2PhaseService) {
        _proposal2PhaseService = proposal2PhaseService;
    }
}
