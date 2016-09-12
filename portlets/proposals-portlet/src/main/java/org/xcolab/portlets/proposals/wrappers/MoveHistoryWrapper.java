package org.xcolab.portlets.proposals.wrappers;

import org.joda.time.DateTime;

import com.ext.portlet.model.ProposalMoveHistory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
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

    public ContestWrapper getSourceContest() {
        try {
            return new ContestWrapper(wrapped.getSourceContestId());
        }catch (ContestNotFoundException ignored){
            return null;
        }
    }

    public BaseContestPhaseWrapper getSourceContestPhase() {
        return new BaseContestPhaseWrapper(wrapped.getSourcePhaseId());
    }

    public long getSourceContestPhaseId() {
        return wrapped.getSourcePhaseId();
    }

    public ProposalWrapper getTargetProposal() throws PortalException, SystemException {
        return new ProposalWrapper(wrapped.getTargetProposalId());
    }

    public ContestWrapper getTargetContest() {
       try {
           return new ContestWrapper(wrapped.getTargetContestId());
       } catch (ContestNotFoundException ignored) {
           return null;
       }
    }

    public BaseContestPhaseWrapper getTargetContestPhase() {
        return new BaseContestPhaseWrapper(wrapped.getTargetPhaseId());
    }

    public Member getMovingUser() {
        return MembersClient.getMemberUnchecked(wrapped.getMovingUserId());
    }

    public MoveType getMoveType() {
        return MoveType.valueOf(wrapped.getMoveType());
    }

    public DateTime getMoveDate() {
        return new DateTime(wrapped.getMoveDate());
    }
}
