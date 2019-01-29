package org.xcolab.client.proposals.pojo.phases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.proposal.MoveType;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalMoveHistory extends AbstractProposalMoveHistory implements Serializable {

    public static final TypeProvider<ProposalMoveHistory> TYPES =
            new TypeProvider<>(ProposalMoveHistory.class,
                    new ParameterizedTypeReference<List<ProposalMoveHistory>>() {});

    public ProposalMoveHistory() {}

    public ProposalMoveHistory(ProposalMoveHistory value) {
        super(value);
    }

    public ProposalMoveHistory(
            Long id,
            Long sourceproposalid,
            Long sourcecontestid,
            Long sourcephaseid,
            Long targetproposalid,
            Long targetcontestid,
            Long targetphaseid,
            Long movinguserid,
            Timestamp movedate,
            String movetype
    ) {
        super(id, sourceproposalid, sourcecontestid, sourcephaseid, targetproposalid,
                targetcontestid, targetphaseid, movinguserid, movedate, movetype);
    }

    public ProposalMoveHistory(AbstractProposalMoveHistory abstractProposalMoveHistory) {
        super(abstractProposalMoveHistory);
    }
    public Proposal getSourceProposal() {
        try {
            return ProposalClientUtil.getProposal(this.getSourceProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    public Contest getSourceContest() {
        try {
            return ContestClientUtil.getContest(this.getSourceContestId());
        }catch (ContestNotFoundException ignored){
            return null;
        }
    }

    public ContestPhase getSourceContestPhase() {
        return (ContestClientUtil.getContestPhase(this.getSourcePhaseId()));
    }

    public Proposal getTargetProposal()  {
        try {
            return  ProposalClientUtil.getProposal(this.getTargetProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    public Contest getTargetContest() {
        try{
            return (ContestClientUtil.getContest(this.getTargetContestId()));
        }catch (ContestNotFoundException ignored){
            return null;
        }
    }


    public ContestPhase getTargetContestPhase() {
        return (ContestClientUtil.getContestPhase(this.getTargetPhaseId()));
    }

    public UserWrapper getMovingUser() {
        return StaticUserContext.getUserClient().getMemberUnchecked(this.getMovingUserId());
    }


    //deal with this
    public MoveType getMoveTypeEnum() {
        return MoveType.valueOf(this.getMoveType());
    }

//    public DateTime getMovedAt() {
//        return new DateTime(this.getMovedAt());
//    }
}
