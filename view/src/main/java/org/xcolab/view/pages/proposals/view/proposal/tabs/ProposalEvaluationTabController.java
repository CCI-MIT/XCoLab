package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.AverageProposalRating;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalRatings;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalEvaluationTabController extends BaseProposalTabController {

    private static final Long AVERAGE_RESULT_ROUND_FACTOR = 10L;
    private static final String EVALUATION_TAB_VIEW_NAME = "/proposals/proposalEvaluation";

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=EVALUATION")
    public String showEvaluation(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper currentMember, ProposalContext proposalContext) {

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanView()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        boolean isFellowScreeningActive = proposalContext.getContestPhase().getFellowScreeningActive();
        boolean showEvaluationRatings = hasContestPassedAnyScreeningPhaseAlready(proposalContext);
        boolean showPublicRatingForm = isFellowScreeningActive || showEvaluationRatings;
        showPublicRatingForm = false; // Remove once public rating is approved
        model.addAttribute("showPublicRatingForm", showPublicRatingForm);
        model.addAttribute("showEvaluation", showEvaluationRatings);

        if (showPublicRatingForm) {
            model.addAttribute("judgeProposalFeedbackBean",
                    getProposalRatingBean(currentMember, proposalContext));
        }

        if (showEvaluationRatings) {
            ProposalWrapper proposal = proposalContext.getProposal();
            ContestWrapper contest = proposalContext.getContest();

            long discussionId = proposal.getResultsDiscussionIdOrCreate();

            ProposalDiscussionPermissions pdp = new ProposalDiscussionPermissions(request,
                    proposalContext.getProposal());
            request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME, pdp);

            model.addAttribute("evaluationDiscussionId", discussionId);
            model.addAttribute("averageRatingsPerPhase", getAverageRatingsForPastPhases(
                    proposalContext, contest, proposal));
            model.addAttribute("activeContestPhaseOpenForEdit", isActiveContestPhaseOpenForEdit(
                    proposalContext, contest));
            model.addAttribute("showEvaluation", true);
            model.addAttribute("authorUserId", proposal.getAuthorUserId());
            model.addAttribute("proposalId", proposal.getId());
        }

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.EVALUATION);
        return EVALUATION_TAB_VIEW_NAME;
    }

    private JudgeProposalFeedbackBean getProposalRatingBean(UserWrapper currentMember,
            ProposalContext proposalContext) {

        ProposalWrapper proposal = proposalContext.getProposal();
        ProposalJudgeWrapper proposalJudgeWrapper =
                new ProposalJudgeWrapper(proposal, currentMember);
        JudgeProposalFeedbackBean proposalRatingBean =
                new JudgeProposalFeedbackBean(proposalJudgeWrapper);

        Long contestPhaseId = proposalContext.getContestPhase().getId();
        proposalRatingBean.setContestPhaseId(contestPhaseId);

        return proposalRatingBean;
    }

    private boolean hasContestPassedAnyScreeningPhaseAlready(ProposalContext proposalContext) {
        boolean hasContestPassedScreeningPhaseAlready = false;

        ContestWrapper contest = proposalContext.getContest();
        ContestPhaseWrapper activeContestPhase = contestClient.getActivePhase(contest.getId());
        List<ContestPhaseWrapper> allContestPhasesForCurrentContest = contestClient.getAllContestPhases(contest.getId());

        for (ContestPhaseWrapper contestPhase : allContestPhasesForCurrentContest) {
            boolean isLastContestPhase = activeContestPhase.getPhaseEndDate() == null;
            boolean isPastContestPhase = !isLastContestPhase && contestPhase.getPhaseEndDate() != null &&
                    contestPhase.getPhaseEndDate().before(activeContestPhase.getPhaseEndDate());
            if (isPastContestPhase || isLastContestPhase) {
                if (contestPhase.getFellowScreeningActive()) {
                    hasContestPassedScreeningPhaseAlready = true;
                    break;
                }
            }
        }
        return hasContestPassedScreeningPhaseAlready;
    }

    private boolean isActiveContestPhaseOpenForEdit(ProposalContext proposalContext, ContestWrapper contest) {
        ContestPhaseWrapper activeContestPhase = proposalContext.getClients().getContestClient().getActivePhase(contest.getId());
        Long contestPhaseTypeId = activeContestPhase.getContestPhaseTypeId();
        return proposalContext.getClients().getContestClient().getContestPhaseType(contestPhaseTypeId).getStatus().equalsIgnoreCase("OPEN_FOR_EDIT");
    }

    private List<ProposalRatings> getAverageRatingsForPastPhases(ProposalContext proposalContext,
            ContestWrapper contest, ProposalWrapper proposal) {
        List<ProposalRatings> proposalRatings = new ArrayList<>();
        List<ContestPhaseWrapper> contestPhases = proposalContext.getClients().getContestClient().getAllContestPhases(contest.getId());

        for (ContestPhaseWrapper contestPhase : contestPhases) {
            boolean isPhasePastScreeningPhase =
                    contestPhase.getFellowScreeningActive() && contestPhase.isEnded();
            if (isPhasePastScreeningPhase) {
                String contestPhaseName = proposalContext.getClients().getContestClient().getContestPhaseName(contestPhase);
                List<ProposalRatingWrapper> judgeRatingsForProposal =
                        proposalContext.getClients().getProposalJudgeRatingClient()
                        .getJudgeRatingsForProposal(proposal.getId(), contestPhase.getId());

                if (!judgeRatingsForProposal.isEmpty()) {
                    ProposalJudgingCommentHelper commentHelper =
                            new ProposalJudgingCommentHelper(proposal, contestPhase);
                    ProposalRatings proposalRating;
                    if (wasProposalPromotedInContestPhase(proposalContext, proposal, contestPhase)) {
                        proposalRating = calculateAverageRating(judgeRatingsForProposal);
                    } else {
                        proposalRating = new AverageProposalRating();
                    }
                    proposalRating.setContestPhase(contestPhase);
                    proposalRating.setContestPhaseTitle(contestPhaseName);
                    proposalRating.setComment(commentHelper.getAdvancingComment());
                    proposalRatings.add(proposalRating);
                } else {
                    ProposalRatings proposalRating = getProposalPromotionCommentRating(proposal, contestPhase, contestPhaseName);
                    if(proposalRating!=null) {
                        proposalRatings.add(proposalRating);
                    }
                }
            }
        }
        Collections.reverse(proposalRatings);
        return proposalRatings;
    }

    private Boolean wasProposalPromotedInContestPhase(ProposalContext proposalContext, ProposalWrapper proposal, ContestPhaseWrapper contestPhase) {
        final IProposalPhaseClient proposalPhaseClient =
                proposalContext.getClients().getProposalPhaseClient();
        IProposalContestPhaseAttribute judgingDecisionAttr =
                proposalPhaseClient.getProposalContestPhaseAttribute(
                        contestPhase.getId(),proposal.getId(),
                        ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        if (judgingDecisionAttr != null) {
            Long judgingDecision = judgingDecisionAttr.getNumericValue();
            return judgingDecision.intValue() == JudgingSystemActions.AdvanceDecision.MOVE_ON.getAttributeValue();
        } else {
            return false;
        }
    }

    private ProposalRatings getProposalPromotionCommentRating(ProposalWrapper proposal, ContestPhaseWrapper contestPhase, String contestPhaseName) {
        ProposalRatings proposalRating = new AverageProposalRating();
        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(
                proposal, contestPhase);
        String promoComment = reviewContentHelper.getPromotionComment(true);
        if (!promoComment.trim().isEmpty()) {
            proposalRating.setComment(promoComment);
            proposalRating.setContestPhase(contestPhase);
            proposalRating.setContestPhaseTitle(contestPhaseName);
            return proposalRating;
        } else {
            //throw new IllegalStateException("No comment set for this proposal: " + proposal.getId()
            //        + " in this rating phase: " + contestPhase.getId());
            return null;
        }
    }

    private ProposalRatings calculateAverageRating(List<ProposalRatingWrapper> judgeRatingsForProposal) {
        List<ProposalRatingWrapper> userRatings = new ArrayList<>();

        Map<Long, List<Long>> averageRatingList = new HashMap<>();
        List<Long> judgeIds = new ArrayList<>();
        for (ProposalRatingWrapper judgeRating : judgeRatingsForProposal) {
            if (judgeRating.isOnlyForInternalUsage()) {
                continue;
            }
            Long ratingAverageIndex = judgeRating.getRatingValueId() / 5L;
            if (!averageRatingList.containsKey(ratingAverageIndex)) {
                averageRatingList.put(ratingAverageIndex, new ArrayList<>());
            }
            Long currentRating = judgeRating.getRatingValueId();
            averageRatingList.get(ratingAverageIndex).add(currentRating);
            if (!judgeIds.contains(judgeRating.getUserId())) {
                judgeIds.add(judgeRating.getUserId());
            }
        }

        for (Map.Entry<Long, List<Long>> entry : averageRatingList.entrySet()) {
            Long sumRating = 0L;
            for (Long averageRating : entry.getValue()) {
                sumRating = sumRating + averageRating * AVERAGE_RESULT_ROUND_FACTOR;
            }
            Double averageRating = (double) sumRating / (double) entry.getValue().size();
            int proposalIndex = new ArrayList<>(averageRatingList.keySet()).indexOf(entry.getKey());
            ProposalRatingWrapper proposalRating = judgeRatingsForProposal.get(proposalIndex);
            proposalRating.setRatingValueId(averageRating.longValue());
            userRatings.add(proposalRating);
        }

        return new AverageProposalRating(userRatings, AVERAGE_RESULT_ROUND_FACTOR);
    }
}
