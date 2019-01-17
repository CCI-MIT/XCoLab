package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.ProposalRating;
import org.xcolab.client.contest.pojo.UserProposalRatings;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.List;

public class ProposalFellowWrapper extends Proposal {

    public ProposalFellowWrapper(ProposalContext proposalContext,
            Proposal proposal, Member currentMember) {
        super(proposal,proposal.getContestPhase());
        try {
            //find out contestPhase
            ContestWrapper baseContest = proposalContext.getClients().getProposalClient().getCurrentContestForProposal(proposal.getId());
            ContestPhaseWrapper contestPhase = ContestClientUtil.getActivePhase(baseContest.getId());

            List<ProposalRating> list = ProposalJudgeRatingClientUtil.getFellowRatingForProposalAndUser(
                    currentMember.getId(),
                    proposal.getId(),
                    contestPhase.getId());
            Member m = MembersClient.getMemberUnchecked(currentMember.getId());
            this.proposalRatings = new UserProposalRatings(m, list);
        } catch (ContestNotFoundException  e) {
            this.proposalRatings = null;
        }
    }
}
