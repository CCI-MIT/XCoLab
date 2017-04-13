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
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.proposals.judging.JudgingUtil;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.view.pages.proposals.requests.ProposalAdvancingBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.util.entity.flash.AlertMessage;

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

    private final ProposalsContext proposalsContext;

    @Autowired
    public ProposalAdvancingTabController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADVANCING")
    public String showAdvancingTab(HttpServletRequest request, Model model) {

        final ProposalTab tab = ProposalTab.ADVANCING;
        setCommonModelAndPageAttributes(request, model, tab);

        if (!tab.getCanAccess(request)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        Proposal proposal = proposalsContext.getProposal(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
        ProposalAdvancingBean bean = new ProposalAdvancingBean(proposalWrapper);

        if (!model.containsAttribute("proposalAdvancingBean")) {
            model.addAttribute("proposalAdvancingBean", bean);
        }

        model.addAttribute("discussionId", proposal.getJudgeDiscussionId());
        model.addAttribute("emailTemplates", bean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());


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
            Model model, @RequestParam(defaultValue = "false") boolean isForcePromotion,
            @RequestParam(defaultValue = "false") boolean isFreeze,
            @RequestParam(defaultValue = "false") boolean isUnfreeze,
            @Valid ProposalAdvancingBean proposalAdvancingBean, BindingResult result)
            throws IOException {

        Proposal proposal = proposalsContext.getProposal(request);
        final Contest contest = proposalsContext.getContest(request);
        long proposalId = proposal.getProposalId();
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        final ClientHelper clients = proposalsContext.getClients(request);
        final ProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();

        final Long phaseId = contestPhase.getContestPhasePK();
        boolean isFrozen = proposalPhaseClient.isProposalContestPhaseAttributeSetAndTrue(proposalId,
                phaseId, ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN);

        if (!(ProposalTab.ADVANCING.getCanAccess(request))
                || (isFrozen && !permissions.getCanAdminAll())) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (result.hasErrors()) {
            return showAdvancingTab(request, model);
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
            Model model, @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean,
            BindingResult result, RedirectAttributes redirectAttributes, Member member)
            throws IOException {

        final Contest contest = proposalsContext.getContest(request);
        Proposal proposal = proposalsContext.getProposalWrapped(request);
        ContestPhase contestPhase =
                ContestClientUtil.getContestPhase(judgeProposalFeedbackBean.getContestPhaseId());
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        Boolean isPublicRating = permissions.getCanPublicRating();

        if (judgeProposalFeedbackBean.getScreeningUserId() != null && permissions
                .getCanAdminAll()) {
            member = MembersClient
                    .getMemberUnchecked(judgeProposalFeedbackBean.getScreeningUserId());
        }

        // Security handling
        if (!(permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudge(member)
                || isPublicRating)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnRedirect(request);
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

        if (permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudge(member)) {
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
