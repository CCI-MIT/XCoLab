package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatings;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.UserProposalRatings;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.judging.JudgingUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.view.pages.proposals.requests.ProposalAdvancingBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalAdvancingTabController extends BaseProposalTabController {

    @Autowired
    private IUserClient userClient;

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADVANCING")
    public String showAdvancingTab(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, ProposalContext proposalContext) {

        final ProposalTab tab = ProposalTab.ADVANCING;
        setCommonModelAndPageAttributes(request, model, proposalContext, tab);

        if (!tab.getCanAccess(proposalContext)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        ProposalWrapper proposal = proposalContext.getProposal();
        ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();
        ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, contestPhase);
        ProposalAdvancingBean advancingBean = new ProposalAdvancingBean(proposalWrapper);

        final boolean isFinalistPhase = contestPhase.isFinalistPhase();

        if (!model.containsAttribute("proposalAdvancingBean")) {
            model.addAttribute("proposalAdvancingBean", advancingBean);
        }

        model.addAttribute("emailTemplates", advancingBean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());
        model.addAttribute("isFinalistPhase", isFinalistPhase);

        List<ProposalRatingWrapper> fellowRatingsUnWrapped =
                proposalJudgeRatingClient.getFellowRatingsForProposal(
                        proposal.getId(), contestPhase.getId());
        List<ProposalRatings> fellowRatings = wrapProposalRatings(fellowRatingsUnWrapped);

        List<ProposalRatingWrapper> judgesRatingsUnWrapped =
                proposalJudgeRatingClient.getJudgeRatingsForProposal(
                        proposal.getId(), contestPhase.getId());

        for (Iterator i = judgesRatingsUnWrapped.iterator(); i.hasNext(); ) {
            ProposalRatingWrapper judgesRatingUnWrapped = (ProposalRatingWrapper) i.next();
            if (judgesRatingUnWrapped.isOnlyForInternalUsage()) {
                i.remove();
            }
        }

        List<ProposalRatings> judgeRatings = wrapProposalRatings(judgesRatingsUnWrapped);
        boolean isFrozen = proposalPhaseClient.isProposalContestPhaseAttributeSetAndTrue(
                proposal.getId(), contestPhase.getId(),
                ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN
        );
        boolean hasAlreadyBeenPromoted =
                proposalPhaseClient.isProposalContestPhaseAttributeSetAndTrue(
                        proposal.getId(), contestPhase.getId(),
                        ProposalContestPhaseAttributeKeys.PROMOTE_DONE
                );

        model.addAttribute("isFrozen", isFrozen);
        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);

        model.addAttribute("fellowRatings", fellowRatings);
        model.addAttribute("judgeRatings", judgeRatings);

        return "proposals/proposalAdvancing";
    }

    private static List<ProposalRatings> wrapProposalRatings(List<ProposalRatingWrapper> ratings) {
        Map<Long, List<ProposalRatingWrapper>> ratingsByUserId = new HashMap<>();

        for (ProposalRatingWrapper r : ratings) {
            ratingsByUserId.computeIfAbsent(r.getUserId(), k -> new ArrayList<>());
            ratingsByUserId.get(r.getUserId()).add(r);
        }

        List<ProposalRatings> wrappers = new ArrayList<>();
        for (Map.Entry<Long, List<ProposalRatingWrapper>> entry : ratingsByUserId.entrySet()) {
            List<ProposalRatingWrapper> userRatings = entry.getValue();
            ProposalRatings wrapper = new UserProposalRatings(entry.getKey(), userRatings);
            wrappers.add(wrapper);
        }
        return wrappers;
    }

    @PostMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADVANCING")
    public String saveAdvanceDetails(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, ProposalContext proposalContext,
            @RequestParam(defaultValue = "false") boolean isForcePromotion,
            @RequestParam(defaultValue = "false") boolean isFreeze,
            @RequestParam(defaultValue = "false") boolean isUnfreeze,
            @Valid ProposalAdvancingBean proposalAdvancingBean, BindingResult result) {

        ProposalWrapper proposal = proposalContext.getProposal();
        final ContestWrapper contest = proposalContext.getContest();
        long proposalId = proposal.getId();
        ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        final ClientHelper clients = proposalContext.getClients();
        final IProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();

        final Long phaseId = contestPhase.getId();
        boolean isFrozen = proposalPhaseClient.isProposalContestPhaseAttributeSetAndTrue(proposalId,
                phaseId, ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN);

        if (!(ProposalTab.ADVANCING.getCanAccess(proposalContext))
                || (isFrozen && !permissions.getCanAdminAll())) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (result.hasErrors()) {
            return showAdvancingTab(request, response, model, member, proposalContext);
        }

        boolean isUndecided = proposalAdvancingBean.getAdvanceDecision()
                == JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue();

        // save judge decision
        proposalPhaseClient.setProposalContestPhaseAttribute(proposalId,
                phaseId, ProposalContestPhaseAttributeKeys.JUDGE_DECISION,
                0L, (long) proposalAdvancingBean.getAdvanceDecision(), null);

        // save judge comment
        ProposalJudgingCommentHelper
                commentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        if (!isUndecided) {
            commentHelper.setAdvancingComment(proposalAdvancingBean.getAdvanceComment());
        } else {
            commentHelper.setAdvancingComment("");
        }

        if (!isUndecided && isFreeze) {
            setIsFrozen(proposalId, contestPhase, true);
        }

        if (permissions.getCanAdminAll() && isUnfreeze) {
            setIsFrozen(proposalId, contestPhase, false);
        }

        final String proposalUrl = proposal.getProposalLinkUrl(contest, phaseId);
        if (permissions.getCanAdminAll() && !isUndecided && isForcePromotion) {
            contestClient.forcePromotionOfProposalInPhase(proposal.getId(), phaseId);
            return "redirect:" + proposalUrl;
        } else {
            final String advancingTab = proposalUrl + "/tab/ADVANCING";
            return "redirect:" + advancingTab;
        }
    }

    private IProposalContestPhaseAttribute setIsFrozen(long proposalId, ContestPhaseWrapper contestPhase,
            boolean isFrozen) {
        return proposalPhaseClient.setProposalContestPhaseAttribute(proposalId,
                contestPhase.getId(),
                ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN, 0L, null,
                String.valueOf(isFrozen));
    }

    @GetMapping("c/{proposalUrlString}/{proposalId}/tab/ADVANCING/saveJudgingFeedback")
    public String saveJudgingFeedback(HttpServletRequest request, HttpServletResponse response,
            ProposalContext proposalContext) {
        final ContestWrapper contest = proposalContext.getContest();
        final ProposalWrapper proposal = proposalContext.getProposal();
        final String redirectUrl = proposal.getProposalLinkUrl(contest,
                proposalContext.getContestPhase().getId());
        AlertMessage.danger(
                "Your rating was NOT saved! The page was reloaded before rating could be saved.")
                .flash(request);
        return "redirect:" + redirectUrl + "#rating";
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADVANCING/saveJudgingFeedback")
    public String saveJudgingFeedback(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, ProposalContext proposalContext,
            @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean,
            BindingResult result, RedirectAttributes redirectAttributes)
            throws IOException {

        final ContestWrapper contest = proposalContext.getContest();
        ProposalWrapper proposal = proposalContext.getProposal();
        ContestPhaseWrapper contestPhase =
                contestClient.getContestPhase(judgeProposalFeedbackBean.getContestPhaseId());
        ProposalsPermissions permissions = proposalContext.getPermissions();
        boolean isPublicRating = permissions.getCanPublicRating();

        if (judgeProposalFeedbackBean.getScreeningUserId() != null && permissions
                .getCanAdminAll()) {
            member = userClient
                    .getMemberUnchecked(judgeProposalFeedbackBean.getScreeningUserId());
        }

        // Security handling
        if (!(permissions.getCanJudgeActions() && proposal.getIsUserAmongSelectedJudges(member.getId())
                || isPublicRating)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        final String redirectUrl = proposal.getProposalLinkUrl(contest, contestPhase.getId());

        if (result.hasErrors()) {
            AlertMessage.danger("Your rating was NOT saved! Please check the form for errors.")
                    .flash(request);
            redirectAttributes
                    .addFlashAttribute("judgeProposalFeedbackBean", judgeProposalFeedbackBean);
            return "redirect:" + redirectUrl + "#rating";
        }

        if (permissions.getCanJudgeActions() && proposal.getIsUserAmongSelectedJudges(member.getId())) {
            isPublicRating = false;
        }

        //find existing ratings
        List<ProposalRatingWrapper> existingRatings =
                proposalJudgeRatingClient.getJudgeRatingsForProposalAndUser(member.getId(),
                        proposal.getId(), contestPhase.getId());

        JudgingUtil.saveRatings(existingRatings, judgeProposalFeedbackBean, proposal.getId(),
                contestPhase.getId(), member.getId(), isPublicRating);

        AlertMessage.success("Rating saved successfully.").flash(request);
        return "redirect:" + redirectUrl;
    }
}
