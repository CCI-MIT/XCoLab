package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.base.Proposal2PhaseLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
/**
 * The implementation of the proposal2 phase local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.Proposal2PhaseLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.Proposal2PhaseLocalServiceBaseImpl
 * @see com.ext.portlet.service.Proposal2PhaseLocalServiceUtil
 */
public class Proposal2PhaseLocalServiceImpl
    extends Proposal2PhaseLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.Proposal2PhaseLocalServiceUtil} to access the proposal2 phase local service.
     */

    public Proposal2Phase create(long proposalId, long contestPhaseId) {
        return createProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
    }
    
    public Proposal2Phase getByProposalIdContestPhaseId(long proposalId, long contestPhaseId) throws PortalException, SystemException {
        return getProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
    }
    
    public Contest getCurrentContestForProposal(long proposalId) throws SystemException, PortalException {
        List<Proposal2Phase> proposal2Phases = proposal2PhasePersistence.findByProposalId(proposalId);
        if (proposal2Phases.isEmpty()) {
            throw new SystemException("Proposal " + proposalId + " isn't associated with any contest");
        }
        ContestPhase phase = contestPhaseLocalService.getContestPhase(proposal2Phases.get(proposal2Phases.size()-1).getContestPhaseId());
        return contestLocalService.getContest(phase.getContestPK());
    }
}
