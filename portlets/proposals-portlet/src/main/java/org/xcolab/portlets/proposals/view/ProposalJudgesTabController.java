package org.xcolab.portlets.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ext.portlet.JudgingSystemActions;

import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalFellowWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingsWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalJudgesTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADVANCING"})
    public String showJudgesPanel(PortletRequest request, Model model)
            throws PortalException, SystemException, ProposalsAuthorizationException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.ADVANCING);

        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!permissions.getCanSeeAdvancingTab()) {
            throw new ProposalsAuthorizationException(ACCESS_TAB_DENIED_MESSAGE);
        }

        Proposal proposal = proposalsContext.getProposal(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, contestPhase);
        ProposalAdvancingBean bean = new ProposalAdvancingBean(proposalWrapper);
        bean.setContestPhaseId(contestPhase.getContestPhasePK());
        model.addAttribute("proposalAdvancingBean", bean);

        setCommonAdvancingAttributes(request, bean, model);
        return "proposalAdvancing";
    }

    @RequestMapping(params = {"pageToDisplay=proposalDetails_ADVANCING", "error=true"})
    public String showJudgesPanelError(PortletRequest request, Model model)
            throws PortalException, SystemException, ProposalsAuthorizationException {
        setCommonModelAndPageAttributes(request, model, ProposalTab.ADVANCING);

        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!permissions.getCanSeeAdvancingTab()) {
            throw new ProposalsAuthorizationException(ACCESS_TAB_DENIED_MESSAGE);
        }

        Proposal proposal = proposalsContext.getProposal(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, contestPhase);
        ProposalAdvancingBean bean = new ProposalAdvancingBean(proposalWrapper);
        bean.setContestPhaseId(contestPhase.getContestPhasePK());

        setCommonAdvancingAttributes(request, bean, model);
        return "proposalAdvancing";
    }

    private void setCommonAdvancingAttributes(PortletRequest request, ProposalAdvancingBean bean, Model model)
            throws SystemException, PortalException {
        Proposal proposal = proposalsContext.getProposal(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);

        model.addAttribute("discussionId", proposal.getJudgeDiscussionId());
        model.addAttribute("emailTemplates", bean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("advanceOptions", JudgingSystemActions.AdvanceDecision.values());


        List<ProposalRating> fellowRatingsUnWrapped = ProposalJudgeRatingClientUtil.getFellowRatingsForProposal(
                proposal.getProposalId(), contestPhase.getContestPhasePK());
        List<ProposalRatingsWrapper> fellowRatings = wrapProposalRatings(fellowRatingsUnWrapped);

        List<ProposalRating> judgesRatingsUnWrapped = ProposalJudgeRatingClientUtil.getJudgeRatingsForProposal(
                proposal.getProposalId(), contestPhase.getContestPhasePK());

        for (Iterator i = judgesRatingsUnWrapped.iterator(); i.hasNext(); ){
            ProposalRating judgesRatingUnWrapped = (ProposalRating) i.next();
            if(judgesRatingUnWrapped.getOnlyForInternalUsage()) {
                i.remove();
            }
        }

        List<ProposalRatingsWrapper> judgeRatings = wrapProposalRatings(judgesRatingsUnWrapped);
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

        model.addAttribute("isFrozen", isFrozen);
        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);

        model.addAttribute("fellowRatings", fellowRatings);
        model.addAttribute("judgeRatings", judgeRatings);
    }


    private static List<ProposalRatingsWrapper> wrapProposalRatings(List<ProposalRating> ratings)
            throws SystemException, PortalException {
        List<ProposalRatingsWrapper> wrappers = new ArrayList<>();
        Map<Long, List<ProposalRating>> ratingsByUserId = new HashMap<>();

        for (ProposalRating r : ratings) {
                if (ratingsByUserId.get(r.getUserId()) == null) {
                    ratingsByUserId.put(r.getUserId(), new ArrayList<ProposalRating>());
                }
                ratingsByUserId.get(r.getUserId()).add(r);
            }

            for (Map.Entry<Long, List<ProposalRating>> entry : ratingsByUserId.entrySet()) {
                List<ProposalRating> userRatings = entry.getValue();
                ProposalRatingsWrapper wrapper = new ProposalRatingsWrapper(entry.getKey(), userRatings);
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
        ProposalFellowWrapper proposalFellowWrapper = new ProposalFellowWrapper(
                proposalWrapper, proposalsContext.getMember(request));

        boolean hasAlreadyBeenPromoted = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                0
        );

        FellowProposalScreeningBean bean = new FellowProposalScreeningBean(proposalFellowWrapper);
        bean.setContestPhaseId(contestPhase.getContestPhasePK());

        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);
        model.addAttribute("fellowProposalScreeningBean", bean);
        model.addAttribute("emailTemplates", bean.getEmailTemplateBean().getEmailTemplates());
        model.addAttribute("judgingOptions", JudgingSystemActions.FellowAction.values());
        model.addAttribute("discussionId", proposal.getFellowDiscussionId());

        return "proposalScreening";
    }
    
}
