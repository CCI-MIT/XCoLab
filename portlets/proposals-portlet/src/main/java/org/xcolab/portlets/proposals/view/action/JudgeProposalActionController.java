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
        // SAVE TO DB
        System.out.println("TEST");
    }

    @RequestMapping(params = {"action=saveFellowRating"})
    public void saveFellowRating(ActionRequest request, Model model,
                                 ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                 BindingResult result) throws PortalException, SystemException, ProposalsAuthorizationException {
        // SAVE TO DB

        // save selection of judges
        long proposalId = proposalsContext.getProposal(request).getProposalId();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
        persistSelectedJudges(proposalId, contestPhaseId, judgeProposalBean.getSelectedJudges());

        // save fellow rating




    }

    private boolean persistSelectedJudges(long proposalId, long contestPhaseId, List<Long> selectedJudges){
        ProposalContestPhaseAttribute judges = getProposalContestPhaseAttributeCreateIfNotExists(proposalId,contestPhaseId,ProposalAttributeKeys.SELECTED_JUDGES);

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

    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(long proposalId, long contestPhaseId, String attributeName){
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName);
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
