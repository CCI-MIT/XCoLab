package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.JudgeProposalBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.*;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("view")
public class JudgeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    // TODO: remove/change this
    @RequestMapping(params = {"action=sendComment"})
    public void sendComment(ActionRequest request, Model model, ActionResponse response) throws SystemException, PortalException {
        ProposalWrapper proposal = new ProposalWrapper(proposalsContext.getProposal(request), proposalsContext.getContestPhase(request));
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

        //get judges decision
        ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposal, proposalsContext.getUser(request));
        String message = proposalJudgeWrapper.getEmailMessage(contestPhaseId, new ProposalsPreferencesWrapper(request));

        if (message != null) {
            try {
                //get sender fellow
                User author = proposalsContext.getUser(request);
                List<User> contestFellows = proposalsContext.getContestWrapped(request).getContestFellows();
                if (!contestFellows.contains(author)) {
                    contestFellows.get(0);
                } //take first fellow of list to be the sender

                //assemble recipients
                List<Long> recipients = new LinkedList<>();
                for (ProposalTeamMemberWrapper member : proposal.getMembers()) {
                    recipients.add(member.getUserId() );
                }

                //send message
                String title = "Judges comments for your proposal '" + proposal.getName() + "'";
                MessageUtil.sendMessage(title, message, author.getUserId(),
                        author.getUserId(), recipients, request);

                DiscussionCategoryGroupLocalServiceUtil.addComment(
                        DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(proposal.getDiscussionId()),
                        title, message, author);

                //mark as finished
                //persistAttribute(proposal.getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGING_STATUS, curren);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(params = {"action=saveAdvanceDetails"})
    public void saveAdvanceDetails(ActionRequest request, Model model,
                                ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

        // save judge decision
        if (judgeProposalBean.getJudgeDecision() != JudgingSystemActions.JudgeDecision.NO_DECISION.getAttributeValue()) {
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0, judgeProposalBean.getJudgeDecision());
        }

        // save judge comment
        if (judgeProposalBean.getJudgeComment() != null)
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_COMMENT, 0, -1);
    }

    @RequestMapping(params = {"action=saveJudgingFeedback"})
    public void saveJudgingFeedback(ActionRequest request, Model model, ActionResponse response,
                                    @Valid JudgeProposalBean judgeProposalBean)
            throws PortalException, SystemException, ProposalsAuthorizationException, AddressException, MailEngineException {


        ProposalWrapper proposal = new ProposalWrapper(proposalsContext.getProposal(request));
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        User currentUser = proposalsContext.getUser(request);

        // TODO: security handling
        if (!proposal.isUserAmongSelectedJudge(currentUser)) {

        }

        persistAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_RATING, currentUser.getUserId(), judgeProposalBean.getJudgeRating());
        persistAttribute(proposal.getProposalId(), contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_REVIEW_COMMENT, currentUser.getUserId(), judgeProposalBean.getJudgeComment());
    }

    @RequestMapping(params = {"action=saveScreening"})
    public void saveScreening(ActionRequest request, Model model,
                                 ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                 BindingResult result) throws PortalException, SystemException, ProposalsAuthorizationException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        // save selection of judges

        persistSelectedJudges(proposalId, contestPhaseId, judgeProposalBean.getSelectedJudges());

        // save fellow rating
        if (Validator.isNotNull(judgeProposalBean.getFellowRating()))
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_RATING, 0, judgeProposalBean.getFellowRating());

        // save fellow action
        if (Validator.isNotNull(judgeProposalBean.getFellowAction()))
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, judgeProposalBean.getFellowAction());

        // save fellow comment
        if (Validator.isNotNull(judgeProposalBean.getFellowComment()))
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_COMMENT, 0, judgeProposalBean.getFellowComment());
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
