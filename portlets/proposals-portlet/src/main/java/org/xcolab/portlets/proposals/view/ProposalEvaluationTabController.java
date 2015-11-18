package org.xcolab.portlets.proposals.view;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingsWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("view")
public class ProposalEvaluationTabController extends BaseProposalTabController {
    private final static Log _log = LogFactoryUtil.getLog(ProposalEvaluationTabController.class);
    private static final Long AVERAGE_RESULT_ROUND_FACTOR = 10L;
    private static final String EVALUATION_TAB_VIEW_NAME = "proposalEvaluation";

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"pageToDisplay=proposalDetails_DISCUSSION"})
    public String showEvaluationControllerForPreviousTabName(PortletRequest request, Model model)
            throws PortalException, SystemException {
        return showEvaluation(request, model);
    }

    @RequestMapping(params = {"pageToDisplay=proposalDetails_EVALUATION"})
    public String showEvaluation(PortletRequest request, Model model)
            throws PortalException, SystemException {

        boolean isFellowScreeningActive = proposalsContext.getContestPhase(request).getFellowScreeningActive();
        boolean showEvaluationRatings = hasContestPassedAnyScreeningPhaseAlready(request);
        boolean showPublicRatingForm = isFellowScreeningActive || showEvaluationRatings;
        showPublicRatingForm = false; // Remove once public rating is approved
        model.addAttribute("showPublicRatingForm", showPublicRatingForm);
        model.addAttribute("showEvaluation", showEvaluationRatings);

        try {
            if (showPublicRatingForm) {
                model.addAttribute("judgeProposalBean", getProposalRatingBean(request));
            }
        } catch (Exception e) {
            _log.warn("Could not create public rating form for evaluation tab", e);
        }

        try {
            if (showEvaluationRatings) {
                Proposal proposal = proposalsContext.getProposal(request);
                Contest contest = proposalsContext.getContest(request);
                Long evaluationDiscussionId = ProposalLocalServiceUtil.getDiscussionIdAndGenerateIfNull(proposal);
                model.addAttribute("evaluationDiscussionId", evaluationDiscussionId);
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

    private JudgeProposalFeedbackBean getProposalRatingBean(PortletRequest request) throws SystemException, PortalException {

        ProposalWrapper proposalWrapper =
                proposalsContext.getProposalWrapped(request);
        ProposalJudgeWrapper proposalJudgeWrapper =
                new ProposalJudgeWrapper(proposalWrapper, proposalsContext.getUser(request));
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

    private boolean isActiveContestPhaseOpenForEdit(Contest contest) throws Exception{
        ContestPhase activeContestPhase = ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
        Long contestPhaseTypeId = activeContestPhase.getContestPhaseType();
        return ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhaseTypeId).getStatus().equalsIgnoreCase("OPEN_FOR_EDIT");
    }

    private List<ProposalRatingsWrapper> getAverageRatingsForPastPhases(Long contestId, Proposal proposal)
            throws Exception {

        List<ProposalRatingsWrapper> proposalRatings = new ArrayList<>();
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contestId);

        for (ContestPhase contestPhase : contestPhases) {
            boolean isPhasePastScreeningPhase =
                    contestPhase.isFellowScreeningActive() && hasContestPhaseEnded(contestPhase);
            if (isPhasePastScreeningPhase) {

                List<ProposalRating> judgeRatingsForProposal = ProposalRatingLocalServiceUtil
                        .getJudgeRatingsForProposal(proposal.getProposalId(), contestPhase.getContestPhasePK());

                if (judgeRatingsForProposal.size() > 0) {
                    try {
                        ProposalJudgingCommentHelper commentHelper =
                                new ProposalJudgingCommentHelper(proposal, contestPhase);
                        ProposalRatingsWrapper proposalRating;
                        if(wasProposalPromotedInContestPhase(proposal, contestPhase)) {
                            proposalRating = calculateAverageRating(judgeRatingsForProposal);
                        } else {
                            proposalRating = new ProposalRatingsWrapper(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
                            proposalRating.setContestPhase(contestPhase);
                        }
                        proposalRating.setComment(commentHelper.getAdvancingComment());
                        proposalRatings.add(proposalRating);
                    } catch (Exception e) {
                        _log.warn("Could not create average rating for contest phase", e);
                    }
                } else {
                    try {
                        ProposalRatingsWrapper proposalRating = getProposalPromotionCommentRating(proposal, contestPhase);
                        proposalRatings.add(proposalRating);
                    } catch (Exception e ){
                        _log.warn("Could not create rating wrapper for comment and contest phase", e);
                    }
                }
            }
        }
        Collections.reverse(proposalRatings);
        return proposalRatings;
    }

    private Boolean wasProposalPromotedInContestPhase(Proposal proposal, ContestPhase contestPhase) throws PortalException, SystemException{
        ProposalContestPhaseAttribute judgingDecisionAttr = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.JUDGE_DECISION
        );
        if(Validator.isNotNull(judgingDecisionAttr)){
            Long judgingDecision = judgingDecisionAttr.getNumericValue();
            return judgingDecision.intValue() == JudgingSystemActions.AdvanceDecision.MOVE_ON.getAttributeValue();
        } else {
            return false;
        }
    }

    private ProposalRatingsWrapper getProposalPromotionCommentRating(Proposal proposal, ContestPhase contestPhase) throws Exception {
        ProposalRatingsWrapper proposalRating = new ProposalRatingsWrapper(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String promoComment = reviewContentHelper.getPromotionComment(true);
        if(!promoComment.trim().isEmpty()) {
            proposalRating.setComment(promoComment);
            proposalRating.setContestPhase(contestPhase);
            return proposalRating;
        }
        throw new Exception("No comment set for this proposal: " + proposal.getProposalId() + " in this rating phase: " + contestPhase.getContestPhasePK());
    }

    private ProposalRatingsWrapper getProposalAdvacningCommentRating(Proposal proposal, ContestPhase contestPhase) throws Exception {
        List<ProposalRating> proposalRatings = Arrays.asList();
        ProposalRatingsWrapper proposalRating = new ProposalRatingsWrapper(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID, proposalRatings);
        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String promoComment = reviewContentHelper.getPromotionComment(true);
        if(!promoComment.trim().isEmpty()) {
            proposalRating.setComment(promoComment);
            proposalRating.setContestPhase(contestPhase);
            return proposalRating;
        }
        throw new Exception("No comment set for this proposal: " + proposal.getProposalId() + " in this rating phase: " + contestPhase.getContestPhasePK());
    }

    private ProposalRatingsWrapper calculateAverageRating(List<ProposalRating> judgeRatingsForProposal)
            throws Exception {

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

        for (Long averageRatingsIndex : averageRatingList.keySet()) {
            Long sumRating = 0L;
            for (Long averageRating : averageRatingList.get(averageRatingsIndex)) {
                sumRating = sumRating + averageRating * AVERAGE_RESULT_ROUND_FACTOR;
            }
            Double averageRating = (double) sumRating / (double) averageRatingList.get(averageRatingsIndex).size();
            int proposalIndex = new ArrayList<>(averageRatingList.keySet()).indexOf(averageRatingsIndex);
            ProposalRating proposalRating = judgeRatingsForProposal.get(proposalIndex);
            proposalRating.setRatingValueId(averageRating.longValue());
            proposalRating.setUserId(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
            map.get(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID).add(proposalRating);
        }

        List<ProposalRating> userRatings = map.get(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID);
        return new ProposalRatingsWrapper(ColabConstants.CLIMATE_COLAB_TEAM_USER_ID, userRatings, AVERAGE_RESULT_ROUND_FACTOR);
}

    private boolean hasContestPhaseEnded(ContestPhase contestPhase) {
        return contestPhase.getPhaseEndDate().before(new Date());
    }

    private boolean isUserAllowToAddComments(User user, Proposal proposal, PortletRequest request) throws Exception {
        return isUserFellowOrJudge(user) || isUserProposalAuthorOrTeamMember(user, proposal) || userIsAdmin(request);
    }

    private boolean isUserFellowOrJudge(User user) throws Exception {
        boolean isFellow = false;
        boolean isJudge = false;

        // TODO this only checks for the Role but not whether the user has this role in this contest
        isJudge = RoleLocalServiceUtil.hasUserRole(user.getUserId(), MemberRole.JUDGE.getRoleId());
        isFellow = RoleLocalServiceUtil.hasUserRole(user.getUserId(), MemberRole.FELLOW.getRoleId());
        return isFellow || isJudge;
    }

    private boolean isUserProposalAuthorOrTeamMember(User user, Proposal proposal) throws Exception {
        boolean isAuthor = proposal.getAuthorId() == user.getUserId();
        boolean isMember = ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), user.getUserId());
        return isAuthor || isMember;
    }

    private boolean userIsAdmin(PortletRequest request) {
        boolean isUserAdmin = false;
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        if (themeDisplay.getPermissionChecker().isOmniadmin()) {
            isUserAdmin = true;
        }
        return isUserAdmin;
    }

}
