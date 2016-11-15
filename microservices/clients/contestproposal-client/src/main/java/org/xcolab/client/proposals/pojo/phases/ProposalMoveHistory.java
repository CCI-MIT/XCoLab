package org.xcolab.client.proposals.pojo.phases;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.proposal.MoveType;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;

public class ProposalMoveHistory extends AbstractProposalMoveHistory {

    public ProposalMoveHistory() {}

    public ProposalMoveHistory(ProposalMoveHistory value) {
        super(value);
    }

    public ProposalMoveHistory(
            Long id_,
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
        super(id_, sourceproposalid, sourcecontestid, sourcephaseid, targetproposalid,
                targetcontestid, targetphaseid, movinguserid, movedate, movetype);
    }

    public ProposalMoveHistory(AbstractProposalMoveHistory abstractProposalMoveHistory,
            RestService restService) {
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

    public long getSourceContestPhaseId() {
        return this.getSourcePhaseId();
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

    public Member getMovingUser() {
        return MembersClient.getMemberUnchecked(this.getMovingUserId());
    }


    //deal with this
    public MoveType getMoveTypez() {
        return MoveType.valueOf(this.getMoveType());
    }

//    public DateTime getMoveDate() {
//        return new DateTime(this.getMoveDate());
//    }
}
