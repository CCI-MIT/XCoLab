package org.xcolab.portlets.proposals.view;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
import com.liferay.portal.model.User;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.*;

import javax.portlet.PortletRequest;
import java.util.*;

@Controller
@RequestMapping("view")
public class ProposalJudgesTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADVANCING"})
    public String showJudgesPanel(PortletRequest request, Model model) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.ADVANCING);

        Proposal proposal = proposalsContext.getProposal(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, contestPhase);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        User currentUser = proposalsContext.getUser(request);
        ProposalAdvancingBean bean = new ProposalAdvancingBean(proposalWrapper);
        bean.setContestPhaseId(contestPhase.getContestPhasePK());

        model.addAttribute("discussionId", proposal.getJudgeDiscussionId());
        model.addAttribute("proposalAdvancingBean", bean);
        model.addAttribute("emailTemplates", bean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());


        List<ProposalRating> judgeRatingsUnWrapped = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposal(proposal.getProposalId(), contestPhase.getContestPhasePK());
        List<ProposalRatingsWrapper> fellowRatings = wrapProposalRatings(judgeRatingsUnWrapped);
        List<ProposalRatingsWrapper> judgeRatings = wrapProposalRatings(ProposalRatingLocalServiceUtil.getJudgeRatingsForProposal(proposal.getProposalId(), contestPhase.getContestPhasePK()));
        boolean isFrozen = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN,
                0
        );
        boolean hasAlreadyBeenPromoted = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                0
        );

        boolean hasNoWritePermission = (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentUser)) &&
                !permissions.getCanAdminAll());

        model.addAttribute("hasNoWritePermission", hasNoWritePermission);
        model.addAttribute("isAdmin", permissions.getCanAdminAll());
        model.addAttribute("isJudgeReadOnly", permissions.getCanJudgeActions() && !permissions.getCanFellowActions());
        model.addAttribute("isFrozen", isFrozen);
        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);
        model.addAttribute("fellowRatings", fellowRatings);
        model.addAttribute("judgeRatings", judgeRatings);

        return "proposalAdvancing";
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
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_SCREENING"})
    public String showFellowsPanel(PortletRequest request, Model model)
            throws PortalException, SystemException {
        setCommonModelAndPageAttributes(request, model, ProposalTab.SCREENING);

        Proposal proposal = proposalsContext.getProposal(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, contestPhase);
        ProposalFellowWrapper proposalFellowWrapper = new ProposalFellowWrapper(proposalWrapper, proposalsContext.getUser(request));
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        User currentUser = proposalsContext.getUser(request);

        boolean hasNoWritePermission = (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentUser)) &&
                !permissions.getCanAdminAll());
        boolean hasAlreadyBeenPromoted = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                0
        );
        List<ProposalRating> judgeRatingsUnWrapped = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposal(proposal.getProposalId(), contestPhase.getContestPhasePK());

        FellowProposalScreeningBean bean = new FellowProposalScreeningBean(proposalFellowWrapper);
        bean.setContestPhaseId(contestPhase.getContestPhasePK());

        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);
        model.addAttribute("hasNoWritePermission", hasNoWritePermission);
        model.addAttribute("fellowProposalScreeningBean", bean);
        model.addAttribute("emailTemplates", bean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("judgingOptions", JudgingSystemActions.FellowAction.values());
        model.addAttribute("discussionId", proposal.getFellowDiscussionId());

        return "proposalScreening";
    }
    
}
