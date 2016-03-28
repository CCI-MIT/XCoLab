package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.ProposalMoveHistory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.joda.time.DateTime;
import org.xcolab.portlets.proposals.utils.MoveType;
import org.xcolab.wrappers.BaseContestPhaseWrapper;

public class MoveHistoryWrapper {

    private final ProposalMoveHistory wrapped;

    public MoveHistoryWrapper(ProposalMoveHistory wrapped) {

        this.wrapped = wrapped;
    }

    public ProposalMoveHistory getWrapped() {
        return wrapped;
    }

    public ProposalWrapper getSourceProposal() throws PortalException, SystemException {
        return new ProposalWrapper(wrapped.getSourceProposalId());
    }

    public ContestWrapper getSourceContest() throws PortalException, SystemException {
        return new ContestWrapper(wrapped.getSourceContestId());
    }

    public BaseContestPhaseWrapper getSourceContestPhase() throws PortalException, SystemException {
        return new BaseContestPhaseWrapper(wrapped.getSourcePhaseId());
    }

    public ProposalWrapper getTargetProposal() throws PortalException, SystemException {
        return new ProposalWrapper(wrapped.getTargetProposalId());
    }

    public ContestWrapper getTargetContest() throws PortalException, SystemException {
        return new ContestWrapper(wrapped.getTargetContestId());
    }

    public BaseContestPhaseWrapper getTargetContestPhase() throws PortalException, SystemException {
        return new BaseContestPhaseWrapper(wrapped.getTargetPhaseId());
    }

    public User getMovingUser() throws SystemException, PortalException {
        return UserLocalServiceUtil.getUser(wrapped.getMovingUserId());
    }

    public MoveType getMoveType() {
        return MoveType.valueOf(wrapped.getMoveType());
    }

    public DateTime getMoveDate() {
        return new DateTime(wrapped.getMoveDate());
    }
}
