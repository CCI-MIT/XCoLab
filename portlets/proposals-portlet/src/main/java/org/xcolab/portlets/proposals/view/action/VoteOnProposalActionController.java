package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.NoSuchProposalVoteException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.emailnotification.ProposalVoteNotification;
import org.xcolab.utils.emailnotification.ProposalVoteValidityConfirmation;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("view")
public class VoteOnProposalActionController {

    private final static Log _log = LogFactoryUtil.getLog(VoteOnProposalActionController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    private final static String VOTE_ANALYTICS_KEY = "VOTE_CONTEST_ENTRIES";
    private final static String VOTE_ANALYTICS_CATEGORY = "User";
    private final static String VOTE_ANALYTICS_ACTION = "Vote contest entry";
    private final static String VOTE_ANALYTICS_LABEL = "";


    @RequestMapping(params = {"action=voteOnProposalAction"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        boolean hasVoted = false;
        final Proposal proposal = proposalsContext.getProposal(request);
        final Contest contest = proposalsContext.getContest(request);
        final User user = proposalsContext.getUser(request);
        if (proposalsContext.getPermissions(request).getCanVote()) {
            long proposalId = proposal.getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
            long userId = user.getUserId();
            if (ProposalLocalServiceUtil.hasUserVoted(proposalId, contestPhaseId, userId)) {
                // User has voted for this proposal and would like to retract the vote
                ProposalLocalServiceUtil.removeVote(contestPhaseId, userId);
            } else {
                if (ProposalVoteLocalServiceUtil.hasUserVoted(contestPhaseId, userId)) {
                    // User has voted for a different proposal. Vote will be retracted and converted to a vote of this proposal.
                    ProposalLocalServiceUtil.removeVote(contestPhaseId, userId);
                }
                ServiceContext serviceContext = new ServiceContext();
                ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
                serviceContext.setPortalURL(themeDisplay.getPortalURL());

                ProposalLocalServiceUtil.addVote(proposalId, contestPhaseId, userId);

                final boolean voteIsValid = validateVote(user, proposal, contest, serviceContext);
                if (voteIsValid) {
                    new ProposalVoteNotification(proposal, contest, user, serviceContext).sendMessage();
                    hasVoted = true;
                }

                //publish event per contestPhaseId to allow voting on exactly one proposal per contest(phase)
            	AnalyticsUtil.publishEvent(request, userId, VOTE_ANALYTICS_KEY+contestPhaseId,
            			VOTE_ANALYTICS_CATEGORY,
            			VOTE_ANALYTICS_ACTION,
            			VOTE_ANALYTICS_LABEL,
            			1);
            }
        } else {
            if (user == null || user.getUserId() == 10115) {
                /* User is not logged in - don't count vote and let user log in*/
                request.setAttribute("promptLoginWindow","true");
                return;
            } else {
                throw new ProposalsAuthorizationException("User isn't allowed to vote on proposal ");
            }
        }
        // Redirect to prevent page-refreshing from influencing the vote
        final String arguments = hasVoted ? "?voted=true" : "";
        response.sendRedirect(ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal) + arguments);
    }

    private boolean validateVote(User user, Proposal proposal, Contest contest, ServiceContext serviceContext) throws SystemException, PortalException {
        List<User> usersWithSharedIP = Xcolab_UserLocalServiceUtil.findUsersByLoginIP(user.getLastLoginIP());
        usersWithSharedIP.remove(user);
        if (!usersWithSharedIP.isEmpty()) {
            final ProposalVote vote = ProposalVoteLocalServiceUtil.findByProposalIdUserId(proposal.getProposalId(), user.getUserId());
            int recentVotesFromSharedIP = 0;
            for (User otherUser : usersWithSharedIP) {
                try {
                    final ProposalVote otherVote = ProposalVoteLocalServiceUtil.findByProposalIdUserId(proposal.getProposalId(), otherUser.getUserId());
                    //check if vote is less than 12 hours old
                    if (new DateTime(otherVote.getCreateDate()).plusHours(12).isAfterNow()) {
                        recentVotesFromSharedIP++;
                    }
                } catch (NoSuchProposalVoteException ignored) { }

                if (StringUtils.getLevenshteinDistance(user.getFirstName(), otherUser.getFirstName()) < 3
                        && StringUtils.getLevenshteinDistance(user.getLastName(), otherUser.getLastName()) < 3) {
                    vote.setIsValid(false);
                    break;
                }
            }
            if (vote.isIsValid() && recentVotesFromSharedIP > 7) {
                vote.setIsValid(false);
                sendConfirmationMail(vote, proposal, contest, user, serviceContext);
            }
            vote.persist();
            return vote.isIsValid();
        }
        return true;
    }

    private void sendConfirmationMail(ProposalVote vote, Proposal proposal, Contest contest, User user, ServiceContext serviceContext) throws PortalException, SystemException {
        String confirmationToken = Long.toHexString(SecureRandomUtil.nextLong());
        vote.setConfirmationToken(confirmationToken);
        vote.setConfirmationEmailSendDate(new Date());
        new ProposalVoteValidityConfirmation(proposal, contest, user, serviceContext,
                confirmationToken).sendEmailNotification();
    }

    @RequestMapping(params = "pageToDisplay=confirmVote")
    public String confirmVote(PortletRequest request,
                              PortletResponse response,
                              Model model,
                            @RequestParam long proposalId,
                            @RequestParam long userId,
                            @RequestParam String confirmationToken) {
        boolean success = false;
        try {
            ProposalVote vote = ProposalVoteLocalServiceUtil.findByProposalIdUserId(proposalId, userId);
            if (!vote.getConfirmationToken().isEmpty()
                    && vote.getConfirmationToken().equalsIgnoreCase(confirmationToken)) {
                vote.setIsValid(true);
                vote.persist();
                ProposalWrapper proposal = new ProposalWrapper(ProposalLocalServiceUtil.fetchProposal(proposalId));
                model.addAttribute("proposal", proposal);
                success = true;
            } else {
                model.addAttribute("error", "TokenError");
            }
        } catch (NoSuchProposalVoteException e) {
            model.addAttribute("error", "NoSuchProposalVote");
        } catch (SystemException e) {
            _log.error(String.format("Exception occurred while confirmingvote for proposal %d, user %d, with token %s",
                    proposalId, userId, confirmationToken), e);
        }
        model.addAttribute("success", success);
        return "confirmVote";
    }
}
