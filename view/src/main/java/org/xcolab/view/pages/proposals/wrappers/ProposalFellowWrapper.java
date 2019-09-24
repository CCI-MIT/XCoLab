package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.UserProposalRatings;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.List;

public class ProposalFellowWrapper extends ProposalWrapper {

    public ProposalFellowWrapper(ProposalContext proposalContext,
            ProposalWrapper proposal, UserWrapper currentMember) {
        super(proposal,proposal.getContestPhase());
        try {
            //find out contestPhase
            ContestWrapper baseContest = proposalContext.getClients().getProposalClient().getCurrentContestForProposal(proposal.getId());
            ContestPhaseWrapper contestPhase = StaticContestContext.getContestClient()
                    .getActivePhase(baseContest.getId());

            List<ProposalRatingWrapper> list = StaticProposalContext.getProposalJudgeRatingClient()
                    .getFellowRatingForProposalAndUser(
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
