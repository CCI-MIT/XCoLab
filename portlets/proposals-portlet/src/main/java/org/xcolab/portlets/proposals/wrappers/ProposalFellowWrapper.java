package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.NoSuchContestException;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;

import java.util.List;

public class ProposalFellowWrapper extends ProposalWrapper {

    public ProposalFellowWrapper(ProposalWrapper proposal, Member currentMember) throws NoSuchContestException {
        super(proposal);
        try {
            //find out contestPhase
            Contest baseContest = ProposalClientUtil.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase contestPhase = ContestClientUtil.getActivePhase(baseContest.getContestPK());

            List<ProposalRating> list = ProposalJudgeRatingClientUtil.getFellowRatingForProposalAndUser(
                    currentMember.getUserId(),
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK());
            Member m = MembersClient.getMemberUnchecked(currentMember.getUserId());
            this.proposalRatings = new ProposalRatingsWrapper(m, list);
        } catch (ContestNotFoundException  e) {
            this.proposalRatings = null;
        }
    }
}
