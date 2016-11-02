package org.xcolab.portlets.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.proposals.ProposalRatings;
import org.xcolab.enums.ColabConstants;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.portlets.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalEvaluationTabController extends BaseProposalTabController {
    private final static Log _log = LogFactoryUtil.getLog(ProposalEvaluationTabController.class);
    private static final Long AVERAGE_RESULT_ROUND_FACTOR = 10L;
    private static final String EVALUATION_TAB_VIEW_NAME = "proposalEvaluation";

    private final ProposalsContext proposalsContext;

    @Autowired
    public ProposalEvaluationTabController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    @RequestMapping(params = {"pageToDisplay=proposalDetails_EVALUATION"})
    public String showEvaluation(PortletRequest request, Model model) {

        boolean isFellowScreeningActive = proposalsContext.getContestPhase(request).getFellowScreeningActive();
        boolean showEvaluationRatings = hasContestPassedAnyScreeningPhaseAlready(request);
        boolean showPublicRatingForm = isFellowScreeningActive || showEvaluationRatings;
        showPublicRatingForm = false; // Remove once public rating is approved
        model.addAttribute("showPublicRatingForm", showPublicRatingForm);
        model.addAttribute("showEvaluation", showEvaluationRatings);

        if (showPublicRatingForm) {
            model.addAttribute("judgeProposalBean", getProposalRatingBean(request));
        }

        if (showEvaluationRatings) {
            Proposal proposal = proposalsContext.getProposal(request);
            Contest contest = proposalsContext.getContest(request);

            long discussionId = proposal.getResultsDiscussionId();
            if (discussionId == 0) {
                discussionId = createEvaluationThread(request);
            }

            request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME,
                    new ProposalDiscussionPermissions(request));


            model.addAttribute("evaluationDiscussionId", discussionId);
            model.addAttribute("averageRatingsPerPhase", getAverageRatingsForPastPhases(contest.getContestPK(), proposal,
                    request));
            model.addAttribute("activeContestPhaseOpenForEdit", isActiveContestPhaseOpenForEdit(contest));
            model.addAttribute("showEvaluation", true);
            model.addAttribute("isJudgeReadOnly", true);
            model.addAttribute("authorId", proposal.getAuthorId());
            model.addAttribute("proposalId", proposal.getProposalId());
        }

        setCommonModelAndPageAttributes(request, model, ProposalTab.EVALUATION);
        return EVALUATION_TAB_VIEW_NAME;
    }

    private long createEvaluationThread(PortletRequest request) {
        Proposal proposal = proposalsContext.getProposal(request);
        final long discussionThreadId = createDiscussionThread(request, " results discussion", true);
        proposal.setResultsDiscussionId(discussionThreadId);

        ProposalsContextUtil.getClients(request).getProposalClient().updateProposal(proposal);
        return discussionThreadId;
    }

    private JudgeProposalFeedbackBean getProposalRatingBean(PortletRequest request) {

        Proposal proposalWrapper = proposalsContext.getProposalWrapped(request);
        ProposalJudgeWrapper proposalJudgeWrapper =
                new ProposalJudgeWrapper(proposalWrapper, proposalsContext.getMember(request));
        JudgeProposalFeedbackBean proposalRatingBean =
                new JudgeProposalFeedbackBean(proposalJudgeWrapper);

        Long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        proposalRatingBean.setContestPhaseId(contestPhaseId);

        return proposalRatingBean;
    }

    private boolean hasContestPassedAnyScreeningPhaseAlready(PortletRequest request) {
        boolean hasContestPassedScreeningPhaseAlready = false;

        Contest contest = proposalsContext.getContest(request);
        ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contest.getContestPK());
        List<ContestPhase> allContestPhasesForCurrentContest = ContestClientUtil.getAllContestPhases(contest.getContestPK());

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

    private boolean isActiveContestPhaseOpenForEdit(Contest contest) {
        ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contest.getContestPK());
        Long contestPhaseTypeId = activeContestPhase.getContestPhaseType();
        return ContestClientUtil.getContestPhaseType(contestPhaseTypeId).getStatus().equalsIgnoreCase("OPEN_FOR_EDIT");
    }

    private List<ProposalRatings> getAverageRatingsForPastPhases(Long contestId,
            Proposal proposal, PortletRequest request) {

        List<ProposalRatings> proposalRatings = new ArrayList<>();
        List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(contestId);

        for (ContestPhase contestPhase : contestPhases) {
            boolean isPhasePastScreeningPhase =
                    contestPhase.getFellowScreeningActive() && hasContestPhaseEnded(contestPhase);
            if (isPhasePastScreeningPhase) {

                List<ProposalRating> judgeRatingsForProposal = ProposalJudgeRatingClientUtil
                        .getJudgeRatingsForProposal(proposal.getProposalId(), contestPhase.getContestPhasePK());

                if (!judgeRatingsForProposal.isEmpty()) {
                    try {
                        ProposalJudgingCommentHelper commentHelper =
                                new ProposalJudgingCommentHelper(proposal, contestPhase);
                        ProposalRatings proposalRating;
                        if (wasProposalPromotedInContestPhase(proposal, contestPhase, request)) {
                            proposalRating = calculateAverageRating(judgeRatingsForProposal);
                        } else {
                            proposalRating = new ProposalRatings(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
                            proposalRating.setContestPhase(contestPhase);
                        }
                        proposalRating.setComment(commentHelper.getAdvancingComment());
                        proposalRatings.add(proposalRating);
                    } catch (SystemException e) {
                        _log.warn("Could not create average rating for contest phase", e);
                    } catch (MemberNotFoundException e) {
                        throw new InternalException(e);
                    }
                } else {
                    ProposalRatings proposalRating = getProposalPromotionCommentRating(proposal, contestPhase);
                    proposalRatings.add(proposalRating);
                }
            }
        }
        Collections.reverse(proposalRatings);
        return proposalRatings;
    }

    private Boolean wasProposalPromotedInContestPhase(Proposal proposal, ContestPhase contestPhase,
            PortletRequest request) {
        final ProposalPhaseClient proposalPhaseClient =
                proposalsContext.getClients(request).getProposalPhaseClient();
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

    private ProposalRatings getProposalPromotionCommentRating(Proposal proposal, ContestPhase contestPhase) {
        try {
            ProposalRatings proposalRating = new ProposalRatings(
                    ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
            ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(
                    proposal, contestPhase);
            String promoComment = reviewContentHelper.getPromotionComment(true);
            if (!promoComment.trim().isEmpty()) {
                proposalRating.setComment(promoComment);
                proposalRating.setContestPhase(contestPhase);
                return proposalRating;
            } else {
                throw new IllegalStateException("No comment set for this proposal: " + proposal.getProposalId()
                        + " in this rating phase: " + contestPhase.getContestPhasePK());
            }
        } catch (MemberNotFoundException e) {
            throw new InternalException(e);
        }
    }

    private ProposalRatings calculateAverageRating(List<ProposalRating> judgeRatingsForProposal) {

        Map<Long, List<ProposalRating>> map = new HashMap<>();
        map.put(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID, new ArrayList<ProposalRating>());

        Map<Long, List<Long>> averageRatingList = new HashMap<>();
        List<Long> judgeIds = new ArrayList<>();
        for (ProposalRating judgeRating : judgeRatingsForProposal) {
            if (judgeRating.getOnlyForInternalUsage()) {
                continue;
            }
            Long ratingAverageIndex = judgeRating.getRatingValueId() / 5L;
            if (!averageRatingList.containsKey(ratingAverageIndex)) {
                averageRatingList.put(ratingAverageIndex, new ArrayList<Long>());
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
