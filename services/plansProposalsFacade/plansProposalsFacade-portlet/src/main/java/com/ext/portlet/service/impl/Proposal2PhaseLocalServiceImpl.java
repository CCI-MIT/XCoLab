package com.ext.portlet.service.impl;

import com.ext.portlet.service.base.Proposal2PhaseLocalServiceBaseImpl;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
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

    public Proposal2Phase create(Long proposalId, Long contestPhaseId) {
        return createProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
    }
}
