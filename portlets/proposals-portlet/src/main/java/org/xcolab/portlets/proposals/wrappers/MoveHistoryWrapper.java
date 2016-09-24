package org.xcolab.portlets.proposals.wrappers;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.joda.time.DateTime;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.ProposalMoveHistory;
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
        try {
            return new ProposalWrapper(wrapped.getSourceProposalId());
        }catch (ProposalNotFoundException ignored){
            return null;
        }
    }

    public ContestWrapper getSourceContest() throws PortalException, SystemException {
        try {
            return new ContestWrapper(wrapped.getSourceContestId());
        }catch (ContestNotFoundException ignored){
            return null;
        }
    }

    public BaseContestPhaseWrapper getSourceContestPhase() throws PortalException, SystemException {
        return new BaseContestPhaseWrapper(wrapped.getSourcePhaseId());
    }

    public ProposalWrapper getTargetProposal() throws PortalException, SystemException {
        try {
            return new ProposalWrapper(wrapped.getTargetProposalId());
        }catch (ProposalNotFoundException ignored){
            return null;
        }
    }

    public ContestWrapper getTargetContest() throws PortalException, SystemException {
       try{
           return new ContestWrapper(wrapped.getTargetContestId());
       }catch (ContestNotFoundException ignored){
           return null;
       }
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
