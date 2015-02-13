package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingsWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("view")
public class ProposalDiscussionTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
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

                List<ProposalRating> ratings = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposal(proposalId, contestId);
                List<ProposalRatingsWrapper> judgeAverageRating = calculateJudgesAverageRating(wrapProposalRatings(ratings));

                model.addAttribute("discussionId", discussionId);
                model.addAttribute("showDiscussion", true);
                model.addAttribute("judgeAverageRating", judgeAverageRating);
                model.addAttribute("authorId", proposalsContext.getProposal(request).getAuthorId());
                model.addAttribute("proposalId", proposalId);
            }
        } catch (Exception e){
            model.addAttribute("showDiscussion", false);
        }

        setCommonModelAndPageAttributes(request, model, ProposalTab.DISCUSSION);
        return "proposalDiscussion";
    }


    private static List<ProposalRatingsWrapper> wrapProposalRatings(List<ProposalRating> ratings) throws SystemException, PortalException {
        List<ProposalRatingsWrapper> wrappers = new ArrayList<ProposalRatingsWrapper>();
        Map<Long, List<ProposalRating>> map = new HashMap<Long, List<ProposalRating>>();

        for (ProposalRating r : ratings) {
            if (map.get(r.getUserId()) == null) {
                map.put(r.getUserId(), new ArrayList<ProposalRating>());
            }
            map.get(r.getUserId()).add(r);
        }

        for (Long userId : map.keySet()) {
            List<ProposalRating> userRatings = map.get(userId);
            ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(userId, userRatings);
            wrappers.add(wrapper);
        }

        return wrappers;
    }

    private List<ProposalRatingsWrapper> calculateJudgesAverageRating(List<ProposalRatingsWrapper> proposalRatingsWrappers)
    throws SystemException, PortalException{
        List<ProposalRatingsWrapper> averageWrapper = new ArrayList<ProposalRatingsWrapper>();
        //List<ProposalRating> userRatings =  new HashMap<Long, List<ProposalRating>>();
        User user = UserLocalServiceUtil.getDefaultUser(10112L);

        for(ProposalRatingsWrapper individualJudgeRating : proposalRatingsWrappers){
        }

        //ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(user, userRatings);
       // averageWrapper.add(wrapper);
        return averageWrapper;
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
        return isUserFellowOrJudge(user) || isUserProposalAuthorOrTeamMember(user, proposal);
    }
    
}
