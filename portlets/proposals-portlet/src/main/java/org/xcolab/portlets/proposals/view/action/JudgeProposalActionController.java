package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("view")
public class JudgeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=sendComment"})
    public void sendComment(ActionRequest request, Model model, ActionResponse response) throws SystemException, PortalException, AddressException, MailEngineException {
        // Security handling
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!permissions.getCanAdminAll()) {
            return;
        }

        ProposalWrapper proposal = new ProposalWrapper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));
        ProposalLocalServiceUtil.contestPhasePromotionEmailNotifyProposalContributors(proposal.getWrapped(), proposalsContext.getContestPhase(request), request);
        ProposalLocalServiceUtil.contestPhasePromotionCommentNotifyProposalContributors(proposal.getWrapped(), proposalsContext.getContestPhase(request));

    }

    @RequestMapping(params = {"action=saveAdvanceDetails"})
    public void saveAdvanceDetails(ActionRequest request, Model model,
                                ActionResponse response, @Valid ProposalAdvancingBean proposalAdvancingBean,
                                BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        if (result.hasErrors()) {
            return;
        }

        Proposal proposal = proposalsContext.getProposal(request);
        long contestId = proposalsContext.getContest(request).getContestPK();
        long proposalId = proposal.getProposalId();
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        User currentUser = proposalsContext.getUser(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        // Security handling
        if (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentUser)) &&
                !permissions.getCanAdminAll()) {
            return;
        }

        //check if advancement was frozen
        boolean isFrozen = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                proposalId,
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN,
                0
        );

        //disallow saving for non-admins
        if (isFrozen && !permissions.getCanAdminAll()) {
            return;
        }

        // save judge decision
        if (proposalAdvancingBean.getAdvanceDecision() != JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue()) {
            ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                    proposalId,
                    contestPhase.getContestPhasePK(),
                    ProposalContestPhaseAttributeKeys.JUDGE_DECISION,
                    0,
                    proposalAdvancingBean.getAdvanceDecision()
            );
        }

        // save judge comment
        if (proposalAdvancingBean.getAdvanceDecision() != JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue()) {
            ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);

            commentHelper.setAdvancingComment(proposalAdvancingBean.getAdvanceComment());
        }

        //freeze the advancement
        if (request.getParameter("isFreeze") != null && request.getParameter("isFreeze").equals("true")) {
            ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                    proposalId,
                    contestPhase.getContestPhasePK(),
                    ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN,
                    0,
                    "true"
            );
        }

        //unfreeze the advancement
        if (permissions.getCanAdminAll() && request.getParameter("isUnfreeze") != null && request.getParameter("isUnfreeze").equals("true")) {
            ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                    proposalId,
                    contestPhase.getContestPhasePK(),
                    ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN,
                    0,
                    "false"
            );
        }

        //forcefully promote the advancement
        if (permissions.getCanAdminAll() && request.getParameter("isForcePromotion") != null && request.getParameter("isForcePromotion").equals("true")) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase(proposal, contestPhase);
        }

        response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+contestPhase.getContestPhasePK()+"/planId/"+proposalId+"/tab/ADVANCING");
    }

    @RequestMapping(params = {"action=saveJudgingFeedback"})
    public void saveJudgingFeedback(ActionRequest request, Model model, ActionResponse response,
                                    @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean,
                                    BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException, AddressException, MailEngineException {

        if (result.hasErrors()) {
            return;
        }

        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        User currentUser = proposalsContext.getUser(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        // Security handling
        if (!(permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudge(currentUser))) {
            return;
        }

        //find existing rating
        ProposalRating proposalRating = ProposalRatingLocalServiceUtil.getJudgeRatingForProposal(
                currentUser.getUserId(),
                proposal.getProposalId(),
                contestPhase.getContestPhasePK());
        if (proposalRating != null) {
            ProposalRatingLocalServiceUtil.updateRating(
                    proposalRating.getId(),
                    judgeProposalFeedbackBean.getJudgeRating(),
                    judgeProposalFeedbackBean.getJudgeComment(),
                    ""
            );
        } else {
            //create a new rating
            ProposalRatingLocalServiceUtil.addJudgeRating(
                    proposal.getProposalId(),
                    contestPhase.getContestPhasePK(),
                    currentUser.getUserId(),
                    judgeProposalFeedbackBean.getJudgeRating(),
                    judgeProposalFeedbackBean.getJudgeComment(),
                    ""
            );
        }

    }

    @RequestMapping(params = {"action=saveScreening"})
    public void saveScreening(ActionRequest request, Model model,
                                 ActionResponse response,
                                 @ModelAttribute("fellowProposalScreeningBean") @Valid FellowProposalScreeningBean fellowProposalScreeningBean,
                                 BindingResult result) throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        if (result.hasErrors()) {
            SessionErrors.clear(request);
            SessionMessages.clear(request);
            return;
        }

        long contestId = proposalsContext.getContest(request).getContestPK();
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        User currentUser = proposalsContext.getUser(request);

        // Security handling
        if (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentUser)) &&
                !permissions.getCanAdminAll()) {
            return;
        }

        // save selection of judges
        if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.PASS_TO_JUDGES.getAttributeValue()) {
            ProposalContestPhaseAttributeLocalServiceUtil.persistSelectedJudgesAttribute(
                    proposalId,
                    contestPhaseId,
                    fellowProposalScreeningBean.getSelectedJudges()
            );
        } else {
            //clear selected judges attribute since the decision is not to pass the proposal.
            ProposalContestPhaseAttributeLocalServiceUtil.persistSelectedJudgesAttribute(
                    proposalId,
                    contestPhaseId,
                    null
            );
        }

        // save fellow action
        if (Validator.isNotNull(fellowProposalScreeningBean.getFellowScreeningAction())) {
            ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                    proposalId,
                    contestPhaseId,
                    ProposalContestPhaseAttributeKeys.FELLOW_ACTION,
                    0,
                    fellowProposalScreeningBean.getFellowScreeningAction()
            );

            //save fellow action comment
            ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));

            if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.INCOMPLETE.getAttributeValue()) {
                commentHelper.setScreeningComment(fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
            } else if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.OFFTOPIC.getAttributeValue()) {
                commentHelper.setScreeningComment(fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
            }
        }

        // save fellow comment and rating
        String comment = fellowProposalScreeningBean.getFellowScreeningRatingComment();
        Long rating = fellowProposalScreeningBean.getFellowScreeningRating();

        //find existing rating
        ProposalRating proposalRating = ProposalRatingLocalServiceUtil.getFellowRatingForProposal(
                currentUser.getUserId(),
                proposalId,
                contestPhaseId);
        //update if existent
        if (proposalRating != null) {
            ProposalRatingLocalServiceUtil.updateRating(
                    proposalRating.getId(),
                    rating,
                    comment,
                    ""
            );
        } else {
            //create a new rating
            ProposalRatingLocalServiceUtil.addFellowRating(
                    proposalId,
                    contestPhaseId,
                    currentUser.getUserId(),
                    rating,
                    comment,
                    ""
            );
        }
        response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+contestPhaseId+"/planId/"+proposalId+"/tab/SCREENING");
    }





}
