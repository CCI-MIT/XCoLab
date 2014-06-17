package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * Created by Manuel Thurner
 */
public class ProposalFellowWrapper extends ProposalWrapper {
    private User currentUser;
    private ProposalRating proposalRating;


    public ProposalFellowWrapper(ProposalWrapper proposal, User currentUser) {
        super(proposal);
        this.currentUser = currentUser;

        try {
            //find out contestPhase
            Contest baseContest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(baseContest);

            this.proposalRating = ProposalRatingLocalServiceUtil.getFellowRatingForProposal(
                    currentUser.getUserId(),
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK());
        } catch (Exception e) {
            this.proposalRating = null;
        }
    }


    public Long getFellowRating() throws SystemException, PortalException {
        if (this.proposalRating != null) {
            return this.proposalRating.getRating();
        } else {
            return 0L;
        }

    }

    public String getFellowComment() throws SystemException, PortalException {
        if (this.proposalRating != null) {
            return this.proposalRating.getComment();
        } else {
            return "";
        }

    }
}
