package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.proposals.ProposalRatings;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.judging.JudgingUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.view.pages.proposals.requests.ProposalAdvancingBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.commons.servlet.flash.AlertMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalAdvancingTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADVANCING")
    public String showAdvancingTab(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, ProposalContext proposalContext) {

        final ProposalTab tab = ProposalTab.ADVANCING;
        setCommonModelAndPageAttributes(request, model, proposalContext, tab);

        if (!tab.getCanAccess(proposalContext)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        Proposal proposal = proposalContext.getProposal();
        Contest contest = proposalContext.getContest();
        ContestPhase contestPhase = proposalContext.getContestPhase();
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
        ProposalAdvancingBean advancingBean = new ProposalAdvancingBean(proposalWrapper);

        List<ContestPhase> contestPhases = contest.getVisiblePhases();
        final Predicate<ContestPhase> isAfterCurrentPhase =
                phase -> phase.getPhaseStartDateInstant()
                        .isAfter(contestPhase.getPhaseStartDateInstant());
        final Predicate<ContestPhase> isJudgedPhase =
                phase -> phase.getContestPhaseTypeObject().getDefaultPromotionTypeEnum()
                        == ContestPhasePromoteType.PROMOTE_JUDGED;
        final boolean isFinalistPhase = contestPhases.stream()
                        .filter(isAfterCurrentPhase)
                        .noneMatch(isJudgedPhase);

        if (!model.containsAttribute("proposalAdvancingBean")) {
            model.addAttribute("proposalAdvancingBean", advancingBean);
        }

        model.addAttribute("emailTemplates", advancingBean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());
        model.addAttribute("isFinalistPhase", isFinalistPhase);

        List<ProposalRating> fellowRatingsUnWrapped =
                ProposalJudgeRatingClientUtil.getFellowRatingsForProposal(
                        proposal.getProposalId(), contestPhase.getContestPhasePK());
        List<ProposalRatings> fellowRatings = wrapProposalRatings(fellowRatingsUnWrapped);

        List<ProposalRating> judgesRatingsUnWrapped =
                ProposalJudgeRatingClientUtil.getJudgeRatingsForProposal(
                        proposal.getProposalId(), contestPhase.getContestPhasePK());

        for (Iterator i = judgesRatingsUnWrapped.iterator(); i.hasNext(); ) {
            ProposalRating judgesRatingUnWrapped = (ProposalRating) i.next();
            if (judgesRatingUnWrapped.getOnlyForInternalUsage()) {
                i.remove();
            }
        }

        List<ProposalRatings> judgeRatings = wrapProposalRatings(judgesRatingsUnWrapped);
        boolean isFrozen = ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN
        );
        boolean hasAlreadyBeenPromoted =
                ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                        proposal.getProposalId(),
                        contestPhase.getContestPhasePK(),
                        ProposalContestPhaseAttributeKeys.PROMOTE_DONE
                );

        model.addAttribute("isFrozen", isFrozen);
        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);

        model.addAttribute("fellowRatings", fellowRatings);
        model.addAttribute("judgeRatings", judgeRatings);

        return "proposals/proposalAdvancing";
    }

    private static List<ProposalRatings> wrapProposalRatings(List<ProposalRating> ratings) {
        Map<Long, List<ProposalRating>> ratingsByUserId = new HashMap<>();

        for (ProposalRating r : ratings) {
            ratingsByUserId.computeIfAbsent(r.getUserId(), k -> new ArrayList<>());
            ratingsByUserId.get(r.getUserId()).add(r);
        }

        List<ProposalRatings> wrappers = new ArrayList<>();
        for (Map.Entry<Long, List<ProposalRating>> entry : ratingsByUserId.entrySet()) {
            List<ProposalRating> userRatings = entry.getValue();
            ProposalRatings wrapper = new ProposalRatings(entry.getKey(), userRatings);
            wrappers.add(wrapper);
        }
        return wrappers;
    }

    @PostMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADVANCING")
    public String saveAdvanceDetails(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, ProposalContext proposalContext,
            @RequestParam(defaultValue = "false") boolean isForcePromotion,
            @RequestParam(defaultValue = "false") boolean isFreeze,
            @RequestParam(defaultValue = "false") boolean isUnfreeze,
            @Valid ProposalAdvancingBean proposalAdvancingBean, BindingResult result) {

        Proposal proposal = proposalContext.getProposal();
        final Contest contest = proposalContext.getContest();
        long proposalId = proposal.getProposalId();
        ContestPhase contestPhase = proposalContext.getContestPhase();
        ProposalsPermissions permissions = proposalContext.getPermissions();

        final ClientHelper clients = proposalContext.getClients();
        final ProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();

        final Long phaseId = contestPhase.getContestPhasePK();
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
            ContestClientUtil.forcePromotionOfProposalInPhase(proposal.getProposalId(),
                    phaseId);
            return "redirect:" + proposalUrl;
        } else {
            final String advancingTab = proposalUrl + "/tab/ADVANCING";
            return "redirect:" + advancingTab;
        }
    }

    private ProposalContestPhaseAttribute setIsFrozen(long proposalId, ContestPhase contestPhase,
            boolean isFrozen) {
        return ProposalPhaseClientUtil.setProposalContestPhaseAttribute(proposalId,
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN, 0L, null,
                String.valueOf(isFrozen));
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}/tab/ADVANCING/saveJudgingFeedback")
    public String saveJudgingFeedback(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, ProposalContext proposalContext,
            @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean,
            BindingResult result, RedirectAttributes redirectAttributes)
            throws IOException {

        final Contest contest = proposalContext.getContest();
        Proposal proposal = proposalContext.getProposal();
        ContestPhase contestPhase =
                ContestClientUtil.getContestPhase(judgeProposalFeedbackBean.getContestPhaseId());
        ProposalsPermissions permissions = proposalContext.getPermissions();
        Boolean isPublicRating = permissions.getCanPublicRating();

        if (judgeProposalFeedbackBean.getScreeningUserId() != null && permissions
                .getCanAdminAll()) {
            member = MembersClient
                    .getMemberUnchecked(judgeProposalFeedbackBean.getScreeningUserId());
        }

        // Security handling
        if (!(permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudges(member.getUserId())
                || isPublicRating)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        final String redirectUrl = proposal.getWrapped().getProposalLinkUrl(contest,
                contestPhase.getContestPhasePK());

        if (result.hasErrors()) {
            AlertMessage.danger("Your rating was NOT saved! Please check the form for errors.")
                    .flash(request);
            redirectAttributes
                    .addFlashAttribute("judgeProposalFeedbackBean", judgeProposalFeedbackBean);
            return "redirect:" + redirectUrl + "#rating";
        }

        if (permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudges(member.getUserId())) {
            isPublicRating = false;
        }

        //find existing ratings
        List<ProposalRating> existingRatings =
                ProposalJudgeRatingClientUtil.getJudgeRatingsForProposalAndUser(
                        member.getUserId(),
                        proposal.getProposalId(),
                        contestPhase.getContestPhasePK());

        JudgingUtil.saveRatings(existingRatings, judgeProposalFeedbackBean, proposal.getProposalId(),
                contestPhase.getContestPhasePK(), member.getUserId(), isPublicRating);

        AlertMessage.success("Rating saved successfully.").flash(request);
        return "redirect:" + redirectUrl;
    }

}
