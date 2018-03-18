package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.proposals.ProposalRatings;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;
import org.xcolab.commons.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.commons.enums.promotion.JudgingSystemActions;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.view.pages.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.view.pages.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.util.entity.enums.ColabConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalEvaluationTabController extends BaseProposalTabController {

    private static final Long AVERAGE_RESULT_ROUND_FACTOR = 10L;
    private static final String EVALUATION_TAB_VIEW_NAME = "/proposals/proposalEvaluation";

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=EVALUATION")
    public String showEvaluation(HttpServletRequest request, Model model,
            Member currentMember, ProposalContext proposalContext) {

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
            Proposal proposal = proposalContext.getProposal();
            Contest contest = proposalContext.getContest();

            long discussionId = proposal.getResultsDiscussionId();
            if (discussionId == 0) {
                discussionId = createEvaluationThread(proposalContext);
            }

            ProposalDiscussionPermissions pdp = new ProposalDiscussionPermissions(request,
                    proposalContext);
            pdp.setProposalId(proposalContext.getProposal().getProposalId(),
                    proposalContext.getContestPhase().getContestPhasePK());
            request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME,pdp);


            model.addAttribute("evaluationDiscussionId", discussionId);
            model.addAttribute("averageRatingsPerPhase", getAverageRatingsForPastPhases(
                    proposalContext, contest, proposal));
            model.addAttribute("activeContestPhaseOpenForEdit", isActiveContestPhaseOpenForEdit(
                    proposalContext, contest));
            model.addAttribute("showEvaluation", true);
            model.addAttribute("authorId", proposal.getAuthorId());
            model.addAttribute("proposalId", proposal.getProposalId());
        }

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.EVALUATION);
        return EVALUATION_TAB_VIEW_NAME;
    }

    private long createEvaluationThread(ProposalContext proposalContext) {
        Proposal proposal = proposalContext.getProposal();
        final long discussionThreadId = createDiscussionThread(proposalContext, " results discussion", true);
        proposal.setResultsDiscussionId(discussionThreadId);

        proposalContext.getClients().getProposalClient().updateProposal(proposal);
        return discussionThreadId;
    }

    private JudgeProposalFeedbackBean getProposalRatingBean(Member currentMember,
            ProposalContext proposalContext) {

        Proposal proposal = proposalContext.getProposal();
        ProposalJudgeWrapper proposalJudgeWrapper =
                new ProposalJudgeWrapper(proposal, currentMember);
        JudgeProposalFeedbackBean proposalRatingBean =
                new JudgeProposalFeedbackBean(proposalJudgeWrapper);

        Long contestPhaseId = proposalContext.getContestPhase().getContestPhasePK();
        proposalRatingBean.setContestPhaseId(contestPhaseId);

        return proposalRatingBean;
    }

    private boolean hasContestPassedAnyScreeningPhaseAlready(ProposalContext proposalContext) {
        boolean hasContestPassedScreeningPhaseAlready = false;

        Contest contest = proposalContext.getContest();
        ServiceNamespace contestServiceNamespace = contest.getServiceNamespace();
        ContestPhase activeContestPhase = ContestClient.fromNamespace(contestServiceNamespace).getActivePhase(contest.getContestPK());
        List<ContestPhase> allContestPhasesForCurrentContest = ContestClient.fromNamespace(contestServiceNamespace).getAllContestPhases(contest.getContestPK());

        for (ContestPhase contestPhase : allContestPhasesForCurrentContest) {
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

    private boolean isActiveContestPhaseOpenForEdit(ProposalContext proposalContext, Contest contest) {

        ContestPhase activeContestPhase = proposalContext.getClients().getContestClient().getActivePhase(contest.getContestPK());
        Long contestPhaseTypeId = activeContestPhase.getContestPhaseType();
        return proposalContext.getClients().getContestClient().getContestPhaseType(contestPhaseTypeId).getStatus().equalsIgnoreCase("OPEN_FOR_EDIT");
    }

    private List<ProposalRatings> getAverageRatingsForPastPhases(ProposalContext proposalContext,
            Contest contest, Proposal proposal) {


        List<ProposalRatings> proposalRatings = new ArrayList<>();
        List<ContestPhase> contestPhases = proposalContext.getClients().getContestClient().getAllContestPhases(contest.getContestPK());

        for (ContestPhase contestPhase : contestPhases) {
            boolean isPhasePastScreeningPhase =
                    contestPhase.getFellowScreeningActive() && hasContestPhaseEnded(contestPhase);
            if (isPhasePastScreeningPhase) {
                String contestPhaseName = proposalContext.getClients().getContestClient().getContestPhaseName(contestPhase);
                List<ProposalRating> judgeRatingsForProposal =
                        proposalContext.getClients().getProposalJudgeRatingClient()
                        .getJudgeRatingsForProposal(proposal.getProposalId(), contestPhase.getContestPhasePK());

                if (!judgeRatingsForProposal.isEmpty()) {
                    try {
                        ProposalJudgingCommentHelper commentHelper =
                                new ProposalJudgingCommentHelper(proposal, contestPhase);
                        ProposalRatings proposalRating;
                        if (wasProposalPromotedInContestPhase(proposalContext, proposal, contestPhase)) {
                            proposalRating = calculateAverageRating(judgeRatingsForProposal);
                        } else {
                            proposalRating = new ProposalRatings(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
                        }
                        proposalRating.setContestPhase(contestPhase);
                        proposalRating.setContestPhaseTitle(contestPhaseName);
                        proposalRating.setComment(commentHelper.getAdvancingComment());
                        proposalRatings.add(proposalRating);
                    } catch (MemberNotFoundException e) {
                        throw new InternalException(e);
                    }
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

    private Boolean wasProposalPromotedInContestPhase(ProposalContext proposalContext, Proposal proposal, ContestPhase contestPhase) {
        final ProposalPhaseClient proposalPhaseClient =
                proposalContext.getClients().getProposalPhaseClient();
        ProposalContestPhaseAttribute judgingDecisionAttr =
                proposalPhaseClient.getProposalContestPhaseAttribute(
                        proposal.getProposalId(), contestPhase.getContestPhasePK(),
                        ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        if (judgingDecisionAttr != null) {
            Long judgingDecision = judgingDecisionAttr.getNumericValue();
            return judgingDecision.intValue() == JudgingSystemActions.AdvanceDecision.MOVE_ON.getAttributeValue();
        } else {
            return false;
        }
    }

    private ProposalRatings getProposalPromotionCommentRating(Proposal proposal, ContestPhase contestPhase, String contestPhaseName) {
        try {
            ProposalRatings proposalRating = new ProposalRatings(
                    ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
            ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(
                    proposal, contestPhase);
            String promoComment = reviewContentHelper.getPromotionComment(true);
            if (!promoComment.trim().isEmpty()) {
                proposalRating.setComment(promoComment);
                proposalRating.setContestPhase(contestPhase);
                proposalRating.setContestPhaseTitle(contestPhaseName);
                return proposalRating;
            } else {
                //throw new IllegalStateException("No comment set for this proposal: " + proposal.getProposalId()
                //        + " in this rating phase: " + contestPhase.getContestPhasePK());
                return null;
            }
        } catch (MemberNotFoundException e) {
            throw new InternalException(e);
        }
    }

    private ProposalRatings calculateAverageRating(List<ProposalRating> judgeRatingsForProposal) {

        Map<Long, List<ProposalRating>> map = new HashMap<>();
        map.put(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID, new ArrayList<>());

        Map<Long, List<Long>> averageRatingList = new HashMap<>();
        List<Long> judgeIds = new ArrayList<>();
        for (ProposalRating judgeRating : judgeRatingsForProposal) {
            if (judgeRating.getOnlyForInternalUsage()) {
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
            ProposalRating proposalRating = judgeRatingsForProposal.get(proposalIndex);
            proposalRating.setRatingValueId(averageRating.longValue());
            proposalRating.setUserId(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
            map.get(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID).add(proposalRating);
        }

        List<ProposalRating> userRatings = map.get(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
        try {
            return new ProposalRatings(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID, userRatings, AVERAGE_RESULT_ROUND_FACTOR);
        } catch (MemberNotFoundException e) {
            throw new InternalException(e);
        }
    }

    private boolean hasContestPhaseEnded(ContestPhase contestPhase) {
        return contestPhase.getPhaseEndDate().before(new Date());
    }
}
