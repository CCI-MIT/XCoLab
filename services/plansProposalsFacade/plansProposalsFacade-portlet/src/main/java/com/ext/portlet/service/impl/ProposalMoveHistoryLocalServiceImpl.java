package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalMoveHistoryException;
import com.ext.portlet.model.ProposalMoveHistory;
import com.ext.portlet.service.base.ProposalMoveHistoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import org.joda.time.DateTime;

import java.util.List;

/**
 * The implementation of the proposal move history local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalMoveHistoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalMoveHistoryLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil
 */
public class ProposalMoveHistoryLocalServiceImpl
    extends ProposalMoveHistoryLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil} to access the proposal move history local service.
     */

    @Override
    public ProposalMoveHistory create(long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
            long sourcePhaseId, long targetPhaseId, String moveType) throws SystemException {
        final ProposalMoveHistory proposalMoveHistory = proposalMoveHistoryPersistence
                .create(counterLocalService.increment(ProposalMoveHistory.class.getName()));
        proposalMoveHistory.setSourceProposalId(srcProposalId);
        proposalMoveHistory.setTargetProposalId(targetProposalId);

        proposalMoveHistory.setSourceContestId(srcContestId);
        proposalMoveHistory.setTargetContestId(targetContestId);

        proposalMoveHistory.setSourcePhaseId(sourcePhaseId);
        proposalMoveHistory.setTargetPhaseId(targetPhaseId);

        proposalMoveHistory.setMoveDate(DateTime.now().toDate());
        proposalMoveHistory.setMoveType(moveType);

        proposalMoveHistory.persist();

        return proposalMoveHistory;
    }

    @Override
    public ProposalMoveHistory createMoveHistory(long proposalId, long srcContestId, long targetContestId,
            long srcPhaseId, long targetPhaseId) throws SystemException {
        return create(proposalId, proposalId, srcContestId, targetContestId, srcPhaseId, targetPhaseId, "MOVE_PERMANENTLY");
    }

    @Override
    public ProposalMoveHistory createCopyHistory(long proposalId, long srcContestId, long targetContestId,
            long srcPhaseId, long targetPhaseId) throws SystemException {
        return create(proposalId, proposalId, srcContestId, targetContestId, srcPhaseId, targetPhaseId, "COPY");
    }

    @Override
    public ProposalMoveHistory createForkHistory(long srcProposalId, long targetProposalId, long srcContestId, long targetContestId,
            long srcPhaseId, long targetPhaseId) throws SystemException {
        return create(srcProposalId, targetProposalId, srcContestId, targetContestId, srcPhaseId, targetPhaseId, "COPY");
    }

    @Override
    public List<ProposalMoveHistory> getBySourceProposalId(long srcProposalId) throws SystemException {
        return proposalMoveHistoryPersistence.findBySourceProposalId(srcProposalId);
    }

    @Override
    public List<ProposalMoveHistory> getBySourceContestId(long srcContestId) throws SystemException {
        return proposalMoveHistoryPersistence.findBySourceContestId(srcContestId);
    }

    @Override
    public List<ProposalMoveHistory> getBySourcePhaseId(long srcPhaseId) throws SystemException {
        return proposalMoveHistoryPersistence.findBySourcePhaseId(srcPhaseId);
    }

    @Override
    public List<ProposalMoveHistory> getByTargetProposalId(long targetProposalId) throws SystemException {
        return proposalMoveHistoryPersistence.findByTargetProposalId(targetProposalId);
    }

    @Override
    public List<ProposalMoveHistory> getByTargetContestId(long targetContestId) throws SystemException {
        return proposalMoveHistoryPersistence.findByTargetContestId(targetContestId);
    }

    @Override
    public List<ProposalMoveHistory> getByTargetPhaseId(long targetPhaseId) throws SystemException {
        return proposalMoveHistoryPersistence.findByTargetPhaseId(targetPhaseId);
    }

    @Override
    public List<ProposalMoveHistory> getBySourceProposalIdContestId(long srcProposalId, long srcContestId) throws SystemException {
        return proposalMoveHistoryPersistence.findBySourceProposalIdContestId(srcProposalId, srcContestId);
    }

    @Override
    public ProposalMoveHistory getByTargetProposalIdContestId(long targetProposalId, long targetContestId)
            throws NoSuchProposalMoveHistoryException, SystemException {
        return proposalMoveHistoryPersistence.findByTargetProposalIdContestId(targetProposalId, targetContestId);
    }
}
