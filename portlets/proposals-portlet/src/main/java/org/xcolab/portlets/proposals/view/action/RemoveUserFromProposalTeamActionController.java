package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

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
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        final Proposal proposal = proposalsContext.getProposal(request);
        final User actingUser = proposalsContext.getUser(request);
        final long proposalId = proposal.getProposalId();
        final long actingUserId = actingUser.getUserId();

        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!permissions.getCanManageUsers()) {
            final String errorMessage = String.format(
                    "User %d does not have the necessary permissions to remove a user from the team of proposal %d",
                    actingUserId, proposalId);
            _log.error(errorMessage);
            throw new ProposalsAuthorizationException(errorMessage);
        }
        if (memberUserId == proposal.getAuthorId()) {
            final String errorMessage = String.format("User %d is trying to remove the owner %d of proposal %d",
                    actingUserId, memberUserId, proposalId);
            _log.error(errorMessage);
            try {
                throw new ProposalsAuthorizationException(errorMessage);
            } catch (ProposalsAuthorizationException e) {
                //TODO: remove debug email
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.format("Acting user: %s, %d <br/>", actingUser.getScreenName(), actingUserId));
                User deletedUser = UserLocalServiceUtil.fetchUser(memberUserId);
                stringBuilder.append(String.format("Deleted member: %s, %d <br/>", deletedUser.getScreenName(), deletedUser.getUserId()));
                stringBuilder.append(String.format("Deleted from group: %d <br/>", proposal.getGroupId()));
                stringBuilder.append(String.format("Proposal: %s, %d <br/>", new ProposalWrapper(proposal).getName(), proposalId));
                stringBuilder.append("<br/>Acting user permissions: <br/>");
                stringBuilder.append(String.format("CanManageUsers: %b <br/>", permissions.getCanManageUsers()));
                stringBuilder.append(String.format("CanAdminProposal: %b <br/>", permissions.getCanAdminProposal()));
                stringBuilder.append(String.format("CanAdminAll: %b <br/>", permissions.getCanAdminAll()));
                stringBuilder.append(String.format("CanEdit: %b <br/>", permissions.getCanEdit()));
                stringBuilder.append(String.format("CanDelete: %b <br/>", permissions.getCanDelete()));
                stringBuilder.append(String.format("CanFellowActions: %b <br/>", permissions.getCanFellowActions()));
                stringBuilder.append(String.format("CanJudgeActions: %b <br/>", permissions.getCanJudgeActions()));
                stringBuilder.append("<br/>Stack trace: <br/>");
                stringBuilder.append(ExceptionUtils.getStackTrace(e));
                new EmailToAdminDispatcher(errorMessage, stringBuilder.toString()).sendMessage();
                throw e;
            }
        }

        ProposalLocalServiceUtil.removeUserFromTeam(proposalId,memberUserId);
        response.sendRedirect(proposal.getProposalLinkUrl(proposalsContext.getContest(request)) + "/tab/TEAM");
    }
}
