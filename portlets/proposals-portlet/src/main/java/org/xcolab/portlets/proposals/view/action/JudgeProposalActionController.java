package org.xcolab.portlets.proposals.view.action;


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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("view")
public class JudgeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    // TODO: remove/change this
    @RequestMapping(params = {"action=sendComment"})
    public void sendComment(ActionRequest request, Model model, ActionResponse response) throws SystemException, PortalException, AddressException, MailEngineException {
        ProposalWrapper proposal = new ProposalWrapper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

//        //get judges decision
//        ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposal, proposalsContext.getUser(request));
//        String message = proposalJudgeWrapper.getEmailMessage(contestPhaseId, new ProposalsPreferencesWrapper(request));
//
//        if (message != null) {
//            try {
//                //get sender fellow
//                User author = proposalsContext.getUser(request);
//                List<User> contestFellows = proposalsContext.getContestWrapped(request).getContestFellows();
//                if (!contestFellows.contains(author)) {
//                    contestFellows.get(0);
//                } //take first fellow of list to be the sender
//
//                //assemble recipients
//                List<Long> recipients = new LinkedList<>();
//                for (ProposalTeamMemberWrapper member : proposal.getMembers()) {
//                    recipients.add(member.getUserId() );
//                }
//
//                //send message
//                String title = "Judges comments for your proposal '" + proposal.getName() + "'";
//                MessageUtil.sendMessage(title, message, author.getUserId(),
//                        author.getUserId(), recipients, request);
//
//                DiscussionCategoryGroupLocalServiceUtil.addComment(
//                        DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(proposal.getDiscussionId()),
//                        title, message, author);
//
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//        }
        ProposalLocalServiceUtil.contestPhasePromotionEmailNotifyProposalContributors(proposal.getWrapped(), proposalsContext.getContestPhase(request), request);
        ProposalLocalServiceUtil.contestPhasePromotionCommentNotifyProposalContributors(proposal.getWrapped(), proposalsContext.getContestPhase(request));

    }

    @RequestMapping(params = {"action=saveAdvanceDetails"})
    public void saveAdvanceDetails(ActionRequest request, Model model,
                                ActionResponse response, @Valid ProposalAdvancingBean proposalAdvancingBean,
                                BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        User currentUser = proposalsContext.getUser(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        // Security handling
        if (!(permissions.getCanFellowActions() && proposalsContext.getProposalWrapped(request).isUserAmongFellows(currentUser)) &&
                !permissions.getCanAdminAll()) {
            return;
        }

        // save judge decision
        if (proposalAdvancingBean.getAdvanceDecision() != JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue()) {
            persistAttribute(proposalId, contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0, proposalAdvancingBean.getAdvanceDecision());
        }

        // save judge comment
        if (proposalAdvancingBean.getAdvanceDecision() != JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue()) {
            ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));
            String judgeCommentTemplate = null;
            ProposalsPreferencesWrapper preferences = proposalsContext.getProposalsPreferences(request);
            if (proposalAdvancingBean.getAdvanceDecision() == JudgingSystemActions.AdvanceDecision.MOVE_ON.getAttributeValue()) {
                judgeCommentTemplate = preferences.getAdvanceAcceptanceText(contestPhase.getContestPhaseType());
            } else if (proposalAdvancingBean.getAdvanceDecision() == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON.getAttributeValue()) {
                judgeCommentTemplate = preferences.getAdvanceRejectionText(contestPhase.getContestPhaseType());
            }

            commentHelper.setAdvancingComment(judgeCommentTemplate, proposalAdvancingBean.getAdvanceComment());
        }
    }

    @RequestMapping(params = {"action=saveJudgingFeedback"})
    public void saveJudgingFeedback(ActionRequest request, Model model, ActionResponse response,
                                    @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean)
            throws PortalException, SystemException, ProposalsAuthorizationException, AddressException, MailEngineException {


        ProposalWrapper proposal = proposalsContext.getProposalWrapped(request);
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        User currentUser = proposalsContext.getUser(request);
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        // Security handling
        if (!(permissions.getCanJudgeActions() && proposal.isUserAmongSelectedJudge(currentUser))) {
            return;
        }

        persistAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_RATING, currentUser.getUserId(), judgeProposalFeedbackBean.getJudgeRating());
        persistAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_COMMENT, currentUser.getUserId(), judgeProposalFeedbackBean.getJudgeComment());
    }

    @RequestMapping(params = {"action=saveScreening"})
    public void saveScreening(ActionRequest request, Model model,
                                 ActionResponse response, @Valid FellowProposalScreeningBean fellowProposalScreeningBean,
                                 BindingResult result) throws PortalException, SystemException, ProposalsAuthorizationException {
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

        // save fellow rating
        if (Validator.isNotNull(fellowProposalScreeningBean.getFellowScreeningRating()))
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_RATING, 0, fellowProposalScreeningBean.getFellowScreeningRating());

        // save fellow action
        if (Validator.isNotNull(fellowProposalScreeningBean.getFellowScreeningAction()))
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, fellowProposalScreeningBean.getFellowScreeningAction());

        // save fellow comment
        ProposalJudgingCommentHelper commentHelper = new ProposalJudgingCommentHelper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));
        if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.INCOMPLETE.getAttributeValue()) {
            commentHelper.setScreeningComment(proposalsContext.getProposalsPreferences(request).getScreeningIncompleteText(), fellowProposalScreeningBean.getFellowScreeningCommentBody());
        } else if (fellowProposalScreeningBean.getFellowScreeningAction() == JudgingSystemActions.FellowAction.OFFTOPIC.getAttributeValue()) {
            commentHelper.setScreeningComment(proposalsContext.getProposalsPreferences(request).getScreeningOfftopicText(), fellowProposalScreeningBean.getFellowScreeningCommentBody());
        }
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
