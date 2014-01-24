package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.JudgeProposalBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class JudgeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=sendEmail"})
    public void sendEmails(ActionRequest request, Model model, ActionResponse response) throws SystemException, PortalException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

        //get judges decision
        ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposalsContext.getProposal(request), proposalsContext.getUser(request));
        String message = proposalJudgeWrapper.getEmailMessage(contestPhaseId, new ProposalsPreferencesWrapper(request));

        if (message != null) {
            try {
                String messageSubject = "Your Climate CoLab proposal";
                ProposalWrapper wrapper = new ProposalWrapper(proposalsContext.getProposal(request));
                InternetAddress[] addressTo = new InternetAddress[] {
                        new InternetAddress(wrapper.getAuthor().getDisplayEmailAddress()) };

                InternetAddress addressFrom = new InternetAddress("admin@climatecolab.org");
                MailEngine.send(addressFrom, addressTo, null, null, null, messageSubject, message, false,
                        new InternetAddress[]{addressFrom}, null, null);

                //mark as finished
                persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGING_STATUS, 0, 1, null);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(params = {"action=saveJudgeRating"})
    public void saveJudgeRating(ActionRequest request, Model model,
                                ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

        // save judge rating
        if (judgeProposalBean.getJudgeRating() != null)
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_RATING, 0, judgeProposalBean.getJudgeRating(), null);

        // save judge action
        if (judgeProposalBean.getJudgeAction() != null)
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_ACTION, 0, judgeProposalBean.getJudgeAction().getAttributeValue(), null);

        // save judge comment
        if (judgeProposalBean.getJudgeComment() != null)
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_COMMENT, 0, -1, judgeProposalBean.getJudgeComment());
    }

    @RequestMapping(params = {"action=sendJudgeRating"})
    public void sendJudgeRating(ActionRequest request, Model model,
                                ActionResponse response, @RequestParam("judgeComment") String judgeComment)
            throws PortalException, SystemException, ProposalsAuthorizationException, AddressException, MailEngineException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();

        List<Long> recipientIds = new ArrayList<Long>();
        String subject = "Judge comment from " + proposalsContext.getUser(request).getScreenName() + " for proposal [" + proposalId + "]";

        ContestWrapper cr = new ContestWrapper(proposalsContext.getContest(request));
        for (User fellow : cr.getContestFellows()) {
            recipientIds.add(fellow.getUserId());
        }

        MessageUtil.sendMessage(subject, judgeComment, proposalsContext.getUser(request).getUserId(),
                proposalsContext.getUser(request).getUserId(), recipientIds, null);

    }

    @RequestMapping(params = {"action=saveFellowRating"})
    public void saveFellowRating(ActionRequest request, Model model,
                                 ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                 BindingResult result) throws PortalException, SystemException, ProposalsAuthorizationException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        // save selection of judges

        if (judgeProposalBean.getSelectedJudges() != null)
            persistSelectedJudges(proposalId, contestPhaseId, judgeProposalBean.getSelectedJudges());

        // save fellow rating
        if (judgeProposalBean.getFellowRating() != null)
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_RATING, 0, judgeProposalBean.getFellowRating(), null);

        // save fellow action
        if (judgeProposalBean.getFellowAction() != null)
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, judgeProposalBean.getFellowAction().getAttributeValue(), null);

        // save fellow comment
        if (judgeProposalBean.getFellowComment() != null)
            persistAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_COMMENT, 0, -1, judgeProposalBean.getFellowComment());
    }

    private boolean persistAttribute(long proposalId, long contestPhaseId, String attributeName, long additionalId, long numericValue, String stringValue) {
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(proposalId, contestPhaseId, attributeName, additionalId);

        attribute.setAdditionalId(additionalId);
        if (numericValue != -1) attribute.setNumericValue(numericValue);
        if (stringValue != null) attribute.setStringValue(stringValue);

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

        String s = "";
        for (Long l : selectedJudges) s += l + ";";
        judges.setStringValue(s.replaceAll(";$", ""));

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
