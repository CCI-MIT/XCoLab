package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingsWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import javax.portlet.PortletRequest;
import java.util.*;

@Controller
@RequestMapping("view")
public class ProposalDiscussionTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    private boolean isUserAdmin = false;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_DISCUSSION"})
    public String showDiscussion(PortletRequest request, Model model)
            throws PortalException, SystemException  {

        try {
            if(isPhaseStatusClosedOrOpenForSubmission(request)) {
                model.addAttribute("showDiscussion", false);
            } else {
                Long discussionId = ProposalLocalServiceUtil.getDiscussionIdAndGenerateIfNull(proposalsContext.getProposal(request));

                Long proposalId = proposalsContext.getProposal(request).getProposalId();
                Long contestId = proposalsContext.getContestPhase(request).getContestPK();

                List<ProposalRatingsWrapper> judgeAverageRating = getJudgesAverageRating(contestId, proposalId);
                Collections.reverse(judgeAverageRating);

                model.addAttribute("discussionId", discussionId);
                model.addAttribute("showDiscussion", true);
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
            model.addAttribute("showDiscussion", false);
        }

        setCommonModelAndPageAttributes(request, model, ProposalTab.DISCUSSION);
        return "proposalDiscussion";
    }


    private List<ProposalRatingsWrapper> getJudgesAverageRating(Long contestId, Long proposalId) throws Exception{
        List<ProposalRatingsWrapper> wrappers = new ArrayList<ProposalRatingsWrapper>();
        List<ContestPhase> contestPhases =  ContestPhaseLocalServiceUtil.getPhasesForContest(contestId);

        // TODO this is the Admin Id, replace with what Laur and Patrick decide to use
        Long userId = 10144L;
        Long amountOfCriteria = 5L;

        for(ContestPhase contestPhase : contestPhases){

            if(contestPhase.isFellowScreeningActive()) {
                List<ProposalRating> judgeRatingsForProposal = ProposalRatingLocalServiceUtil
                        .getJudgeRatingsForProposal(proposalId, contestPhase.getContestPhasePK());

                List<Long> judgeIds = new ArrayList<>();

                if (judgeRatingsForProposal.size() > 0) {
                    Map<Long, List<ProposalRating>> map = new HashMap<>();
                    Map<Long, List<Long>> averageRatingList = new HashMap<>();
                    map.put(userId, new ArrayList<ProposalRating>());

                    for (ProposalRating r : judgeRatingsForProposal) {
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
                            sumRating = sumRating + averageRating;
                        }
                        Long averageRating = sumRating / averageRatingList.get(averageRatingsIndex).size();
                        ProposalRating proposalRating = judgeRatingsForProposal.get(averageRatingsIndex.intValue());
                        proposalRating.setRatingValueId(averageRating);
                        proposalRating.setUserId(userId);
                        map.get(userId).add(proposalRating);
                    }

                    List<ProposalRating> userRatings = map.get(userId);
                    ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(userId, userRatings);
                    Proposal proposal =  ProposalLocalServiceUtil.getProposal(proposalId);
                    ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
                    wrapper.setComment(commentHelper.getAdvancingComment());
                    wrappers.add(wrapper);
                }
            }

        }
        return wrappers;
    }

    private boolean isPhaseStatusClosedOrOpenForSubmission(PortletRequest request
    ) throws SystemException, PortalException {
        Long contestPhaseType = proposalsContext.getContestPhase(request).getContestPhaseType();
        boolean phaseStatusOpenForSubmission =
                ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhaseType).getStatus().equalsIgnoreCase("OPEN_FOR_SUBMISSION");
        boolean phaseStatusClosed =
                ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhaseType).getStatus().equalsIgnoreCase("CLOSED");
        return phaseStatusOpenForSubmission || phaseStatusClosed;
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
    
}
