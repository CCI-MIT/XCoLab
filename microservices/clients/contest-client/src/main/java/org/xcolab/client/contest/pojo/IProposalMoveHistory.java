package org.xcolab.client.contest.pojo;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.proposal.MoveType;

import java.sql.Timestamp;

public interface IProposalMoveHistory {

    Long getId();

    void setId(Long id);

    Long getSourceProposalId();

    void setSourceProposalId(Long sourceProposalId);

    Long getSourceContestId();

    void setSourceContestId(Long sourceContestId);

    Long getSourcePhaseId();

    void setSourcePhaseId(Long sourcePhaseId);

    Long getTargetProposalId();

    void setTargetProposalId(Long targetProposalId);

    Long getTargetContestId();

    void setTargetContestId(Long targetContestId);

    Long getTargetPhaseId();

    void setTargetPhaseId(Long targetPhaseId);

    Long getMovingUserId();

    void setMovingUserId(Long movingUserId);

    Timestamp getMovedAt();

    void setMovedAt(Timestamp movedAt);

    String getMoveType();

    void setMoveType(String moveType);

    default ProposalWrapper getSourceProposal() {
        try {
            return ProposalClientUtil.getProposal(this.getSourceProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    default ContestWrapper getSourceContest() {
        try {
            return ContestClientUtil.getContest(this.getSourceContestId());
        } catch (ContestNotFoundException ignored) {
            return null;
        }
    }

    default ContestPhaseWrapper getSourceContestPhase() {
        return (ContestClientUtil.getContestPhase(this.getSourcePhaseId()));
    }

    default ProposalWrapper getTargetProposal() {
        try {
            return ProposalClientUtil.getProposal(this.getTargetProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    default ContestWrapper getTargetContest() {
        try {
            return (ContestClientUtil.getContest(this.getTargetContestId()));
        } catch (ContestNotFoundException ignored) {
            return null;
        }
    }

    default ContestPhaseWrapper getTargetContestPhase() {
        return (ContestClientUtil.getContestPhase(this.getTargetPhaseId()));
    }

    default Member getMovingUser() {
        return MembersClient.getMemberUnchecked(this.getMovingUserId());
    }

    //deal with this
    default MoveType getMoveTypeEnum() {
        return MoveType.valueOf(this.getMoveType());
    }

    //    default DateTime getMovedAt() {
    //        return new DateTime(this.getMovedAt());
    //    }
}
