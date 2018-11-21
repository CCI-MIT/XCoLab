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
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.List;

public class ProposalFellowWrapper extends Proposal {

    public ProposalFellowWrapper(ProposalContext proposalContext,
            Proposal proposal, Member currentMember) {
        super(proposal,proposal.getContestPhase());
        try {
            //find out contestPhase
            Contest baseContest = proposalContext.getClients().getProposalClient().getCurrentContestForProposal(proposal.getId());
            ContestPhase contestPhase = ContestClientUtil.getActivePhase(baseContest.getId());

            List<ProposalRating> list = ProposalJudgeRatingClientUtil.getFellowRatingForProposalAndUser(
                    currentMember.getId(),
                    proposal.getId(),
                    contestPhase.getId());
            Member m = MembersClient.getMemberUnchecked(currentMember.getId());
            this.proposalRatings = new ProposalRatings(list);
        } catch (ContestNotFoundException  e) {
            this.proposalRatings = null;
        }
    }
}
