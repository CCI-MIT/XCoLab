package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/10/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("view")
public class RemoveUserFromProposalTeamActionController {

    private final static Log _log = LogFactoryUtil.getLog(RemoveUserFromProposalTeamActionController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=removeUserFromTeam"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam("member") long memberUserId)
            throws PortalException, SystemException, ProposalsAuthorizationException {

        final Proposal proposal = proposalsContext.getProposal(request);
        final User user = proposalsContext.getUser(request);
        final long proposalId = proposal.getProposalId();
        final long userId = user.getUserId();

        if (!proposalsContext.getPermissions(request).getCanManageUsers()) {
            final String errorMessage = String.format(
                    "User %d does not have the necessary permissions to remove a user from the team of proposal %d",
                    userId, proposalId);
            _log.error(errorMessage);
            throw new ProposalsAuthorizationException(errorMessage);
        }
        if (memberUserId == proposal.getAuthorId()) {
            final String errorMessage = String.format("User %d is trying to remove the owner of proposal %d",
                    userId, proposalId);
            _log.error(errorMessage);
            throw new ProposalsAuthorizationException(errorMessage);
        }

        ProposalLocalServiceUtil.removeUserFromTeam(proposalId,memberUserId);
    }
}
