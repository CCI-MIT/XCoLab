package com.ext.portlet.service.impl;

import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.base.ProposalVoteLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalVotePK;

/**
 * The implementation of the proposal vote local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalVoteLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalVoteLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalVoteLocalServiceUtil
 */
public class ProposalVoteLocalServiceImpl
    extends ProposalVoteLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalVoteLocalServiceUtil} to access the proposal vote local service.
     */

    public ProposalVote create(long contestPhaseId, long userID) {
        return createProposalVote(new ProposalVotePK(contestPhaseId, userID));
    }

    public ProposalVote findByProposalIdContestPhaseIdUserId(long contestPhaseId, long userId) throws com.ext.portlet.NoSuchProposalVoteException,
    com.liferay.portal.kernel.exception.SystemException {
        return proposalVotePersistence.findByContestPhaseIdUserId(contestPhaseId, userId);
    }

}
