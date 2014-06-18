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
            throws PortalException, SystemException, ProposalsAuthorizationException {

        if (result.hasErrors()) {
            return;
        }

        Proposal proposal = proposalsContext.getProposal(request);
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

        //disallow saving
        if (isFrozen) {
            return;
        }

        // save judge decision
        if (proposalAdvancingBean.getAdvanceDecision() != JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue()) {
            persistAttribute(proposalId, contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0, proposalAdvancingBean.getAdvanceDecision());
        }

        // save judge comment
        if (proposalAdvancingBean.getAdvanceDecision() != JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue()) {
            ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
            String judgeCommentTemplate = null;
            ProposalsPreferencesWrapper preferences = proposalsContext.getProposalsPreferences(request);
            if (proposalAdvancingBean.getAdvanceDecision() == JudgingSystemActions.AdvanceDecision.MOVE_ON.getAttributeValue()) {
                judgeCommentTemplate = preferences.getAdvanceAcceptanceText(contestPhase.getContestPhaseType());
            } else if (proposalAdvancingBean.getAdvanceDecision() == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON.getAttributeValue()) {
                judgeCommentTemplate = preferences.getAdvanceRejectionText(contestPhase.getContestPhaseType());
            }

            commentHelper.setAdvancingComment(judgeCommentTemplate, proposalAdvancingBean.getAdvanceComment());
        }

        //freeze the advancement
        if (request.getParameter("isFreeze") != null && request.getParameter("isFreeze").equals("true")) {
            persistAttribute(proposalId, contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ADVANCEMENT_FROZEN, 0, "true");
        }

        //forcefully promote the advancement
        if (request.getParameter("isForcePromotion") != null && request.getParameter("isForcePromotion").equals("true")) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase(proposal, contestPhase);
        }
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
        persistSelectedJudges(proposalId, contestPhaseId, fellowProposalScreeningBean.getSelectedJudges());

        // save fellow action
        if (Validator.isNotNull(fellowProposalScreeningBean.getFellowScreeningAction()) && fellowProposalScreeningBean.getFellowScreeningAction() > 0) {
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, fellowProposalScreeningBean.getFellowScreeningAction());

            //save fellow action comment
            ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));

            if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.INCOMPLETE.getAttributeValue()) {
                commentHelper.setScreeningComment(proposalsContext.getProposalsPreferences(request).getScreeningIncompleteText(), fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
            } else if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.OFFTOPIC.getAttributeValue()) {
                commentHelper.setScreeningComment(proposalsContext.getProposalsPreferences(request).getScreeningOfftopicText(), fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
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

    @ResourceMapping("getJudgingCsv")
    public void getJudgingCsv(ResourceRequest request, ResourceResponse response)
            throws PortalException, SystemException {

        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        User currentUser = proposalsContext.getUser(request);
        // Security handling
        if (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentUser)) &&
                !permissions.getCanAdminAll()) {
            return;
        }

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(themeDisplay.getPortalURL());

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String csvPayload = ContestLocalServiceUtil.getProposalJudgeReviewCsv(proposalsContext.getContest(request),
                    proposalsContext.getContestPhase(request), serviceContext);
            outputStream.write(csvPayload.getBytes());
            response.setContentType("text/csv");
            response.addProperty(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
            response.setProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv");

            response.setContentLength(outputStream.size());
            OutputStream out = response.getPortletOutputStream();
            outputStream.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean persistAttribute(long proposalId, long contestPhaseId, String attributeName, long additionalId, long numericValue) {
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(proposalId, contestPhaseId, attributeName, additionalId);

        attribute.setAdditionalId(additionalId);
        attribute.setNumericValue(numericValue);

        try {
            ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute(attribute);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean persistAttribute(long proposalId, long contestPhaseId, String attributeName, long additionalId, String stringValue) {
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(proposalId, contestPhaseId, attributeName, additionalId);

        attribute.setAdditionalId(additionalId);
        attribute.setStringValue(stringValue);

        try {
            ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute(attribute);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean persistSelectedJudges(long proposalId, long contestPhaseId, List<Long> selectedJudges) {
        ProposalContestPhaseAttribute judges = getProposalContestPhaseAttributeCreateIfNotExists(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0);


        String attributeValue = "";
        if (selectedJudges != null) {
            for (Long userId : selectedJudges) attributeValue += userId + ";";
        }
        judges.setStringValue(attributeValue.replaceAll(";$", ""));

        try {
            ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute(judges);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(long proposalId, long contestPhaseId, String attributeName, long additionalId) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, additionalId);
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            try {
                ProposalContestPhaseAttribute attribute = ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(
                        CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()));
                attribute.setProposalId(proposalId);
                attribute.setContestPhaseId(contestPhaseId);
                attribute.setName(attributeName);
                ProposalContestPhaseAttributeLocalServiceUtil.addProposalContestPhaseAttribute(attribute);
                return attribute;
            } catch (Exception e2) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
