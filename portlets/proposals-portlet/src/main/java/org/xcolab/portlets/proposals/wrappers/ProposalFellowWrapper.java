package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.NoSuchContestException;
import com.liferay.portal.model.User;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalRatingClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.ProposalRating;

import java.util.List;

;

public class ProposalFellowWrapper extends ProposalWrapper {

    public ProposalFellowWrapper(ProposalWrapper proposal, User currentUser) throws NoSuchContestException {
        super(proposal);
        try {
            //find out contestPhase
            Contest baseContest = ProposalsClient.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase contestPhase = ContestClientUtil.getActivePhase(baseContest.getContestPK());

            List<ProposalRating> list = ProposalRatingClient.getFellowRatingForProposalAndUser(
                    currentUser.getUserId(),
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK());
            Member m = MembersClient.getMemberUnchecked(currentUser.getUserId());
            this.proposalRatings = new ProposalRatingsWrapper(m, list);
        } catch (ContestNotFoundException  e) {
            this.proposalRatings = null;
        }
    }
}
