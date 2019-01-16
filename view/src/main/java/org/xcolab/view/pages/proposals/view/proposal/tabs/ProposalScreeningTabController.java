package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.judging.JudgingUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalFellowWrapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalScreeningTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=SCREENING")
    public String showFellowsPanel(HttpServletRequest request, Model model,
            ProposalContext proposalContext, Member currentMember) {

        //TODO: permission check missing?

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.SCREENING);

        Proposal proposal = proposalContext.getProposal();
        ContestPhase contestPhase = proposalContext.getContestPhase();
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
        ProposalFellowWrapper proposalFellowWrapper = new ProposalFellowWrapper(proposalContext,
                proposalWrapper, currentMember);

        boolean hasAlreadyBeenPromoted =
                ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                        proposal.getId(),
                        contestPhase.getId(),
                        ProposalContestPhaseAttributeKeys.PROMOTE_DONE
                );

        FellowProposalScreeningBean bean = new FellowProposalScreeningBean(proposalFellowWrapper);
        bean.setContestPhaseId(contestPhase.getId());

        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);
        model.addAttribute("fellowProposalScreeningBean", bean);
        model.addAttribute("emailTemplates", bean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("judgingOptions", JudgingSystemActions.FellowAction.values());

        return "proposals/proposalScreening";
    }

    @PostMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=SCREENING")
    public String saveScreening(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, Member currentMember,
            @ModelAttribute FellowProposalScreeningBean fellowProposalScreeningBean) {

        final Contest contest = proposalContext.getContest();
        final Proposal proposal = proposalContext.getProposal();
        long proposalId = proposal.getId();
        long contestPhaseId = fellowProposalScreeningBean.getContestPhaseId();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        // Security handling
        final boolean isContestFellow = permissions.getCanFellowActions()
                && proposal.isUserAmongFellows(currentMember.getId());
        if (!isContestFellow && !permissions.getCanAdminAll()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
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
        proposalContext.getClients().getProposalPhaseClient()
                .setProposalContestPhaseAttribute(proposalId, contestPhaseId,
                        ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0L,
                        (long) fellowProposalScreeningBean.getFellowScreeningAction(),
                        null);
        if (fellowProposalScreeningBean.getFellowScreeningAction()
                != JudgingSystemActions.FellowAction.NO_DECISION.getAttributeValue()) {
            //save fellow action comment
            ProposalJudgingCommentHelper commentHelper =
                    new ProposalJudgingCommentHelper(proposal,
                            ContestClientUtil.getContestPhase(contestPhaseId));

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
                        currentMember.getId(),
                        proposalId,
                        contestPhaseId);

        JudgingUtil.saveRatings(existingRatings, fellowProposalScreeningBean, proposalId,
                contestPhaseId, currentMember.getId(), false);

        final String proposalLinkUrl = proposal.getProposalLinkUrl(contest, contestPhaseId);
        return "redirect:" + proposalLinkUrl + "/tab/SCREENING";
    }
}
