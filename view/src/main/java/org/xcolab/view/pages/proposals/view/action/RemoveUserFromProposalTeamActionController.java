package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.email.EmailToAdminDispatcher;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//-- @RequestMapping("view")
public class RemoveUserFromProposalTeamActionController {

    private final static Logger _log = LoggerFactory.getLogger(RemoveUserFromProposalTeamActionController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    //-- @RequestMapping(params = {"action=removeUserFromTeam"})
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response, @RequestParam("member") long memberId)
            throws ProposalsAuthorizationException, IOException {

        final Proposal proposal = proposalsContext.getProposal(request);
        final Member actingMember = proposalsContext.getMember(request);
        final long proposalId = proposal.getProposalId();
        final long actingUserId = actingMember.getUserId();

        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!permissions.getCanManageUsers()) {
            final String errorMessage = String.format(
                    "User %d does not have the necessary permissions to remove a user from the team of proposal %d",
                    actingUserId, proposalId);
            _log.error(errorMessage);
            throw new ProposalsAuthorizationException(errorMessage);
        }
        if (memberId == proposal.getAuthorId()) {
            final String errorMessage = String.format("User %d is trying to remove the owner %d of proposal %d",
                    actingUserId, memberId, proposalId);
            _log.error(errorMessage);
            try {
                throw new ProposalsAuthorizationException(errorMessage);
            } catch (ProposalsAuthorizationException e) {
                //TODO: remove debug email
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.format("Acting user: %s, %d <br/>", actingMember.getScreenName(), actingUserId));
                Member deletedMember = MembersClient.getMemberUnchecked(memberId);
                stringBuilder.append(String.format("Deleted member: %s, %d <br/>", deletedMember.getScreenName(), deletedMember.getUserId()));
                stringBuilder.append(String.format("Deleted from group: %d <br/>", proposal.getGroupId()));
                stringBuilder.append(String.format("Proposal: %s, %d <br/>", new Proposal(proposal).getName(), proposalId));
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

        ProposalsContextUtil.getClients(request).getProposalClient().removeUserFromProposalTeam(proposalId,memberId);
        response.sendRedirect(proposal.getProposalLinkUrl(proposalsContext.getContest(request)) + "/tab/TEAM");
    }
}
