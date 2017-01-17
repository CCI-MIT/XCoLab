package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.proposals.ProposalRatings;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ProposalFellowWrapper extends Proposal {

    public ProposalFellowWrapper(Proposal proposal, Member currentMember, HttpServletRequest request) {
        super(proposal,proposal.getContestPhase());
        try {
            //find out contestPhase
            Contest baseContest = ProposalsContextUtil.getClients(request).getProposalClient().getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase contestPhase = ContestClientUtil.getActivePhase(baseContest.getContestPK());

            List<ProposalRating> list = ProposalJudgeRatingClientUtil.getFellowRatingForProposalAndUser(
                    currentMember.getUserId(),
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK());
            Member m = MembersClient.getMemberUnchecked(currentMember.getUserId());
            this.proposalRatings = new ProposalRatings(m, list);
        } catch (ContestNotFoundException  e) {
            this.proposalRatings = null;
        }
    }
}
