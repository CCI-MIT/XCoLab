package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.portlets.proposals.utils.ProposalAttributeUtil;

/**
 * Created by patrickhiesel on 19/12/13.
 */
public class ProposalJudgeWrapper extends ProposalWrapper{

    private User currentUser;

    public ProposalJudgeWrapper(Proposal proposal, User currentUser) {
        super(proposal);
        this.currentUser = currentUser;
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, User currentUser) {
        super(proposal, version);
        this.currentUser = currentUser;
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase, User currentUser) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentUser = currentUser;
    }

    public boolean getIsJudgeAssignedAndIncomplete() throws SystemException, PortalException {
        if (currentUser == null) return false;
        Proposal p = ProposalLocalServiceUtil.getProposal(this.getProposalId());
        for (long l : this.getSelectedJudges()){
            if (currentUser.getUserId() == l) return !MessageRecipientStatusLocalServiceUtil.didReceiveJudgeCommentForProposal(p, currentUser);
        }
        return false;
    }
}
