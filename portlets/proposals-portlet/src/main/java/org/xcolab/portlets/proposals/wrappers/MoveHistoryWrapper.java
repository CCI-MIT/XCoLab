package org.xcolab.portlets.proposals.wrappers;

import org.joda.time.DateTime;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.phases.ProposalMoveHistory;
import org.xcolab.portlets.proposals.utils.MoveType;

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
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    public Contest getSourceContest() {
            return new Contest(wrapped.getSourceContestId());
    }

    public ContestPhase getSourceContestPhase() {
        return new ContestPhase(ContestClientUtil.getContestPhase(wrapped.getSourcePhaseId()));
    }

    public long getSourceContestPhaseId() {
        return wrapped.getSourcePhaseId();
    }

    public ProposalWrapper getTargetProposal() throws PortalException, SystemException {
        try {
            return new ProposalWrapper(wrapped.getTargetProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    public Contest getTargetContest() {
            return new Contest(wrapped.getTargetContestId());
    }

    public ContestPhase getTargetContestPhase() {
        return new ContestPhase(ContestClientUtil.getContestPhase(wrapped.getTargetPhaseId()));
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
