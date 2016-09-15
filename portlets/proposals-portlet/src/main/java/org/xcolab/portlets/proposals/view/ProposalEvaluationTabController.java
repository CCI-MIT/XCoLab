package org.xcolab.portlets.proposals.view;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.enums.ColabConstants;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.portlets.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingsWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
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

    @Autowired
    private ProposalsContext proposalsContext;


    @RequestMapping(params = {"pageToDisplay=proposalDetails_EVALUATION"})
    public String showEvaluation(PortletRequest request, Model model)
            throws PortalException, SystemException {

        boolean isFellowScreeningActive = proposalsContext.getContestPhase(request).getFellowScreeningActive();
        boolean showEvaluationRatings = hasContestPassedAnyScreeningPhaseAlready(request);
        boolean showPublicRatingForm = isFellowScreeningActive || showEvaluationRatings;
        showPublicRatingForm = false; // Remove once public rating is approved
        model.addAttribute("showPublicRatingForm", showPublicRatingForm);
        model.addAttribute("showEvaluation", showEvaluationRatings);

        if (showPublicRatingForm) {
            model.addAttribute("judgeProposalBean", getProposalRatingBean(request));
        }

        try {
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
                model.addAttribute("averageRatingsPerPhase", getAverageRatingsForPastPhases(contest.getContestPK(), proposal));
                model.addAttribute("activeContestPhaseOpenForEdit", isActiveContestPhaseOpenForEdit(contest));
                model.addAttribute("showEvaluation", true);
                model.addAttribute("isJudgeReadOnly", true);
                model.addAttribute("authorId", proposal.getAuthorId());
                model.addAttribute("proposalId", proposal.getProposalId());
            }
        } catch (Exception e) {
            _log.warn("Could not gather ratings for evaluation tab", e);
        }

        setCommonModelAndPageAttributes(request, model, ProposalTab.EVALUATION);
        return EVALUATION_TAB_VIEW_NAME;
    }

    private long createEvaluationThread(PortletRequest request)
            throws SystemException, PortalException {
        Proposal proposal = proposalsContext.getProposal(request);
        final long discussionThreadId = createDiscussionThread(request, " results discussion", true);
        proposal.setResultsDiscussionId(discussionThreadId);
        proposal.persist();
        return discussionThreadId;
    }

    private JudgeProposalFeedbackBean getProposalRatingBean(PortletRequest request) {

        ProposalWrapper proposalWrapper = proposalsContext.getProposalWrapped(request);
        ProposalJudgeWrapper proposalJudgeWrapper =
                new ProposalJudgeWrapper(proposalWrapper, proposalsContext.getMember(request));
        JudgeProposalFeedbackBean proposalRatingBean =
                new JudgeProposalFeedbackBean(proposalJudgeWrapper);

        Long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        proposalRatingBean.setContestPhaseId(contestPhaseId);

        return proposalRatingBean;
    }

    private boolean hasContestPassedAnyScreeningPhaseAlready(PortletRequest request) throws SystemException, PortalException {
        boolean hasContestPassedScreeningPhaseAlready = false;

        Contest contest = proposalsContext.getContest(request);
        ContestPhase activeContestPhase = ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
        List<ContestPhase> allContestPhasesForCurrentContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);

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

    private boolean isActiveContestPhaseOpenForEdit(Contest contest) throws PortalException, SystemException {
        ContestPhase activeContestPhase = ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
        Long contestPhaseTypeId = activeContestPhase.getContestPhaseType();
        return ContestClient.getContestPhaseType(contestPhaseTypeId).getStatus().equalsIgnoreCase("OPEN_FOR_EDIT");
    }

    private List<ProposalRatingsWrapper> getAverageRatingsForPastPhases(Long contestId, Proposal proposal)
            throws SystemException {

        List<ProposalRatingsWrapper> proposalRatings = new ArrayList<>();
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contestId);

        for (ContestPhase contestPhase : contestPhases) {
            boolean isPhasePastScreeningPhase =
                    contestPhase.isFellowScreeningActive() && hasContestPhaseEnded(contestPhase);
            if (isPhasePastScreeningPhase) {

                List<ProposalRating> judgeRatingsForProposal = ProposalRatingLocalServiceUtil
                        .getJudgeRatingsForProposal(proposal.getProposalId(), contestPhase.getContestPhasePK());

                if (!judgeRatingsForProposal.isEmpty()) {
                    try {
                        ProposalJudgingCommentHelper commentHelper =
                                new ProposalJudgingCommentHelper(proposal, contestPhase);
                        ProposalRatingsWrapper proposalRating;
                        if (wasProposalPromotedInContestPhase(proposal, contestPhase)) {
                            proposalRating = calculateAverageRating(judgeRatingsForProposal);
                        } else {
                            proposalRating = new ProposalRatingsWrapper(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
                            proposalRating.setContestPhase(contestPhase);
                        }
                        proposalRating.setComment(commentHelper.getAdvancingComment());
                        proposalRatings.add(proposalRating);
                    } catch (SystemException | PortalException e) {
                        _log.warn("Could not create average rating for contest phase", e);
                    } catch (MemberNotFoundException e) {
                        throw new InternalException(e);
                    }
                } else {
                    ProposalRatingsWrapper proposalRating = getProposalPromotionCommentRating(proposal, contestPhase);
                    proposalRatings.add(proposalRating);
                }
            }
        }
        Collections.reverse(proposalRatings);
        return proposalRatings;
    }

    private Boolean wasProposalPromotedInContestPhase(Proposal proposal, ContestPhase contestPhase) throws SystemException, NoSuchProposalContestPhaseAttributeException {
        ProposalContestPhaseAttribute judgingDecisionAttr = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.JUDGE_DECISION
        );
        if (Validator.isNotNull(judgingDecisionAttr)) {
            Long judgingDecision = judgingDecisionAttr.getNumericValue();
            return judgingDecision.intValue() == JudgingSystemActions.AdvanceDecision.MOVE_ON.getAttributeValue();
        } else {
            return false;
        }
    }

    private ProposalRatingsWrapper getProposalPromotionCommentRating(Proposal proposal, ContestPhase contestPhase) {
        try {
            ProposalRatingsWrapper proposalRating = new ProposalRatingsWrapper(
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

    private ProposalRatingsWrapper calculateAverageRating(List<ProposalRating> judgeRatingsForProposal)
            throws SystemException, PortalException {

        List<Long> judgeIds = new ArrayList<>();
        Map<Long, List<ProposalRating>> map = new HashMap<>();
        Map<Long, List<Long>> averageRatingList = new HashMap<>();
        map.put(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID, new ArrayList<ProposalRating>());

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
            return new ProposalRatingsWrapper(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID, userRatings, AVERAGE_RESULT_ROUND_FACTOR);
        } catch (MemberNotFoundException e) {
            throw new InternalException(e);
        }
    }

    private boolean hasContestPhaseEnded(ContestPhase contestPhase) {
        return contestPhase.getPhaseEndDate().before(new Date());
    }
}
