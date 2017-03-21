package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.judging.JudgingUtil;
import org.xcolab.view.pages.proposals.judging.ProposalJudgingCommentHelper;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
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

    private final ProposalsContext proposalsContext;

    @Autowired
    public ProposalScreeningTabController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }


    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=SCREENING")
    public String showFellowsPanel(HttpServletRequest request, Model model) {
        setCommonModelAndPageAttributes(request, model, ProposalTab.SCREENING);

        Proposal proposal = proposalsContext.getProposal(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
        ProposalFellowWrapper proposalFellowWrapper = new ProposalFellowWrapper(
                proposalWrapper, proposalsContext.getMember(request), request);

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
            @ModelAttribute FellowProposalScreeningBean fellowProposalScreeningBean)
            throws ProposalsAuthorizationException, IOException {
        try {

            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = fellowProposalScreeningBean.getContestPhaseId();
            ProposalsPermissions permissions = proposalsContext.getPermissions(request);
            Member currentMember = proposalsContext.getMember(request);

            // Security handling
            if (!(permissions.getCanFellowActions()
                    && proposalsContext.getProposalWrapped(request)
                    .isUserAmongFellows(currentMember.getUserId()))
                    && !permissions.getCanAdminAll()) {
                return;
            }

            // save selection of judges
            if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions
                    .FellowAction.PASS_TO_JUDGES.getAttributeValue()) {
                proposalsContext.getClients(request).getProposalPhaseClient()
                        .persistSelectedJudgesAttribute(
                                proposalId,
                                contestPhaseId,
                                fellowProposalScreeningBean.getSelectedJudges()
                        );
            } else {
                //clear selected judges attribute since the decision is not to pass the proposal.
                proposalsContext.getClients(request).getProposalPhaseClient()
                        .persistSelectedJudgesAttribute(
                                proposalId,
                                contestPhaseId,
                                null
                        );
            }

            // save fellow action
            if (fellowProposalScreeningBean.getFellowScreeningAction() != 0) {
                proposalsContext.getClients(request).getProposalPhaseClient()
                        .setProposalContestPhaseAttribute(
                                proposalId,
                                contestPhaseId,
                                ProposalContestPhaseAttributeKeys.FELLOW_ACTION,
                                0L,
                                (long) fellowProposalScreeningBean.getFellowScreeningAction(),
                                null);

                //save fellow action comment
                ProposalJudgingCommentHelper commentHelper =
                        new ProposalJudgingCommentHelper(proposalsContext.getProposal(request),
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
            response.sendRedirect(proposalsContext.getProposal(request)
                    .getProposalLinkUrl(proposalsContext.getContest(request),
                            proposalsContext.getContestPhase(request).getContestPhasePK())
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
