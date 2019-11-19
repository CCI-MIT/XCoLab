package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalMoveHistory;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.proposal.MoveType;

import java.sql.Timestamp;

@JsonDeserialize(as = ProposalMoveHistory.class)
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
            return new ProposalWrapper(StaticProposalContext.getProposalClient()
                    .getProposal(this.getSourceProposalId()));
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    default ContestWrapper getSourceContest() {
        try {
            return StaticContestContext.getContestClient().getContest(this.getSourceContestId());
        } catch (ContestNotFoundException ignored) {
            return null;
        }
    }

    default ContestPhaseWrapper getSourceContestPhase() {
        return StaticContestContext.getContestClient().getContestPhase(this.getSourcePhaseId());
    }

    default ProposalWrapper getTargetProposal() {
        try {
            return StaticProposalContext.getProposalClient()
                    .getProposal(this.getTargetProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    default ContestWrapper getTargetContest() {
        try {
            return StaticContestContext.getContestClient().getContest(this.getTargetContestId());
        } catch (ContestNotFoundException ignored) {
            return null;
        }
    }

    default ContestPhaseWrapper getTargetContestPhase() {
        return StaticContestContext.getContestClient().getContestPhase(this.getTargetPhaseId());
    }

    default UserWrapper getMovingUser() {
        return StaticUserContext.getUserClient().getMemberUnchecked(this.getMovingUserId());
    }

    //deal with this
    default MoveType getMoveTypeEnum() {
        return MoveType.valueOf(this.getMoveType());
    }

    //    default DateTime getMovedAt() {
    //        return new DateTime(this.getMovedAt());
    //    }
}
