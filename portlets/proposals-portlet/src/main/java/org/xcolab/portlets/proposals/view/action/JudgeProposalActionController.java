package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.JudgeProposalBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/16/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("view")
public class JudgeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;



    @RequestMapping(params = {"action=saveJudgeRating"})
    public void saveJudgeRating(ActionRequest request, Model model,
                                 ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                 BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        // save judge rating
        persistAttribute(proposalId, contestPhaseId,ProposalAttributeKeys.JUDGE_RATING,0, judgeProposalBean.getJudgeRating() , null);
        // save judge action
        persistAttribute(proposalId, contestPhaseId,ProposalAttributeKeys.JUDGE_ACTION,0, judgeProposalBean.getJudgeAction().getAttributeValue() , null);
        // save judge comment
        persistAttribute(proposalId, contestPhaseId,ProposalAttributeKeys.JUDGE_COMMENT,0, -1 , judgeProposalBean.getJudgeComment());
    }

    @RequestMapping(params = {"action=saveFellowRating"})
    public void saveFellowRating(ActionRequest request, Model model,
                                 ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                 BindingResult result) throws PortalException, SystemException, ProposalsAuthorizationException {
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        // save selection of judges
        persistSelectedJudges(proposalId, contestPhaseId, judgeProposalBean.getSelectedJudges());
        // save fellow rating
        persistAttribute(proposalId, contestPhaseId,ProposalAttributeKeys.FELLOW_RATING,0,judgeProposalBean.getFellowRating(), null);
        // save fellow action
        persistAttribute(proposalId, contestPhaseId,ProposalAttributeKeys.FELLOW_ACTION,0,judgeProposalBean.getFellowAction().getAttributeValue(), null);
        // save fellow comment
        persistAttribute(proposalId, contestPhaseId,ProposalAttributeKeys.FELLOW_COMMENT,0, -1 , judgeProposalBean.getFellowComment());
    }

    private boolean persistAttribute(long proposalId, long contestPhaseId, String attributeName, long additionalId, long numericValue, String stringValue){
        ProposalContestPhaseAttribute attribute = getProposalContestPhaseAttributeCreateIfNotExists(proposalId,contestPhaseId,attributeName, additionalId);

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

    private boolean persistSelectedJudges(long proposalId, long contestPhaseId, List<Long> selectedJudges){
        ProposalContestPhaseAttribute judges = getProposalContestPhaseAttributeCreateIfNotExists(proposalId,contestPhaseId,ProposalAttributeKeys.SELECTED_JUDGES,0);

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

    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(long proposalId, long contestPhaseId, String attributeName, long additionalId){
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName,additionalId);
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
