package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.proposals.UserProposalRatings;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.List;

public class ProposalFellowWrapper extends Proposal {

    public ProposalFellowWrapper(ProposalContext proposalContext,
            Proposal proposal, UserWrapper currentMember) {
        super(proposal,proposal.getContestPhase());
        try {
            //find out contestPhase
            Contest baseContest = proposalContext.getClients().getProposalClient().getCurrentContestForProposal(proposal.getId());
            ContestPhase contestPhase = ContestClientUtil.getActivePhase(baseContest.getId());

            List<ProposalRating> list = ProposalJudgeRatingClientUtil.getFellowRatingForProposalAndUser(
                    currentMember.getId(),
                    proposal.getId(),
                    contestPhase.getId());
            UserWrapper m = StaticUserContext.getUserClient().getMemberUnchecked(currentMember.getId());
            this.proposalRatings = new UserProposalRatings(m, list);
        } catch (ContestNotFoundException  e) {
            this.proposalRatings = null;
        }
    }
}
