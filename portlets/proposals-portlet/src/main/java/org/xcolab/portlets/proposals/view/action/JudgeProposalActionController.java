package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.FellowProposalScreeningBean;
import org.xcolab.portlets.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.portlets.proposals.requests.ProposalAdvancingBean;
import org.xcolab.portlets.proposals.requests.RatingBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.*;


import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
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
import org.xcolab.portlets.proposals.wrappers.*;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;
import java.io.IOException;
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
        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.fetchContestPhase(proposalAdvancingBean.getContestPhaseId());
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

        boolean isUndecided = (proposalAdvancingBean.getAdvanceDecision() == JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue());

        //disallow saving when frozen for non-admins
        if (isFrozen && !permissions.getCanAdminAll()) {
            return;
        }

        // save judge decision
        ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                proposalId,
                contestPhase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.JUDGE_DECISION,
                0,
                proposalAdvancingBean.getAdvanceDecision()
        );

        // save judge comment
        if (!isUndecided) {
            ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);

            commentHelper.setAdvancingComment(proposalAdvancingBean.getAdvanceComment());
        }

        //freeze the advancement
        if (!isUndecided && request.getParameter("isFreeze") != null && request.getParameter("isFreeze").equals("true")) {
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
        if (permissions.getCanAdminAll() && !isUndecided && request.getParameter("isForcePromotion") != null && request.getParameter("isForcePromotion").equals("true")) {
            ContestPhaseLocalServiceUtil.forcePromotionOfProposalInPhase(proposal, contestPhase);
        }

        response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+contestPhase.getContestPhasePK()+"/planId/"+proposalId+"/tab/ADVANCING");
    }

    @ResourceMapping("getJudgingCsv")
    public void getJudgingCsv(ResourceRequest request, ResourceResponse response)
            throws PortalException, SystemException {

        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        User currentUser = proposalsContext.getUser(request);
        // Security handling
        if (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentUser)) &&
                !permissions.getCanAdminAll() && !permissions.getCanJudgeActions()) {
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

    @RequestMapping(params = {"action=saveJudgingFeedback"})
    public void saveJudgingFeedback(ActionRequest request, Model model, ActionResponse response,
                                    @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean,
                                    BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException, AddressException, MailEngineException, IOException {

        if (result.hasErrors()) {
            return;
        }

        long contestId = proposalsContext.getContest(request).getContestPK();
        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);
        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.fetchContestPhase(judgeProposalFeedbackBean.getContestPhaseId());
        User currentUser = proposalsContext.getUser(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        if(judgeProposalFeedbackBean.getScreeningUserId() != null) {
            currentUser = UserLocalServiceUtil.getUser(judgeProposalFeedbackBean.getScreeningUserId());
        }

        // Security handling
        if (!(permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudge(currentUser))) {
            return;
        }

        //find existing ratings
        List<ProposalRating> existingRatings = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposalAndUser(
                currentUser.getUserId(),
                proposal.getProposalId(),
                contestPhase.getContestPhasePK());

        this.saveRatings(existingRatings, judgeProposalFeedbackBean, proposal.getProposalId(), contestPhase.getContestPhasePK(), currentUser.getUserId());
        response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+contestPhase.getContestPhasePK()+"/planId/"+proposal.getProposalId()+"/");

    }

    @RequestMapping(params = {"action=saveScreening"})
    public void saveScreening(ActionRequest request, Model model,
                                 ActionResponse response,
                                 @ModelAttribute("fellowProposalScreeningBean") @Valid FellowProposalScreeningBean fellowProposalScreeningBean,
                                 BindingResult result) throws PortalException, SystemException, ProposalsAuthorizationException, IOException, AddressException, MailEngineException {
        try {
            if (result.hasErrors()) {
                SessionErrors.clear(request);
                SessionMessages.clear(request);
                return;
            }

            long contestId = proposalsContext.getContest(request).getContestPK();
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = fellowProposalScreeningBean.getContestPhaseId();
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
                ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposalsContext.getProposal(request), ContestPhaseLocalServiceUtil.fetchContestPhase(contestPhaseId));

                if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.INCOMPLETE.getAttributeValue()) {
                    commentHelper.setScreeningComment(fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
                } else if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.OFFTOPIC.getAttributeValue()) {
                    commentHelper.setScreeningComment(fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
                } else if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER.getAttributeValue()) {
                    commentHelper.setScreeningComment(fellowProposalScreeningBean.getFellowScreeningActionCommentBody());
                }
            }

            // save fellow comment and rating
            //find existing ratings
            List<ProposalRating> existingRatings = ProposalRatingLocalServiceUtil.getFellowRatingForProposalAndUser(
                    currentUser.getUserId(),
                    proposalId,
                    contestPhaseId);

            this.saveRatings(existingRatings, fellowProposalScreeningBean, proposalId, contestPhaseId, currentUser.getUserId());
            response.sendRedirect("/web/guest/plans/-/plans/contestId/" + contestId + "/phaseId/" + contestPhaseId + "/planId/" + proposalId + "/tab/SCREENING");
        } catch (Exception e) {
            List<Long> recipientIds = new ArrayList<Long>();
            recipientIds.add(1451771L); //Manuel
            recipientIds.add(1011659L); //Patrick
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            MessageUtil.sendMessage("Exception thrown when fellow rated proposal", e.getMessage()+"\n\n"+exceptionAsString, 1010458L, 1010458L, recipientIds, null);
            throw e;
        }
    }


    private void saveRatings(List<ProposalRating> existingRatings, RatingBean ratingBean, long proposalId, long contestPhaseId, long currentUserId) throws NoSuchUserException, SystemException {
        //initialize a map of existing ratings
        Map<Long, ProposalRating> typeToRatingMap = new HashMap<Long, ProposalRating>();
        for (ProposalRating r: existingRatings) {
            ProposalRatingWrapper wrapper = new ProposalRatingWrapper(r);
            typeToRatingMap.put(wrapper.getRatingTypeId(), r);
        }

        Map<Long, String> ratingsFromForm = ratingBean.getRatingValues();
        //now update the values and save or create a new rating of not existent yet
        boolean commentAdded = false;
        if (ratingsFromForm != null) {
            for (Long typeId : ratingsFromForm.keySet()) {
                ProposalRating existingRating = typeToRatingMap.get(typeId);
                long newRatingValueId = Long.parseLong(ratingsFromForm.get(typeId));

                if (existingRating != null) {
                    //rating exists. update and save
                    existingRating.setRatingValueId(newRatingValueId);
                    //convention: save comment in first type
                    if (!commentAdded) {
                        existingRating.setComment(ratingBean.getComment());
                        existingRating.setCommentEnabled(true);
                        commentAdded = true;
                    } else {
                        existingRating.setComment(null);
                        existingRating.setCommentEnabled(false);
                    }
                    ProposalRatingLocalServiceUtil.updateRating(existingRating);
                } else {
                    String comment = null;
                    if (!commentAdded) {
                        comment = ratingBean.getComment();
                        commentAdded = true;
                    }
                    //create a new rating
                    ProposalRatingLocalServiceUtil.addRating(
                            proposalId,
                            contestPhaseId,
                            currentUserId,
                            newRatingValueId,
                            comment,
                            ""
                    );
                }
            }
        }
    }


}
