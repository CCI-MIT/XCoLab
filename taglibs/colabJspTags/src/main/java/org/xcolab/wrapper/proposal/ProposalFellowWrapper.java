package org.xcolab.wrapper.proposal;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * Created by Manuel Thurner
 */
public class ProposalFellowWrapper extends ProposalWrapper {
    private User currentUser;


    public ProposalFellowWrapper(ProposalWrapper proposal, User currentUser) {
        super(proposal);
        this.currentUser = currentUser;

        try {
            //find out contestPhase
            Contest baseContest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(baseContest);

            List<ProposalRating> list = ProposalRatingLocalServiceUtil.getFellowRatingForProposalAndUser(
                    currentUser.getUserId(),
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK());
            this.proposalRatings = new ProposalRatingsWrapper(currentUser, list);
        } catch (Exception e) {
            this.proposalRatings = null;
        }
    }
}
