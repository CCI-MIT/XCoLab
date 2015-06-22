package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingsWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.*;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import javax.portlet.PortletRequest;
import java.util.*;

@Controller
@RequestMapping("view")
public class ProposalEvaluationTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    private boolean isUserAdmin = false;

    @RequestMapping(params = {"pageToDisplay=proposalDetails_DISCUSSION"})
    public String showEvaluationControllerForPreviousTabName(PortletRequest request, Model model)
            throws PortalException, SystemException {
        return showEvaluation(request, model);
    }

    @RequestMapping(params = {"pageToDisplay=proposalDetails_EVALUATION"})
    public String showEvaluation(PortletRequest request, Model model)
            throws PortalException, SystemException  {

        try {
            boolean isFellowScreeningActive = proposalsContext.getContestPhase(request).getFellowScreeningActive() == true;
            model.addAttribute("isFellowScreeningActive", isFellowScreeningActive);
            if(isFellowScreeningActive){
                populateJudgeProposalBean(request, model);
            }
        } catch (Exception e){
            model.addAttribute("isFellowScreeningActive", false);
        }

        try {
            if(!hasContestPassedScreeningPhaseAlready(request)) {
                model.addAttribute("showEvaluation", false);
            } else {
                Long evaluationDiscussionId = ProposalLocalServiceUtil.getDiscussionIdAndGenerateIfNull(proposalsContext.getProposal(request));

                Long proposalId = proposalsContext.getProposal(request).getProposalId();
                Long contestId = proposalsContext.getContestPhase(request).getContestPK();

                List<ProposalRatingsWrapper> judgeAverageRating = getJudgesAverageRating(contestId, proposalId);
                Collections.reverse(judgeAverageRating);

                model.addAttribute("evaluationDiscussionId", evaluationDiscussionId);
                model.addAttribute("showEvaluation", true);
                model.addAttribute("isJudgeReadOnly", true);
                model.addAttribute("judgeAverageRating", judgeAverageRating);
                model.addAttribute("authorId", proposalsContext.getProposal(request).getAuthorId());
                model.addAttribute("proposalId", proposalId);

                ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
                if (themeDisplay.getPermissionChecker().isOmniadmin()){
                    isUserAdmin = true;
                }

            }
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("showEvaluation", false);
        }

        setCommonModelAndPageAttributes(request, model, ProposalTab.EVALUATION);
        return "proposalEvaluation";
    }


    private List<ProposalRatingsWrapper> getJudgesAverageRating(Long contestId, Long proposalId) throws Exception{
        Long CLIMATE_COLAB_TEAM_USER_ID = 1431053L;
        Long AVERAGE_RESULT_ROUND_FACTOR = 10L;

        List<ProposalRatingsWrapper> wrappers = new ArrayList<ProposalRatingsWrapper>();
        List<ContestPhase> contestPhases =  ContestPhaseLocalServiceUtil.getPhasesForContest(contestId);

        for(ContestPhase contestPhase : contestPhases){

            if(contestPhase.isFellowScreeningActive()) {
                List<ProposalRating> judgeRatingsForProposal = ProposalRatingLocalServiceUtil
                        .getJudgeRatingsForProposal(proposalId, contestPhase.getContestPhasePK());

                List<Long> judgeIds = new ArrayList<>();

                if (judgeRatingsForProposal.size() > 0) {
                    Map<Long, List<ProposalRating>> map = new HashMap<>();
                    Map<Long, List<Long>> averageRatingList = new HashMap<>();
                    map.put(CLIMATE_COLAB_TEAM_USER_ID, new ArrayList<ProposalRating>());

                    for (ProposalRating r : judgeRatingsForProposal) {
                        if (r.getOnlyForInternalUsage()){
                            continue;
                        }
                        Long ratingAverageIndex = r.getRatingValueId() / 5L;
                        if(!averageRatingList.containsKey(ratingAverageIndex)){
                            averageRatingList.put(ratingAverageIndex, new ArrayList<Long>());
                        }
                        Long currentRating = r.getRatingValueId();
                        averageRatingList.get(ratingAverageIndex).add(currentRating);
                        if(!judgeIds.contains(r.getUserId())) {
                            judgeIds.add(r.getUserId());
                        }
                    }

                    for (Long averageRatingsIndex : averageRatingList.keySet()) {
                        Long sumRating = 0L;
                        for(Long averageRating : averageRatingList.get(averageRatingsIndex) ){
                            sumRating = sumRating + averageRating * AVERAGE_RESULT_ROUND_FACTOR;
                        }
                        Double averageRating = (double) sumRating / (double)averageRatingList.get(averageRatingsIndex).size();
                        int proposalIndex = new ArrayList<>(averageRatingList.keySet()).indexOf(averageRatingsIndex);
                        ProposalRating proposalRating = judgeRatingsForProposal.get(proposalIndex);
                        proposalRating.setRatingValueId(averageRating.longValue());
                        proposalRating.setUserId(CLIMATE_COLAB_TEAM_USER_ID);
                        map.get(CLIMATE_COLAB_TEAM_USER_ID).add(proposalRating);
                    }

                    List<ProposalRating> userRatings = map.get(CLIMATE_COLAB_TEAM_USER_ID);
                    ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(CLIMATE_COLAB_TEAM_USER_ID, userRatings, AVERAGE_RESULT_ROUND_FACTOR);
                    Proposal proposal =  ProposalLocalServiceUtil.getProposal(proposalId);
                    ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
                    wrapper.setComment(commentHelper.getAdvancingComment());
                    wrappers.add(wrapper);
                }
            }

        }
        return wrappers;
    }

    private boolean hasContestPassedScreeningPhaseAlready(PortletRequest request) throws SystemException, PortalException {
        boolean hasContestPassedScreeningPhaseAlready = false;

        ContestPhase currentContestPhase = proposalsContext.getContestPhase(request);
        Contest contest = proposalsContext.getContest(request);
        List<ContestPhase> allContestPhasesForCurrentContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);

        for(ContestPhase contestPhase : allContestPhasesForCurrentContest){
            boolean isPastContestPhase = contestPhase.getPhaseEndDate().before(currentContestPhase.getPhaseEndDate());
            if(isPastContestPhase){
                if(contestPhase.getFellowScreeningActive()){
                    hasContestPassedScreeningPhaseAlready = true;
                    break;
                }
            }
        }
        return hasContestPassedScreeningPhaseAlready;
    }

    private boolean isUserFellowOrJudge(User user){
        boolean fellow = false;
        boolean judge = false;

        try {
            // TODO this only checks for the Role but not whether the user has this role in this contest
            judge = RoleLocalServiceUtil.hasUserRole(user.getUserId(), MemberRole.JUDGES.getRoleId());
            fellow = RoleLocalServiceUtil.hasUserRole(user.getUserId(), MemberRole.FELLOW.getRoleId());
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return fellow || judge;
    }

    private boolean isUserProposalAuthorOrTeamMember(User user, Proposal proposal){

        try {
            boolean author = proposal.getAuthorId() == user.getUserId();
            boolean member = ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), user.getUserId());
            return author || member;
        } catch (PortalException | SystemException e) {
        }

        return false;
    }

    private boolean isUserAllowToAddComments(User user, Proposal proposal){
        return isUserFellowOrJudge(user) || isUserProposalAuthorOrTeamMember(user, proposal) || isUserAdmin;
    }

    private void populateJudgeProposalBean(PortletRequest request, Model model) throws SystemException, PortalException
    {

        ProposalWrapper proposalWrapper = proposalsContext.getProposalWrapped(request);
        ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposalWrapper, proposalsContext.getUser(request));
        JudgeProposalFeedbackBean judgeProposalBean = new JudgeProposalFeedbackBean(proposalJudgeWrapper);

        Long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        Long userId = proposalsContext.getUser(request).getUserId();
        judgeProposalBean.setContestPhaseId(contestPhaseId);

        //find existing ratings
        List<ProposalRating> existingRatings = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposalAndUser(
                userId,
                proposalWrapper.getProposalId(),
                contestPhaseId);

        if (!existingRatings.isEmpty()) {
            Map<Long, String> existingJudgeRating = new LinkedHashMap<>();
            Long index = 1L;
            for (ProposalRating proposalRating : existingRatings) {
                existingJudgeRating.put(index, "" + proposalRating.getRatingValueId());
                index++;
            }
            String existingComment = existingRatings.get(0).getComment();
            judgeProposalBean.setRatingValues(existingJudgeRating);
            judgeProposalBean.setComment(existingComment);
        }

        model.addAttribute("judgeProposalBean", judgeProposalBean);
    }
    
}
