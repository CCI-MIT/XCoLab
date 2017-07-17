package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.judging.JudgingUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalFellowWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalScreeningTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=SCREENING")
    public String showFellowsPanel(HttpServletRequest request, Model model,
            ProposalContext proposalContext, Member currentMember) {
        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.SCREENING);

        Proposal proposal = proposalContext.getProposal();
        ContestPhase contestPhase = proposalContext.getContestPhase();
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
        ProposalFellowWrapper proposalFellowWrapper = new ProposalFellowWrapper(proposalContext,
                proposalWrapper, currentMember);

        boolean hasAlreadyBeenPromoted =
                ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                        proposal.getProposalId(),
                        contestPhase.getContestPhasePK(),
                        ProposalContestPhaseAttributeKeys.PROMOTE_DONE
                );

        FellowProposalScreeningBean bean = new FellowProposalScreeningBean(proposalFellowWrapper);
        bean.setContestPhaseId(contestPhase.getContestPhasePK());

        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);
        model.addAttribute("fellowProposalScreeningBean", bean);
        model.addAttribute("emailTemplates", bean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("judgingOptions", JudgingSystemActions.FellowAction.values());
        model.addAttribute("discussionId", proposal.getFellowDiscussionId());

        return "proposals/proposalScreening";
    }

    @PostMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=SCREENING")
    public void saveScreening(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member currentMember,
            @ModelAttribute FellowProposalScreeningBean fellowProposalScreeningBean)
            throws ProposalsAuthorizationException, IOException {
        try {

            long proposalId = proposalContext.getProposal().getProposalId();
            long contestPhaseId = fellowProposalScreeningBean.getContestPhaseId();
            ProposalsPermissions permissions = proposalContext.getPermissions();

            // Security handling
            if (!(permissions.getCanFellowActions()
                    && proposalContext.getProposal()
                    .isUserAmongFellows(currentMember.getUserId()))
                    && !permissions.getCanAdminAll()) {
                return;
            }

            // save selection of judges
            if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions
                    .FellowAction.PASS_TO_JUDGES.getAttributeValue()) {
                proposalContext.getClients().getProposalPhaseClient()
                        .persistSelectedJudgesAttribute(
                                proposalId,
                                contestPhaseId,
                                fellowProposalScreeningBean.getSelectedJudges()
                        );
            } else {
                //clear selected judges attribute since the decision is not to pass the proposal.
                proposalContext.getClients().getProposalPhaseClient()
                        .persistSelectedJudgesAttribute(
                                proposalId,
                                contestPhaseId,
                                null
                        );
            }

            // save fellow action
            if (fellowProposalScreeningBean.getFellowScreeningAction() != 0) {
                proposalContext.getClients().getProposalPhaseClient()
                        .setProposalContestPhaseAttribute(
                                proposalId,
                                contestPhaseId,
                                ProposalContestPhaseAttributeKeys.FELLOW_ACTION,
                                0L,
                                (long) fellowProposalScreeningBean.getFellowScreeningAction(),
                                null);

                //save fellow action comment
                ProposalJudgingCommentHelper commentHelper =
                        new ProposalJudgingCommentHelper(proposalContext.getProposal(),
                                ContestClientUtil

                                        .getContestPhase(contestPhaseId));

                if (fellowProposalScreeningBean.getFellowScreeningAction()
                        == JudgingSystemActions.FellowAction.INCOMPLETE.getAttributeValue()
                        || fellowProposalScreeningBean.getFellowScreeningAction()
                        == JudgingSystemActions.FellowAction.OFFTOPIC.getAttributeValue()
                        || fellowProposalScreeningBean.getFellowScreeningAction()
                        == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER
                        .getAttributeValue()) {
                    commentHelper.setScreeningComment(
                            fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
                }
            }

            // save fellow comment and rating
            //find existing ratings
            List<ProposalRating> existingRatings =
                    ProposalJudgeRatingClientUtil.getFellowRatingForProposalAndUser(
                            currentMember.getUserId(),
                            proposalId,
                            contestPhaseId);

            JudgingUtil.saveRatings(existingRatings, fellowProposalScreeningBean, proposalId,
                    contestPhaseId, currentMember.getUserId(), false);
            response.sendRedirect(proposalContext.getProposal()
                    .getProposalLinkUrl(proposalContext.getContest(),
                            proposalContext.getContestPhase().getContestPhasePK())
                    + "/tab/SCREENING");
        } catch (Exception e) {
            //TODO: do we still want this?
            List<Long> recipientIds = new ArrayList<>();
            recipientIds.add(1451771L); //Manuel
            recipientIds.add(1011659L); //Patrick
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            MessagingClient.sendMessage("Exception thrown when fellow rated proposal",
                    e.getMessage() + "\n\n" + exceptionAsString, 1010458L, 1010458L, recipientIds);
            throw e;
        }
    }
}
