package org.xcolab.portlets.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.proposals.ProposalRatings;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalFellowWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
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
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
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
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
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
        List<ProposalRatings> fellowRatings = wrapProposalRatings(fellowRatingsUnWrapped);

        List<ProposalRating> judgesRatingsUnWrapped = ProposalJudgeRatingClientUtil.getJudgeRatingsForProposal(
                proposal.getProposalId(), contestPhase.getContestPhasePK());

        for (Iterator i = judgesRatingsUnWrapped.iterator(); i.hasNext(); ){
            ProposalRating judgesRatingUnWrapped = (ProposalRating) i.next();
            if(judgesRatingUnWrapped.getOnlyForInternalUsage()) {
                i.remove();
            }
        }

        List<ProposalRatings> judgeRatings = wrapProposalRatings(judgesRatingsUnWrapped);
        boolean isFrozen = ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN
        );
        boolean hasAlreadyBeenPromoted = ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE
        );

        model.addAttribute("isFrozen", isFrozen);
        model.addAttribute("hasAlreadyBeenPromoted", hasAlreadyBeenPromoted);

        model.addAttribute("fellowRatings", fellowRatings);
        model.addAttribute("judgeRatings", judgeRatings);
    }


    private static List<ProposalRatings> wrapProposalRatings(List<ProposalRating> ratings)
            throws SystemException, PortalException {
        List<ProposalRatings> wrappers = new ArrayList<>();
        Map<Long, List<ProposalRating>> ratingsByUserId = new HashMap<>();

        for (ProposalRating r : ratings) {
                if (ratingsByUserId.get(r.getUserId()) == null) {
                    ratingsByUserId.put(r.getUserId(), new ArrayList<ProposalRating>());
                }
                ratingsByUserId.get(r.getUserId()).add(r);
            }

            for (Map.Entry<Long, List<ProposalRating>> entry : ratingsByUserId.entrySet()) {
                List<ProposalRating> userRatings = entry.getValue();
                ProposalRatings wrapper = new ProposalRatings(entry.getKey(), userRatings);
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
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);
        ProposalFellowWrapper proposalFellowWrapper = new ProposalFellowWrapper(
                proposalWrapper, proposalsContext.getMember(request), request);

        boolean hasAlreadyBeenPromoted = ProposalPhaseClientUtil.isProposalContestPhaseAttributeSetAndTrue(
                proposal.getProposalId(),
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE
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
